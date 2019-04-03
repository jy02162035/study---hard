package thread.wait_notify;

// WaitTest.java的源码
/**
 * wait释放锁，notify不释放锁
 * wait和notify必须配合synchronized使用
 * @author 吴鹏顺
 *
 */
class ThreadA extends Thread {

	public ThreadA(String name) {
		super(name);
	}

	public void run() {
		// “线程t1”运行之后，通过synchronized(this)获取“当前对象的锁”；接着调用notify()唤醒“当前对象上的等待线程”，也就是唤醒“主线程”。唤醒的同时不会释放锁，等待当前线程所有代码执行完后才会释放锁
		synchronized (this) {
			System.out.println(Thread.currentThread().getName() + " call notify()");
			// 唤醒当前对象上的等待线程；notify是唤醒单个线程，notifyAll是唤醒所有等待线程
			notify();
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// 此时等待5秒后会执行，执行完后才会释放t1的锁，回到主线程，充分说明notify只是唤醒别的线程进入等待状态，而不会释放当前线程的锁
			System.out.println(Thread.currentThread().getName() + " after notify()");
		}
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + " out synchronized after notify()");
	}
}

public class WaitTest {

	public static void main(String[] args) {
		// (02-1)“主线程”通过 new ThreadA("t1") 新建“线程t1”。
		ThreadA t1 = new ThreadA("t1");

		// (02-2)通过synchronized(t1)让main获取“t1对象的同步锁”
		// 调用t1.wait()，notify()和notifyAll()的线程在调用这些方法前必须"拥有"对象t1的锁。
		synchronized (t1) {
			try {
				// (02-3)然后调用t1.start()启动“线程t1”。
				System.out.println(Thread.currentThread().getName() + " start t1");
				t1.start();

				// 主线程等待t1通过notify()唤醒。
				//  (03-1)“主线程”执行t1.wait() 释放“t1对象的锁”并且进入“等待(阻塞)状态”。等待t1对象上的线程通过notify() 或 notifyAll()将其唤醒。
				System.out.println(Thread.currentThread().getName() + " wait()");
				// wait的作用是1：让当前线程进入等待状态，2：让当前线程释放它所持有的所有的锁
				t1.wait();

				System.out.println(Thread.currentThread().getName() + " continue");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}