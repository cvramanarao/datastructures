package com.javaassets.training.threads;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Shared s = new Shared();
		new Producer(s).start();
		new Consumer(s).start();

	}
	
	class Shared {
		private volatile char c;
		private volatile boolean available;
		private final Lock lock;
		private final Condition condition;
		
		Shared() {
			// TODO Auto-generated constructor stub
			c = '\u0000';
			available = false;
			lock = new ReentrantLock();
			condition = lock.newCondition();
		}
		
		Lock getLock(){
			return lock;
		}
		
		char getSharedChar(){
			lock.lock();
			try {
				while(!available){
					try {
						condition.await();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				available = false;
				condition.signal();
			} finally  {
				lock.unlock();
				return c;
			}	
		}
		
		void setSharedChar(char c){
			lock.lock();
			
			try {
				while(available){
					try {
						condition.await();
					} catch(InterruptedException e){
						e.printStackTrace();
					}
				}
				
				this.c = c;
				available = true;
				condition.signal();
			} finally {
				// TODO Auto-generated catch block
				lock.unlock();
			}
		}
	}
	
	
	class Producer extends Thread {
		private final Lock l;
		
	}

}
