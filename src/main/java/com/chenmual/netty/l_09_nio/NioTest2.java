package com.chenmual.netty.l_09_nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NioTest2 {
	public static void main(String[] args) throws IOException {
		FileInputStream fileInputStream	= new FileInputStream("NioTest2.txt");
		FileChannel fileChannel = fileInputStream.getChannel();

		ByteBuffer byteBuffer = ByteBuffer.allocate(512);

		fileChannel.read(byteBuffer);

		byteBuffer.flip();//翻转

		while(byteBuffer.remaining() > 0){
			byte b = byteBuffer.get();
			System.out.println("Character:" + (char)b);
		}

		fileInputStream.close();
	}
}
