package com.chenmual.netty.l_07_thrift;

import org.apache.thrift.TProcessorFactory;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.server.THsHaServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TTransportException;
import thrift.generated.PersonService;

public class ThriftServer{
	public static void main(String[] args) throws TTransportException {
		TNonblockingServerSocket socket = new TNonblockingServerSocket(8899);//非阻塞
		THsHaServer.Args arg = new THsHaServer.Args(socket).minWorkerThreads(2).maxWorkerThreads(4);//高可用server

		PersonService.Processor<PersonServiceImpl> processor = new PersonService.Processor<>(new PersonServiceImpl());

		arg.protocolFactory(new TCompactProtocol.Factory());//面向协议层 压缩字节码(其他的有二进制 json simplejson debug)
		arg.transportFactory(new TFramedTransport.Factory());//传输层 底层以什么形式传(tsocket frame file memery zlib)
		arg.processorFactory(new TProcessorFactory(processor));

		TServer server = new THsHaServer(arg);//half sync half async 半异步处理ios 半同步处理rpc
		//单线程服务用于测试simple/threadpool多线程 线程池阻塞io
		//nonblocking非阻塞式
		System.out.println("thrift server started");
		server.serve();//异步非阻塞死循环
	}
}
