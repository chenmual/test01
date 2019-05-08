package com.chenmual.netty.l_09_nio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

public class NioTest10 {
	public static void main(String[] args) throws IOException {
		RandomAccessFile randomAccessFile = new RandomAccessFile("NioTest10.txt", "rw");
		FileChannel fileChannel = randomAccessFile.getChannel();

		FileLock fileLock = fileChannel.lock(3, 6, true);

		System.out.println("valid: " + fileLock.isValid());

		System.out.println("looktype: " + fileLock.isShared());//共享锁

		fileLock.release();

		randomAccessFile.close();
	}
}
