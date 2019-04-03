package thread.volatile_test;

import java.util.concurrent.atomic.AtomicInteger;

public class VolatileNoAtomic extends Thread {

	// 10个线程共享一个count,volatile只能保证可见性，但是不能够保证原子性
	// private static volatile int count;

	// 原子操作可以保证方法的最终一致性，但是不能保证执行时期的一致性
	// new AtomicInteger(0): 赋初始值0
	private static AtomicInteger count = new AtomicInteger(0);

	private static void addCount() {
		for (int i = 0; i < 1000; i++) {
			// count++;
			count.incrementAndGet();
		}
		System.out.println(count);
	}

	@Override
	public void run() {
		addCount();
	}

	public static void main(String[] args) {
		VolatileNoAtomic[] arr = new VolatileNoAtomic[10];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = new VolatileNoAtomic();
		}

		for (int i = 0; i < 10; i++) {
			arr[i].start();
		}
	}

}
