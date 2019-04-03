package com.xdclass.pool;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class LinkedBlockingDequeDemo {

	public static void main(String[] args) throws InterruptedException {
		Runnable myRunnable = new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(2000);
					System.out.println(Thread.currentThread().getName() + " run");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
		};

		ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 8, 5, TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>(10));
		executor.execute(myRunnable);
		executor.execute(myRunnable);
		executor.execute(myRunnable);
		System.out.println("---先开三个---");
		System.out.println("核心线程数" + executor.getCorePoolSize());
		System.out.println("线程池数" + executor.getPoolSize());
		System.out.println("队列任务数" + executor.getQueue().size());
		executor.execute(myRunnable);
		executor.execute(myRunnable);
		executor.execute(myRunnable);
		executor.execute(myRunnable);
		executor.execute(myRunnable);
		executor.execute(myRunnable);
		executor.execute(myRunnable);
		System.out.println("---再开7个---");
		System.out.println("核心线程数" + executor.getCorePoolSize());
		System.out.println("线程池数" + executor.getPoolSize());
		try {
			System.out.println("队列任务数" + executor.getQueue().size());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Thread.sleep(8000);
		System.out.println("----8秒之后----");
		System.out.println("核心线程数" + executor.getCorePoolSize());
		System.out.println("线程池数" + executor.getPoolSize());
		System.out.println("队列任务数" + executor.getQueue().size());
		executor.shutdown();
	}

}
