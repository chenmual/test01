package com.chenmual.netty.l_11_zerocopy;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 传统的serversocket
 */
public class OldServer {
	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket = new ServerSocket(8899);
		while(true){
			Socket socket = serverSocket.accept();//阻塞等待链接

			DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());

			String filename = "H:\\tools\\net2cat-0.7.1.tar.gz";

			FileOutputStream fileOutputStream = new FileOutputStream(filename);
			int total = 0;
			long startTime = System.currentTimeMillis();
			try {
				byte[] byteArray = new byte[4096];
				int len = 0;
				while(true){
					len = dataInputStream.read(byteArray, 0, byteArray.length);
					total += len;
//					fileOutputStream.write(byteArray);
//					fileOutputStream.flush();
					if(-1 == len){
						break;
					}
				}
				System.out.println("收到字节数:" + total + " 耗时:" + (System.currentTimeMillis() - startTime));
			}catch(Exception e){
				e.printStackTrace();
			}finally {
				dataInputStream.close();
				fileOutputStream.close();
			}
		}
	}
}
