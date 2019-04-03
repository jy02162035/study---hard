package thread;

public class MyRunnable implements Runnable {
	// 将ticket修改成volatile。这样保证了，每个线程修改了ticket之后，其他线程都能读取到最新的ticket值。
	private volatile int ticket = 100;

	@Override
	// 给run()方法添加synchronized同步关键字。这保证了多个线程对是同一个MyThread对象的run()是互斥操作的。
	// 加入synchronized之后，只有thread-0在工作。
	// 在主线程中创建了t1,t2和t3共三个线程，它们共用一个MyThread任务对象。thread-0启动之后，一直占着"MyThread同步锁"，
	// 而一个对象有且只有一个同步锁；因此，只有thread-0在工作。
	public synchronized void run() {
//		synchronized (this) {
			// TODO Auto-generated method stub
			for (int i = 0; i < 200; i++) {
				if (ticket > 0) {
					System.out.println(Thread.currentThread().getName() + "sell ticket: " + this.ticket--);
				}
			}
//		}

	}

}
