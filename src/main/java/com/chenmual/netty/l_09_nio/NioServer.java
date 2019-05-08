package com.chenmual.netty.l_09_nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class NioServer {
	private static Map<String, SocketChannel> clientMap = new HashMap<>();


	public static void main(String[] args) throws IOException {
		ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
		serverSocketChannel.configureBlocking(false);
		ServerSocket serverSocket = serverSocketChannel.socket();
		serverSocket.bind(new InetSocketAddress(8899));

		//创建selector 把serverSocketChannel注册到上 关注accept事件
		Selector selector = Selector.open();//server负责链接的channel
		serverSocketChannel.register(selector,SelectionKey.OP_ACCEPT);

		while(true) {
			try {
				selector.select();
				//当获取事件时 把关注的keys返回
				Set<SelectionKey> selectionKeys = selector.selectedKeys();


				selectionKeys.forEach(selectionKey -> {
					final SocketChannel client;

					try {
						if(selectionKey.isAcceptable()){
							//在哪个channel上触发的acceptable
							ServerSocketChannel server = (ServerSocketChannel) selectionKey.channel();
							client = server.accept();//client负责读数据的channel

							client.configureBlocking(false);
							client.register(selector, SelectionKey.OP_READ);

							String key = "[" + UUID.randomUUID().toString() + "]";
							clientMap.put(key, client);
						}else if(selectionKey.isReadable()){
							client = (SocketChannel) selectionKey.channel();
							ByteBuffer readBuffer = ByteBuffer.allocate(1024);
							int count = client.read(readBuffer);

							if(count > 0){
								readBuffer.flip();
								Charset charset = Charset.forName("UTF-8");
								String recievedMessage = String.valueOf(charset.decode(readBuffer).array());

								System.out.println(client + ": " + recievedMessage);
								String senderKey = null;
								for(Map.Entry<String, SocketChannel> entry : clientMap.entrySet()){
									if(client == entry.getValue()){
										senderKey = entry.getKey();
										break;
									}
								}

								for(Map.Entry<String, SocketChannel> entry : clientMap.entrySet()){
									SocketChannel socketChannel = entry.getValue();
									ByteBuffer writeBuffer = ByteBuffer.allocate(1024);
									writeBuffer.put((senderKey + ", " + recievedMessage).getBytes());

									writeBuffer.flip();

									socketChannel.write(writeBuffer);
								}
							}
						}
					}catch(Exception e){
						e.printStackTrace();
					}
				});
				//处理完之后清空
				selectionKeys.clear();
			}catch(Exception e){
				e.printStackTrace();
			}
		}

	}
}
