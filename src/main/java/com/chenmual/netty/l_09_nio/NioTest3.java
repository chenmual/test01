package com.chenmual.netty.l_09_nio;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NioTest3 {
	public static void main(String[] args) throws IOException {
		FileOutputStream fileOutputStream = new FileOutputStream("NioTest3.txt");
		FileChannel fileChannel = fileOutputStream.getChannel();
		ByteBuffer byteBuffer = ByteBuffer.allocate(512);
		byte[] messages = "hello world welcome, nihao".getBytes();

		for(int i = 0; i < messages.length; i++){
			byteBuffer.put(messages[i]);//数据与buff交互
		}

		byteBuffer.flip();

		fileChannel.write(byteBuffer);

		fileOutputStream.close();
	}
}
