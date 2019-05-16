package com.chenmual.netty.l_12_bytebuff;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.nio.charset.Charset;

public class ByteBufTest1 {
	public static void main(String[] args){
		ByteBuf byteBuf = Unpooled.copiedBuffer("张hello world", Charset.forName("utf-8"));

		if(byteBuf.hasArray()){//如果为真 则字节数组 堆上缓冲
			byte[] content = byteBuf.array();
			System.out.println(new String(content, Charset.forName("utf-8")));
			System.out.println(byteBuf);
			System.out.println(byteBuf.arrayOffset());
			System.out.println(byteBuf.readerIndex());
			System.out.println(byteBuf.writerIndex());
			System.out.println(byteBuf.capacity());
			System.out.println(byteBuf.readableBytes());
			System.out.println(byteBuf.writableBytes());

			for(int i = 0; i < byteBuf.capacity(); i++){
				System.out.println((char)byteBuf.getByte(i));
			}

			System.out.println(byteBuf.getCharSequence(0, 4, Charset.forName("utf-8")));
			System.out.println(byteBuf.getCharSequence(4, 6, Charset.forName("utf-8")));
		}else{//直接缓冲


		}
	}
}
