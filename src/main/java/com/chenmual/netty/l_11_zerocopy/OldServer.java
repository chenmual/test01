package com.chenmual.netty.l_11_zerocopy;

import java.io.DataInputStream;
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

			try {
				byte[] byteArray = new byte[4096];

				while(true){

				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
}
