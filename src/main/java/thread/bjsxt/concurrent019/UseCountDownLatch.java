package thread.bjsxt.concurrent019;

import java.util.concurrent.CountDownLatch;

/**
 * 定义一个2个数的CountDownLatch
 * 线程A执行countDown.await()之后就会阻塞，后续代码不会执行
 * 另外2个线程【线程B和C处理】一顿处理完之后调用countDown.countDown()之后，线程A会接着执行
 * 
 * @author pengshun.wu
 *
 */
public class UseCountDownLatch {

	public static void main(String[] args) {

		final CountDownLatch countDown = new CountDownLatch(2);

		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					System.out.println("进入线程t1" + "等待其他线程处理完成...");
					countDown.await();
					System.out.println("t1线程继续执行...");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, "t1");

		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					System.out.println("t2线程进行初始化操作...");
					Thread.sleep(3000);
					System.out.println("t2线程初始化完毕，通知t1线程继续...");
					countDown.countDown();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		Thread t3 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					System.out.println("t3线程进行初始化操作...");
					Thread.sleep(4000);
					System.out.println("t3线程初始化完毕，通知t1线程继续...");
					countDown.countDown();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});

		t1.start();
		t2.start();
		t3.start();

	}
}
