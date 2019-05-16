package com.chenmual.netty.l_11_zerocopy;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class NewServer {
	public static void main(String[] args) throws IOException {
		InetSocketAddress inetSocketAddress = new InetSocketAddress(8167);

		ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
		ServerSocket serverSocket = serverSocketChannel.socket();
		serverSocket.setReuseAddress(true);
		serverSocket.bind(inetSocketAddress);
		ByteBuffer byteBuffer = ByteBuffer.allocate(4096);

		while(true){
			SocketChannel socketChannel = serverSocketChannel.accept();
			socketChannel.configureBlocking(false);
			int readCount = 0;
			while(-1 != readCount){
				try {
					readCount = socketChannel.read(byteBuffer);
				}catch(Exception ex){
					ex.printStackTrace();
				}
				byteBuffer.rewind();
			}
		}
	}
}
