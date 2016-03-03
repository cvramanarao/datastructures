package com.javaassets.training.threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class LockDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ExecutorService executor = Executors.newFixedThreadPool(2);
		final ReentrantLock rl = new ReentrantLock();
		
		class Worker implements Runnable {
			
			private String name;
			
			Worker(String name) {
				this.name = name;
			}

			@Override
			public void run() {
				rl.lock();
				
				try {
					if(rl.isHeldByCurrentThread()){
						System.out.printf("Thread %s has entered its critical section.\n",name);
					}
					System.out.printf("Thread %s is performing work for 2 seconds %n", name);
					
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.printf("Thread %s has finished working. \n", name);
				}  finally {
					rl.unlock();
				}
				
			}
			
		}
		
		executor.execute(new Worker("A"));
		executor.execute(new Worker("B"));
		
		try {
			executor.awaitTermination(5, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		executor.shutdownNow();

	}

}
