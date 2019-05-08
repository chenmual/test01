package com.chenmual.netty.l_07_thrift;

import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import thrift.generated.Person;
import thrift.generated.PersonService;

public class ThriftClient {
	public static void main(String[] args){
		TTransport transport = new TFramedTransport(new TSocket("127.0.0.1", 8848), 5000);//与服务器的transport保持一致

		TProtocol protocol = new TCompactProtocol(transport);

		PersonService.Client client = new PersonService.Client((protocol));

		try {
			transport.open();
			Person person = client.getPersonByUserName("张三");
			System.out.println(person.getUsername());
			System.out.println(person.getAge());
			System.out.println(person.isMarried());
			System.out.println("----");

			Person person2 = new Person();
			person2.setUsername("李四");
			person2.setAge(22);
			person2.setMarried(false);

			client.savePerson(person2);
		}catch(Exception e){
			e.printStackTrace();
//			throw new RuntimeException(e.getMessage(), e);
		}finally {
			transport.close();
		}
	}
}
