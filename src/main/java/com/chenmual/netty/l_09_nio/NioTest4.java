package com.chenmual.netty.l_09_nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NioTest4 {
	public static void main(String[] args) throws IOException {
		FileInputStream inputStream = new FileInputStream("input.txt");
		FileOutputStream outputStream = new FileOutputStream("output.txt");

		FileChannel inputChannel = inputStream.getChannel();
		FileChannel outputChannel = outputStream.getChannel();

		ByteBuffer buffer = ByteBuffer.allocate(30);

		while(true){
			buffer.clear();
			System.out.println(buffer.position() + "limit=" + buffer.limit());
			int read = inputChannel.read(buffer);
			System.out.println(buffer.position() + "limit=" + buffer.limit());
			System.out.println("read1=" + read);
			read = inputChannel.read(buffer);
			System.out.println("read2=" + read);
			System.out.println(buffer.position() + "limit=" + buffer.limit());
			if(-1 == read){
				break;
			}
			buffer.flip();
			System.out.println(buffer.position() + "limit=" + buffer.limit());
			outputChannel.write(buffer);
			System.out.println(buffer.position() + "limit=" + buffer.limit());
			System.out.println("-----");
		}

		outputStream.close();
		inputStream.close();
	}
}
