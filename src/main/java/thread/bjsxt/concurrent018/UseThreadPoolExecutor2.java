package thread.bjsxt.concurrent018;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 
 * @author pengshun.wu
 *
 */
public class UseThreadPoolExecutor2 implements Runnable {
	private int threadId;

	public UseThreadPoolExecutor2(int threadId) {
		this.threadId = threadId;
	}

	private static AtomicInteger count = new AtomicInteger(0);

	@Override
	public void run() {
		try {
			int temp = count.incrementAndGet();
			System.out.println("线程ID：" + threadId + "  任务" + temp);
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception {
		// System.out.println(Runtime.getRuntime().availableProcessors());
		BlockingQueue<Runnable> queue =
		 new LinkedBlockingQueue<Runnable>(); // 无界列表，都只会用核心线程去执行
//		new ArrayBlockingQueue<Runnable>(10); // 有界列表，参照UseThreadPoolExecutor1的说明
		ExecutorService executor = new ThreadPoolExecutor(5, // core
				10, // max
				120L, // 2fenzhong
				TimeUnit.SECONDS, queue);

		for (int i = 0; i < 20; i++) {
			executor.execute(new UseThreadPoolExecutor2(i));
		}
		Thread.sleep(1000);
		System.out.println("queue size:" + queue.size());

		executor.shutdown();
	}

}
