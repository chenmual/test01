package com.chenmual.netty.l_09_nio;

import java.nio.IntBuffer;
import java.security.SecureRandom;

public class NioTest1 {
	public static void main(String[] args){
		IntBuffer buffer = IntBuffer.allocate(10);

		for(int i = 0;i < 5; i++){
			int randomNumber = new SecureRandom().nextInt(20);
			System.out.println("-------");
			System.out.println(buffer.position());
			buffer.put(randomNumber);
			System.out.println(buffer.position());
		}

		System.out.println("before limit=" + buffer.limit());

		buffer.flip();

		System.out.println("after limit=" + buffer.limit());

		while(buffer.hasRemaining()){
			System.out.println();
			System.out.println("----------");
			System.out.println("position=" + buffer.position());
			System.out.println("limit=" + buffer.limit());
			System.out.println("capacity=" + buffer.capacity());
			System.out.println(buffer.get());
			System.out.println("position=" + buffer.position());
		}

		System.out.println("--------");
		System.out.println("before limit=" + buffer.limit());
		buffer.clear();
		System.out.println("after limit=" + buffer.limit());

		for(int i = 0;i < 7; i++){
			int randomNumber = new SecureRandom().nextInt(20);
			buffer.put(randomNumber);
		}

		buffer.flip();


		while(buffer.hasRemaining()){
			System.out.println("----");
			System.out.println("position=" + buffer.position());
			System.out.println("limit=" + buffer.limit());
			System.out.println("capacity=" + buffer.capacity());
			System.out.println(buffer.get());
		}

	}
}
