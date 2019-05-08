package com.chenmual.netty.l_10_charset;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

public class EncodeTest01 {
	public static void main(String[] args) throws IOException {
	    String inputFile = "Char01.txt";
	    String outputFile = "Char02.txt";
		RandomAccessFile inputRandomAccessFile = new RandomAccessFile(inputFile, "r");
		RandomAccessFile outputRandomAccessFile = new RandomAccessFile(outputFile, "rw");
		long inputLength = new File(inputFile).length();
		FileChannel inputFileChannel = inputRandomAccessFile.getChannel();
		FileChannel outputFileChannel = outputRandomAccessFile.getChannel();

		MappedByteBuffer inputData = inputFileChannel.map(FileChannel.MapMode.READ_ONLY, 0, inputLength);

		Charset charset = Charset.forName("ISO-8859-1");
		CharsetDecoder decoder = charset.newDecoder();//数组转字符串
		CharsetEncoder encoder = charset.newEncoder();//字符串转成数组

		CharBuffer charBuffer = decoder.decode(inputData);
		ByteBuffer byteBuffer = encoder.encode(charBuffer);

		outputFileChannel.write(byteBuffer);
		outputRandomAccessFile.close();
		inputRandomAccessFile.close();


		Charset.availableCharsets().forEach((k, v) -> {
			System.out.println(k + ", " + v);
		});

	}
}
