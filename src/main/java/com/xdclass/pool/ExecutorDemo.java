package com.xdclass.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Executor 框架Demo
 */
public class ExecutorDemo {

	public static void main(String[] args) {
		ExecutorService executorService = Executors.newCachedThreadPool();
		// 创建固定大小的线程池
		ExecutorService executorService1 = Executors.newFixedThreadPool(2);
		ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
		ExecutorService executorService2 = Executors.newWorkStealingPool();
		ExecutorService executorService3 = Executors.newSingleThreadExecutor();
		ScheduledExecutorService scheduledExecutorService1 = Executors.newSingleThreadScheduledExecutor();

		// submit要处理的任务
//		executorService.submit(() -> {
//			System.out.println(Thread.currentThread().getName());
//		});

		ExecutorDemo executor = new ExecutorDemo();
//		executor.fixEcecutor();

//		executor.cacheEcecutor();
		
		executor.scheduledEcecutor();
	}

	/**
	 * 创建一个定长线程池(只有核心线程，并且不会被回收，新来线程会排队等待，直到有空闲线程)，可控制线程最大并发数，超出的线程会在队列中等待
	 */
	private void fixEcecutor() {
		//
		ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
		for (int i = 0; i < 15; i++) {
			final String name = "第" + i + "个线程";
			fixedThreadPool.execute(new Runnable() {
				@Override
				public void run() {
					System.out.println(name + "0s");
					try {
						Thread.sleep(1000);
//						System.out.println(name + "1s");
//						Thread.sleep(1000);
//						System.out.println(name + "2s");
//						Thread.sleep(1000);
//						System.out.println(name + "3s");
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});

		}
		fixedThreadPool.shutdown();
	}

	/**
	 * 线程数不定最大为Integer.MAX_VALUE，只有非核心线程数。
	 * 超时机制：空闲线程超过60秒会被回收
	 * 运行原理：当池中的线程都活跃，则会创建新的线程，否则会利用空闲未被回收的线程来处理任务
	 * 优点：任何线程都会被立即执行
	 * 使用场景：大量任务并且每个任务都很短
	 * 
	 */
	private void cacheEcecutor() {
		ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
		for (int i = 0; i < 15; i++) {
			final String name = "第" + i + "个线程";
			cachedThreadPool.execute(new Runnable() {
				@Override
				public void run() {
					System.out.println(name + "0s");
					try {
						Thread.sleep(1000);
//						System.out.println(name + "1s");
//						Thread.sleep(1000);
//						System.out.println(name + "2s");
//						Thread.sleep(1000);
//						System.out.println(name + "3s");
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});

		}
		cachedThreadPool.shutdown();
	}

	/**
	 * 创建一个定长线程池，支持定时及周期性任务执行
	 */
	private void scheduledEcecutor() {
		// 核心线程数5，非核心线程数Integer.MAX_VALUE
		ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(2);
		for (int i = 0; i < 3; i++) {
			final String name = "第" + i + "个线程";
//			// 2秒后执行任务
//            scheduledThreadPool.schedule(new Runnable() {
//                @Override
//                public void run() {
//                    System.out.println(name);
//                }
//            }, 2, TimeUnit.SECONDS);
            
            // 2秒后执行任务，周期性的每隔1秒执行一次
            scheduledThreadPool.scheduleAtFixedRate(()->{
            	System.out.println(name);
            	}, 2, 1, TimeUnit.SECONDS);
		}
//		scheduledThreadPool.shutdown();
	}
}
