package com.bmpl.chatapp.networking;

//public class ThreadDemo implements Runnable {
public class ThreadDemo extends Thread {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		for(int i = 0; i < 5; i++) {
			System.out.println("I is : " + i + " "+ Thread.currentThread());
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ThreadDemo job = new ThreadDemo();
		job.start();
		
//		Thread thread = new Thread(job);
//		thread.start();   // internally it will call run()
		
		for(int i = 0; i < 5; i++) {
			System.out.println("Main : " + i + " " + Thread.currentThread());
		}
	}

}
