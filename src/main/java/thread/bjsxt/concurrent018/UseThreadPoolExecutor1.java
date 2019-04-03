package thread.bjsxt.concurrent018;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor.DiscardOldestPolicy;
import java.util.concurrent.TimeUnit;

/**
 * ArrayBlockingQueue
 * LinkedBlockingQueue
 * 
 * @author pengshun.wu
 *
 */
public class UseThreadPoolExecutor1 {

	public static void main(String[] args) {
		/**
		 * 在使用有界队列时，若有新的任务需要执行，如果线程池实际线程数小于corePoolSize，则优先创建线程，
		 * 若大于corePoolSize，则会将任务加入队列， 若队列已满，则在总线程数不大于maximumPoolSize的前提下，创建新的线程，
		 * 若线程数大于maximumPoolSize，则执行拒绝策略。或其他自定义方式。
		 * 
		 */
		ThreadPoolExecutor pool = new ThreadPoolExecutor(1, // coreSize
				2, // MaxSize
				60, // 60
				TimeUnit.SECONDS, 
				new ArrayBlockingQueue<Runnable>(3) // 指定一种队列 // （有界队列）
//		 new LinkedBlockingQueue<Runnable>() // 指定一种队列  // （无界队列）
		, new MyRejected() // 自定义拒绝策略
//		 , new DiscardOldestPolicy() // JDK自带的拒绝策略：丢弃最老的一个任务，强制提交新的任务
		);
		
		// JDK自带了4中拒绝策略
		// new DiscardOldestPolicy()

		// 一共7个线程
		// 执行顺序
		// 1 先去用核心线程去创建----任务1执行
		// 2 将多余线程入队列----任务2,3,4入队列
		// 3 如果队列满，并且要执行线程数小于等于最大线程数，创建新的线程执行 ----任务5被非核心线程(最大数2-核心数1)创建并执行
		// 4 如果队列满，对于要执行线程多余最大线程数的线程，执行拒绝策略 ---任务6,7,8被拒绝执行，执行拒绝策略
		MyTask mt1 = new MyTask(1, "任务1");
		MyTask mt2 = new MyTask(2, "任务2");
		MyTask mt3 = new MyTask(3, "任务3");
		MyTask mt4 = new MyTask(4, "任务4");
		MyTask mt5 = new MyTask(5, "任务5");
		MyTask mt6 = new MyTask(6, "任务6");
		MyTask mt7 = new MyTask(7, "任务7");

		pool.execute(mt1);
		pool.execute(mt2);
		pool.execute(mt3);
		pool.execute(mt4);
		pool.execute(mt5);
		pool.execute(mt6);
		pool.execute(mt7);

		pool.shutdown();

	}
}
