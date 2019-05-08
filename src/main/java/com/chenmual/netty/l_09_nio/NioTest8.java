package com.chenmual.netty.l_09_nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NioTest8 {
	public static void main(String[] args) throws IOException {
		FileInputStream fileInputStream = new FileInputStream("input2.txt");
		FileOutputStream fileOutputStream = new FileOutputStream("output2.txt");

		FileChannel fileChannel1 = fileInputStream.getChannel();
		FileChannel fileChannel2 = fileOutputStream.getChannel();

		ByteBuffer buffer = ByteBuffer.allocateDirect(512);

		while(true){
			buffer.clear();
			int read = fileChannel1.read(buffer);
			System.out.println("read: " + read);
			if(-1 == read) {
				break;
			}
			buffer.flip();
			fileChannel2.write(buffer);
		}


		fileInputStream.close();
		fileOutputStream.close();
	}
}
