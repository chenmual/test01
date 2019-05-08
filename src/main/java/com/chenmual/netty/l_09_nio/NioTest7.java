package com.chenmual.netty.l_09_nio;

import java.nio.ByteBuffer;

public class NioTest7 {
	public static void main(String[] args){
		ByteBuffer buffer = ByteBuffer.allocate(10);

		System.out.println(buffer.getClass());
		for(int i = 0; i < buffer.capacity(); i++){
			buffer.put((byte)i);
		}

		ByteBuffer readonlyBuffer1 = buffer.asReadOnlyBuffer();
		System.out.println(readonlyBuffer1.getClass());
		readonlyBuffer1.put((byte)2);
	}
}
