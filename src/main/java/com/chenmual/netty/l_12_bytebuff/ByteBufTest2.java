package com.chenmual.netty.l_12_bytebuff;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.CompositeByteBuf;
import io.netty.buffer.Unpooled;

import java.util.Iterator;

public class ByteBufTest2 {
	public static void main(String[] args){
		CompositeByteBuf compositeByteBuf = Unpooled.compositeBuffer();//返回一个新的大端组件
		ByteBuf heatBuf = Unpooled.buffer(10);
		ByteBuf directBuf = Unpooled.directBuffer(8);

		compositeByteBuf.addComponents(heatBuf, directBuf);
		compositeByteBuf.removeComponent(0);

		Iterator<ByteBuf> iterator = compositeByteBuf.iterator();
		while(iterator.hasNext()){
			System.out.println(iterator.next());
		}


	}
}
