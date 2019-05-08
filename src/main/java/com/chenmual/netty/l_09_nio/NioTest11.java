package com.chenmual.netty.l_09_nio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

/**
 * Scattering Gathering
 */
public class NioTest11 {
	public static void main(String[] args) throws IOException {
		ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

		InetSocketAddress address = new InetSocketAddress(8899);
		serverSocketChannel.socket().bind(address);


		//构造3个buffer读取
		int messageLength = 2 + 3 + 4;

		ByteBuffer[] buffers = new ByteBuffer[3];
		buffers[0] = ByteBuffer.allocate(2);
		buffers[1] = ByteBuffer.allocate(3);
		buffers[2] = ByteBuffer.allocate(4);

		SocketChannel socketChannel = serverSocketChannel.accept();

		while(true){
			//telnet localhost 8899 | send helloworl
			int byteRead = 0;
			while(byteRead < messageLength) {
				long r = socketChannel.read(buffers);
				byteRead += r;
				System.out.println("bytesRead: " + byteRead);
				Arrays.asList(buffers).stream()
						.map(buffer -> "position:" + buffer.position() + ", limit" + buffer.limit())
						.forEach(System.out::println);
			}
			Arrays.asList(buffers).forEach(buffer -> {
				buffer.flip();
			});
			long bytesWriten = 0;
			while(bytesWriten < messageLength){
				long r = socketChannel.write(buffers);
				bytesWriten += r;
			}
			Arrays.asList(buffers).forEach(buffer -> {
				buffer.clear();
			});

			System.out.println("bytesRead: " + byteRead + ", byteswriten:" + bytesWriten + ", messageLength" + messageLength);
		}
	}
}
