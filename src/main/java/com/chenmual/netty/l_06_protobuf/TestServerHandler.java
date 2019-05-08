package com.chenmual.netty.l_06_protobuf;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class TestServerHandler extends SimpleChannelInboundHandler<MyDataInfo.MyMessage> {
	@Override
	protected void channelRead0(ChannelHandlerContext ctx,MyDataInfo.MyMessage msg) throws Exception {
		System.out.println("get");
		MyDataInfo.MyMessage.DataType dataType = msg.getDataType();
		if(dataType == MyDataInfo.MyMessage.DataType.PersonType){
			MyDataInfo.Person person = msg.getPerson();
			System.out.println(person.getName());
			System.out.println(person.getAge());
			System.out.println(person.getAddress());
		}else if(dataType == MyDataInfo.MyMessage.DataType.DogType){
			MyDataInfo.Dog dog = msg.getDog();
			System.out.println(dog.getName());
			System.out.println(dog.getAge());
		}else{
			MyDataInfo.Cat cat = msg.getCat();
			System.out.println(cat.getName());
			System.out.println(cat.getCity());
		}
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx,Throwable cause) throws Exception {
		System.out.println("断开");
	}
}