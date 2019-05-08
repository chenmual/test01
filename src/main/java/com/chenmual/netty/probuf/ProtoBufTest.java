package com.chenmual.netty.probuf;

import com.google.protobuf.InvalidProtocolBufferException;

public class ProtoBufTest {
	public static void main(String[] args) throws InvalidProtocolBufferException {
	    //构造Student对象
		DataInfo.Student student = DataInfo.Student.newBuilder()
				.setName("张三")
				.setAge(20)
				.setAddress("北京").build();
	    byte[] student2ByteArray = student.toByteArray();

	    DataInfo.Student student2 = DataInfo.Student.parseFrom(student2ByteArray);

		System.out.println(student2.getName());
		System.out.println(student2.getAddress());
		System.out.println(student2.getAge());

	}
}
