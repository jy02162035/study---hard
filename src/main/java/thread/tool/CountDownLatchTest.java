package thread.tool;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class CountDownLatchTest {
	private static Random sr = new Random(47);
	private static AtomicInteger result = new AtomicInteger(0);
	private static int threadCount = 10;// 线程数量
	private static CountDownLatch countDown = new CountDownLatch(threadCount);// CountDownLatch

	private static class Parser implements Runnable {
		String name;

		public Parser(String name) {
			this.name = name;
		}

		@Override
		public void run() {
			int sum = 0;
			int seed = Math.abs(sr.nextInt());
			Random r = new Random(47);
			for (int i = 0; i < 100; i++) {
				sum += r.nextInt(seed);
			}
			result.addAndGet(sum);
			System.out.println(name + "线程的解析结果：" + sum);
			countDown.countDown();// 注意这里
		}
	}

	public static void main(String[] args) throws InterruptedException {
		Thread[] threads = new Thread[threadCount];
		for (int i = 0; i < threadCount; i++) {
			threads[i] = new Thread(new Parser("Parser-" + i));
		}
		for (int i = 0; i < threadCount; i++) {
			threads[i].start();
		}
		/*
		 * for(int i=0;i<threadCount;i++){ threads[i].join(); }
		 */
		countDown.await();// 将join改为使用CountDownLatch
		System.out.println("所有线程解析结束！");
		System.out.println("所有线程的解析结果：" + result);
	}
}
