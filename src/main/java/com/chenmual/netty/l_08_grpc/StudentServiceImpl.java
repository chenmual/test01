package com.chenmual.netty.l_08_grpc;

import com.chenmual.proto.*;
import io.grpc.stub.StreamObserver;

import java.util.Scanner;
import java.util.UUID;

public class StudentServiceImpl extends StudentServiceGrpc.StudentServiceImplBase {
	@Override
	public void getRealNameByUserName(MyRequest request,StreamObserver<MyResponse> responseObserver) {
		System.out.println("接受到客户端信息1:" + request.getUsername());

		responseObserver.onNext(MyResponse.newBuilder().setRealname("张三").build());
		System.out.println("下一步");
		Scanner s = new Scanner(System.in);
		s.nextLine();
		responseObserver.onCompleted();//标识响应结束
		System.out.println("1 onCompleted完成");
	}

	@Override
	public void getStudentsByAge(StudentRequest request,StreamObserver<StudentResponse> responseObserver) {
		System.out.println("接受到客户端信息2: " + request.getAge());
		System.out.println("服务器准备下发onNext1 下一步");
		new Scanner(System.in).nextLine();
		responseObserver.onNext(StudentResponse.newBuilder().setName("张三").setAge(20).setCity("北京").build());
		System.out.println("服务器准备下发onNext2 下一步");
		new Scanner(System.in).nextLine();
		responseObserver.onNext(StudentResponse.newBuilder().setName("李四").setAge(33).setCity("上海").build());
		System.out.println("服务器准备下发onNext3 下一步");
		new Scanner(System.in).nextLine();
		responseObserver.onNext(StudentResponse.newBuilder().setName("abc").setAge(22).setCity("深圳").build());
		System.out.println("服务器准备下发onNext4 下一步");
		new Scanner(System.in).nextLine();
		responseObserver.onNext(StudentResponse.newBuilder().setName("fds").setAge(11).setCity("成都").build());
		System.out.println("准备下发OnCompleted 下一步");
		new Scanner(System.in).nextLine();
		responseObserver.onCompleted();
		System.out.println("2 onCompleted完成");
	}

	@Override
	public StreamObserver<StudentRequest> getStudentsWrapperByAges(StreamObserver<StudentResponseList> responseObserver) {

		return new StreamObserver<StudentRequest>() {
			@Override
			public void onNext(StudentRequest value) {
				System.out.println("onNext:" + value.getAge());
//				System.out.println("等待下一步");
//				Scanner s = new Scanner(System.in);
//				s.nextLine();
//				System.out.println("onNext 完成");
			}

			@Override
			public void onError(Throwable t) {
				System.out.println(t.getMessage());
			}

			@Override
			public void onCompleted() {
				System.out.println("服务器调用onCompleted");
				System.out.println("等待下一步");
				Scanner s = new Scanner(System.in);
				s.nextLine();
				StudentResponse studentResponse1 = StudentResponse.newBuilder().setName("张三").setAge(20).setCity("北京").build();
				StudentResponse studentResponse2 = StudentResponse.newBuilder().setName("李四").setAge(30).setCity("上海").build();
					StudentResponseList studentResponseList = StudentResponseList.newBuilder()
							.addStudentResponse(studentResponse1)
							.addStudentResponse(studentResponse2)
							.build();

				responseObserver.onNext(studentResponseList);
				responseObserver.onCompleted();

				System.out.println("完成返回结果");
			}
		};
	}

	@Override
	public StreamObserver<StreamRequest> biTalk(StreamObserver<StreamResponse> responseObserver) {
		return new StreamObserver<StreamRequest>() {
			@Override
			public void onNext(StreamRequest value) {
				System.out.println(value.getRequestInfo());
				responseObserver.onNext(StreamResponse.newBuilder().setResponseInfo(UUID.randomUUID().toString()).build());
			}

			@Override
			public void onError(Throwable t) {
				System.out.println(t.getMessage());
			}

			@Override
			public void onCompleted() {
				responseObserver.onCompleted();
			}
		};
	}
}
