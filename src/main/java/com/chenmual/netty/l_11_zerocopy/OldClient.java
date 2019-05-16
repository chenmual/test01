package com.chenmual.netty.l_11_zerocopy;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class OldClient {
	public static void main(String[] args) throws IOException {

		Socket socket = new Socket("localhost", 8899);
		DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());

		String filename = "H:\\tools\\pycharm-professional-2019.1.1_exe";
		InputStream inputStream = new FileInputStream(filename);

		byte[] buffer = new byte[4096];
		long readCount = 0;
		long total = 0;
		long startTime = System.currentTimeMillis();
		while((readCount = inputStream.read(buffer)) >= 0){
			total += readCount;
			dataOutputStream.write(buffer);
		}
		System.out.println("发送字节数:" + total + " 耗时:" + (System.currentTimeMillis() - startTime));
		dataOutputStream.close();
		socket.close();
		inputStream.close();
	}
}
