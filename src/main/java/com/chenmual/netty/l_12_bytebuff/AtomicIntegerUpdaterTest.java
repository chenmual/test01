package com.chenmual.netty.l_12_bytebuff;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

public class AtomicIntegerUpdaterTest {
	public static void main(String[] args){
		Person person = new Person();
		for(int i = 0; i < 10; i++){
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						Thread.sleep(200);
					} catch(InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(person.age++);
				}
			}).start();
		}

		AtomicIntegerFieldUpdater<Person> atomicIntegerFieldUpdater = AtomicIntegerFieldUpdater.newUpdater(Person.class, "age");
		for(int i = 0 ; i < 10; i++){
			new Thread(()->{
				try {
					Thread.sleep(20);
				} catch(InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(atomicIntegerFieldUpdater.getAndIncrement(person));
			}).start();
		}
	}
}
class Person{
	volatile int age = 1;
}
