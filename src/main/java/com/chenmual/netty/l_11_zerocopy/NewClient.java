package com.chenmual.netty.l_11_zerocopy;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

public class NewClient {
	public static void main(String[] args) throws IOException {
		SocketChannel socketChannel = SocketChannel.open();
		socketChannel.connect(new InetSocketAddress("localhost", 8167));
		socketChannel.configureBlocking(false);


		String filename = "H:\\tools\\pycharm-professional-2019.1.1_exe";
		FileChannel fileChannel = new FileInputStream(filename).getChannel();


		long startTime = System.currentTimeMillis();
		//将filechannel的内容写到socketchannel当中
		//方法比简单while效率更高一些,有些系统可以直接从缓存0拷贝到channel中
		long transferCount = fileChannel.transferTo(0, fileChannel.size(), socketChannel);

		System.out.println("发送总字节数:" + transferCount + " 耗时:" + (System.currentTimeMillis() - startTime));

		new Scanner(System.in).nextLine();
		fileChannel.close();
	}
}
