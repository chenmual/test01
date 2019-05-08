package com.chenmual.netty.l_09_nio;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NioClient {
	public static void main(String[] args){
	    try {
			SocketChannel socketChannel = SocketChannel.open();
			socketChannel.configureBlocking(false);

			Selector selector = Selector.open();
			socketChannel.register(selector, SelectionKey.OP_CONNECT);
			socketChannel.connect(new InetSocketAddress("127.0.0.1", 8899));

			while(true){
				selector.select();
				Set<SelectionKey> keys = selector.selectedKeys();

				for(SelectionKey selectionKey : keys){
					if(selectionKey.isConnectable()){
						SocketChannel client = (SocketChannel) selectionKey.channel();
						if(client.isConnectionPending()){
							client.finishConnect();
							//真正建立好链接
							ByteBuffer writeBuffer = ByteBuffer.allocate(1024);

							writeBuffer.put((LocalDateTime.now() + " 链接成功").getBytes());
							writeBuffer.flip();

							client.write(writeBuffer);

							ExecutorService executorService = Executors.newSingleThreadExecutor(Executors.defaultThreadFactory());

							executorService.submit(() -> {
								while(true){
									try {
										writeBuffer.clear();
										InputStreamReader input = new InputStreamReader(System.in);
										BufferedReader br = new BufferedReader(input);
										String sendMessage = br.readLine();

										writeBuffer.put(sendMessage.getBytes());
										writeBuffer.flip();

										client.write(writeBuffer);
									}catch(Exception e){

									}
								}
							});
						}
						client.register(selector, SelectionKey.OP_READ);
					}else if(selectionKey.isReadable()){
						SocketChannel client = (SocketChannel) selectionKey.channel();
						ByteBuffer buffer = ByteBuffer.allocate(1024);
						int count = client.read(buffer);

						if(count > 0){
							String recievedMessage = new String(buffer.array(), 0, count);
							System.out.println(recievedMessage);
						}

					}
				}
				keys.clear();
			}
		}catch(Exception e){
	    	e.printStackTrace();
		}
	}
}
