package com.chenmual.netty.l_09_nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class NioTest12 {
	public static void main(String[] args) throws IOException {
	    int[] ports = new int[]{
	    	5000, 5001, 5002, 5003, 5004
		};

		Selector selector = Selector.open();
//		System.out.println(SelectorProvider.provider().getClass());
		for(int i = 0;i < ports.length; i++){
			ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
			serverSocketChannel.configureBlocking(false);//设置非阻塞

			ServerSocket serverSocket = serverSocketChannel.socket();
			InetSocketAddress address = new InetSocketAddress(ports[i]);
			serverSocket.bind(address);

			int key = SelectionKey.OP_ACCEPT;//注册感兴趣的key
			SelectionKey selectionKey = serverSocketChannel.register(selector, key);
			//每当selector注册到channel上都会创建key
			//key的生命周期持续到cancel方法调用/channel.close/selector.close

			System.out.println("监听端口: " + ports[i]);
		}

		while(true) {
			int keyCount = selector.select();
			System.out.println("number" + keyCount);

			Set<SelectionKey> selectionKeys = selector.selectedKeys();//selector监听多个通道所以返回一个集合

			System.out.println("selectorKeys:" + selectionKeys);
			Iterator<SelectionKey> iterator = selectionKeys.iterator();

			while(iterator.hasNext()) {
				SelectionKey selectionKey = iterator.next();
				if(selectionKey.isAcceptable()){
					//完成链接
					ServerSocketChannel serverSocketChannel = (ServerSocketChannel) selectionKey.channel();
					SocketChannel socketChannel = serverSocketChannel.accept();
					socketChannel.configureBlocking(false);

					socketChannel.register(selector, SelectionKey.OP_READ);//监听read事件
					iterator.remove();//并把原先监听的事件移除

					System.out.println("获得客户端链接:  " + socketChannel);
				}else if(selectionKey.isReadable()){
					//完成数据读取
					SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
					int byteRead = 0;
					while(true){
						ByteBuffer byteBuffer = ByteBuffer.allocate(512);
						byteBuffer.clear();

						int read = socketChannel.read(byteBuffer);
						if(read <= 0){
							break;
						}
						byteBuffer.flip();
						socketChannel.write(byteBuffer);

						byteRead += read;
					}
					System.out.println("读取:" + byteRead + ", 来自于:" + socketChannel);
					iterator.remove();//处理完的key需要remove
				}else{
					try {
						TimeUnit.SECONDS.sleep(5);
					} catch(InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("null");
				}
			}
		}
	}
}
