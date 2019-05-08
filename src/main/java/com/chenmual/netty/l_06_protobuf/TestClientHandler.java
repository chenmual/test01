package com.chenmual.netty.l_06_protobuf;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Random;

public class TestClientHandler extends SimpleChannelInboundHandler<MyDataInfo.MyMessage> {
	@Override
	protected void channelRead0(ChannelHandlerContext ctx,MyDataInfo.MyMessage msg) throws Exception {

	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		MyDataInfo.MyMessage myMessage = null;

		int randomInt = new Random().nextInt(3);
		if(0 == randomInt){
			myMessage = MyDataInfo.MyMessage.newBuilder()
					.setDataType(MyDataInfo.MyMessage.DataType.PersonType)
					.setPerson(MyDataInfo.Person.newBuilder()
							.setName("张三")
							.setAge(20)
							.setAddress("北京")
							.build())
					.build();
		}else if(1 == randomInt){
			myMessage = MyDataInfo.MyMessage.newBuilder()
					.setDataType(MyDataInfo.MyMessage.DataType.DogType)
					.setDog(MyDataInfo.Dog.newBuilder()
							.setName("汪星人")
							.setAge(3)
							.build())
					.build();
		}else{
			myMessage = MyDataInfo.MyMessage.newBuilder()
					.setDataType(MyDataInfo.MyMessage.DataType.CatType)
					.setCat(MyDataInfo.Cat.newBuilder()
							.setName("喵星人")
							.setCity("北京")
							.build())
					.build();
		}

		System.out.println("send" + myMessage);
		ctx.channel().writeAndFlush(myMessage);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx,Throwable cause) throws Exception {
		System.out.println("断开");
	}
}
