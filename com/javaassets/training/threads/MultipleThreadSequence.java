package com.javaassets.training.threads;

import java.util.concurrent.CountDownLatch;

public class MultipleThreadSequence {
	
	public static void main(String[] args){
		System.out.println("main()-->");
		
		CountDownLatch latch = new CountDownLatch(3);
		
		Object lock1 = new Object();
		
		Object lock2 = new Object();
		
		Object lock3 = new Object();
		
		Thread t1 = new Thread(new Runnable(){

			@Override
			public void run() {
				
				try {
					for(int i=1; i<=20;i=i+3) {
						
						System.out.print(i+", ");
						synchronized(lock1) {
							lock1.notify();
						}
						
						synchronized(lock3) {
							try {
								lock3.wait();
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
				} finally {
					latch.countDown();
				}
			}
			
		});
		
		Thread t2 = new Thread(new Runnable(){

			@Override
			public void run() {
				try {
				
					for(int i=2; i<=20;i=i+3) {
						synchronized(lock1) {
							lock1.wait();
						}
						System.out.print(i+", ");
						synchronized(lock2) {
							lock2.notify();
						}
						
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					latch.countDown();
				}
			}
		});
		
		Thread t3 = new Thread(new Runnable(){

			@Override
			public void run() {
				try {
					for(int i=3; i<=20;i=i+3) {
						synchronized(lock2) {
							try {
								lock2.wait();
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						System.out.print(i+", ");
						
						synchronized(lock3) {
							lock3.notify();
						}
						
					}
				} finally {
					latch.countDown();
				}
			}
		});
		
		t1.start();
		
		t2.start();
		
		t3.start();
		
		try {
			latch.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println();
		System.out.println("<--- main()");
	}

}
