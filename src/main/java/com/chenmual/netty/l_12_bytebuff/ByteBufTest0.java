package com.chenmual.netty.l_12_bytebuff;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.util.Random;

public class ByteBufTest0 {
	public static void main(String[] args){
		ByteBuf buffer = Unpooled.buffer(10);//创建一个长度为10的buf
		for(int i = 0; i< 10; i++){
			buffer.writeByte(i);//相对方法 每次调用使writerindex++
			buffer.setByte(i, i + 1);//绝对方法
		}
		//0		<=		readindex		<=		writeindex		<=		capacity
		//可丢弃的		|可读的					|可写的					|
		//调用discardReadBytes()方法之后清空可丢弃的区间0=readindex<	=writeindex<=capacity
		//调用clear()之后0=readindex=writeindex<=capacity
		for(int i = 0; i < buffer.capacity(); i++	){
			System.out.println(buffer.getByte(i));//绝对方法
//			System.out.println(buffer.readByte());//相对方法 每次readindex++
		}

		//----------------------
//		ByteBuf byteBuf = Unpooled.buffer(1024);
//		while(byteBuf.isReadable()){//写入可读空间
//			System.out.println(byteBuf.readByte());
//			System.out.println(byteBuf.readBytes(buffer));//往参数buffer中写 buffer中writeindex++
//		}
//
//		while(byteBuf.maxWritableBytes() > 4){
//			byteBuf.writeInt(new Random().nextInt());
//		}




	}
}
