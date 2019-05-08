package com.chenmual.netty.l_08_grpc;

import com.chenmual.proto.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class GrpcClient {
	public static void main(String[] args) throws InterruptedException {
		ManagedChannel managedChannel = ManagedChannelBuilder.forAddress("localhost", 8899)
				.usePlaintext().build();

		//同步方式
		StudentServiceGrpc.StudentServiceBlockingStub blockingStub = StudentServiceGrpc
				.newBlockingStub(managedChannel);
		//异步方式(流)
		StudentServiceGrpc.StudentServiceStub studentServiceStub = StudentServiceGrpc.newStub(managedChannel);

		System.out.println("--------------1------------下一步");
		new Scanner(System.in).nextLine();
		MyResponse myResponse = blockingStub
				.getRealNameByUserName(MyRequest.newBuilder().setUsername("张三").build());
		System.out.println(myResponse.getRealname());

		System.out.println("--------------2------------下一步");
		new Scanner(System.in).nextLine();
		//阻塞方式
		Iterator<StudentResponse> iterator = blockingStub.getStudentsByAge(StudentRequest.newBuilder().setAge(20).build());

		while(iterator.hasNext()){
			System.out.println("2的迭代器下一个");
//			new Scanner(System.in).nextLine();
			StudentResponse studentResponse = iterator.next();
			System.out.println("{" + studentResponse.getName() + "," + studentResponse.getAge() + "," + studentResponse.getCity() + "}");
		}

		System.out.println("--------------3------------下一步");
		System.out.print("建立observer..");
		StreamObserver<StudentResponseList> studentResponseListStreamObserver = new StreamObserver<StudentResponseList>() {
			@Override
			public void onNext(StudentResponseList value) {
				//服务端向客户端返回真正的数据结构
				//这里的代码由服务器调用客户端的
				value.getStudentResponseList().forEach(studentResponse->{
					System.out.println(studentResponse.getName());
					System.out.println(studentResponse.getAge());
					System.out.println(studentResponse.getCity());
					System.out.println("******");
				});
			}

			@Override
			public void onError(Throwable t) {
				System.out.println(t.getMessage());
			}

			@Override
			public void onCompleted() {
				System.out.println("onCompleted");
			}
		};
		System.out.println("..完成");

		//构造客户端向服务器所发送的数据本身
		StreamObserver<StudentRequest> studentRequestStreamObserver = studentServiceStub.getStudentsWrapperByAges(studentResponseListStreamObserver);
		System.out.println("客户端准备调用onNext1");
		new Scanner(System.in).nextLine();
		studentRequestStreamObserver.onNext(StudentRequest.newBuilder().setAge(20).build());
		System.out.println("客户端准备调用onNext2");
		new Scanner(System.in).nextLine();
		studentRequestStreamObserver.onNext(StudentRequest.newBuilder().setAge(30).build());
		System.out.println("客户端准备调用onNext3");
		new Scanner(System.in).nextLine();
		studentRequestStreamObserver.onNext(StudentRequest.newBuilder().setAge(40).build());
		System.out.println("客户端准备调用onNext4");
		new Scanner(System.in).nextLine();
		studentRequestStreamObserver.onNext(StudentRequest.newBuilder().setAge(50).build());
		System.out.println("客户端准备调用onCompleted");
		new Scanner(System.in).nextLine();
		studentRequestStreamObserver.onCompleted();
		System.out.println("客户端调用onCompleted完成");

		System.out.println("--------------4------------下一步");
		StreamObserver<StreamRequest> requestStreamObserver = studentServiceStub.biTalk(new StreamObserver<StreamResponse>() {
			@Override
			public void onNext(StreamResponse value) {
				System.out.println(value.getResponseInfo());
			}

			@Override
			public void onError(Throwable t) {
				System.out.println(t.getMessage());
			}

			@Override
			public void onCompleted() {
				System.out.println("onCompleted");
			}
		});

		for(int i = 0; i < 10; i++){
			requestStreamObserver.onNext(StreamRequest.newBuilder().setRequestInfo(LocalDateTime.now().toString()).build());
			TimeUnit.SECONDS.sleep(1);
		}

		Scanner s = new Scanner(System.in);
		while(s.hasNext()){
			String str = s.nextLine();
			System.out.println(str);
		}
	}
}
