package com.xdclass.pool;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池Demo
 */
public class ThreadPoolDemo {

	public static void main(String[] args) throws ExecutionException, InterruptedException {
		// 阻塞队列
		LinkedBlockingQueue<Runnable> objects = new LinkedBlockingQueue<>(10);

//		for (int i = 0; i < 100; i++) {
//			objects.put(() -> {
//				System.out.println(Thread.currentThread().getName());
//			});
//		}

		ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 20, 3L, TimeUnit.SECONDS, objects,
				new CustomPolicy());
		Future<String> submit = null;
		for (int i = 0; i < 100; i++) {
//			try {
//				Thread.sleep(1000L);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
			threadPoolExecutor.submit(() -> {
				System.out.println(Thread.currentThread().getName());
			});
		}
		threadPoolExecutor.shutdown();

		// // 启动所有核心线程
		// threadPoolExecutor.prestartAllCoreThreads();
		// Future<String> submit = null;
		// for (int i = 0; i < 18; i++) {
		// threadPoolExecutor.submit(()->{
		// try {
		// Thread.sleep(1000L);
		// } catch (InterruptedException e) {
		// e.printStackTrace();
		// }
		// System.out.println(Thread.currentThread().getName());
		// // 获取线程活跃数量
		// System.out.println(threadPoolExecutor.getActiveCount());
		// });
		// }
		//
		// for (int i = 0; i < 100; i++) {
		// System.out.println(submit.get());
		// }
	}
}
