package thread.daemon;

/**
 *  * Hello world!  *  
 */
public class MyDaemonTest {

	public static void main(String[] args) {
		Thread thread1 = new Thread(new Runnable() {
			public void run() {
				while (true) {
					try {
						Thread currentthread1 = Thread.currentThread();
						System.out.println("守护线程" + currentthread1.getName() + "心跳一次");
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		thread1.setDaemon(true);// 将该线程设置为守护线程
		thread1.start();

//		Thread thread2 = new Thread(new Runnable() {
//			public void run() {
//				while (true) {
//					try {
//						Thread currentthread2 = Thread.currentThread();
//						System.out.println("用户线程" + currentthread2.getName() + "心跳一次");
//						Thread.sleep(1000);
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
//				}
//			}
//		});
//		thread2.start();
		try {
			Thread.sleep(10000);
			Thread currentthread = Thread.currentThread();
			System.out.println("主线程" + currentthread.getName() + "退出！");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}