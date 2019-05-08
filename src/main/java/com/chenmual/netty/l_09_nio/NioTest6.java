package com.chenmual.netty.l_09_nio;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

public class NioTest6 {
	public static void main(String[] args){
		ByteBuffer buffer = ByteBuffer.allocate(10);

		for(int i = 0; i < buffer.capacity(); i++){
			System.out.println("-------A");
			System.out.println(buffer.position());
			buffer.put((byte) i);
			System.out.println(buffer.position());
		}

		buffer.position(2);
		buffer.limit(6);

		ByteBuffer slice = buffer.slice();

		for(int i = 0; i < slice.capacity(); i++){
			System.out.println("-------");
			System.out.println(slice.position());
			byte b = slice.get(i);
			System.out.println(slice.position());
			b *= 2;
			slice.put(i, b);
			System.out.println(slice.position());
			slice.put((byte)2);
			System.out.println(slice.position());
		}

		buffer.position(0);
		buffer.limit(buffer.capacity());

		while(buffer.hasRemaining()){
			System.out.println(buffer.get());
		}

		System.out.println("---------");
		IntBuffer intBuffer = IntBuffer.allocate(10);

		for(int i = 0; i < 10; i++){
			System.out.println("--------B");
			System.out.println(intBuffer.position());
			intBuffer.put(i);
			System.out.println(intBuffer.position());
		}
		intBuffer.position(0);
		for(int i = 0; i < 10; i++){
			System.out.println("-----------C");
			System.out.println(intBuffer.position());
			System.out.println(intBuffer.get());
			System.out.println(intBuffer.position());
		}

		intBuffer.position(0);
		for(int i = 0; i < 10; i++){
			System.out.println("-----------D");
			System.out.println(intBuffer.position());
			System.out.println(intBuffer.get(i));
			System.out.println(intBuffer.position());
		}

		intBuffer.position(0);
		for(int i = 0; i < 10; i++){
			System.out.println("-----------E");
			System.out.println(intBuffer.position());
			intBuffer.put(i, i);
			System.out.println(intBuffer.position());
		}

		intBuffer.position(4);
		for(int i = 0; i < 4; i++){
			System.out.println("-----------F");
			System.out.println(intBuffer.position());
			System.out.println(intBuffer.get());
			System.out.println(intBuffer.position());
		}

		intBuffer.position(0);
		intBuffer.limit(6);
		while(intBuffer.hasRemaining()){
			System.out.println(intBuffer.get());
		}

	}
}
