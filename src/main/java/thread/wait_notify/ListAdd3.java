package thread.wait_notify;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
/**
 * @author alienware
 *
 */
public class ListAdd3 {
	private volatile static List list = new ArrayList();	
	
	public void add(){
		list.add("bjsxt");
	}
	public int size(){
		return list.size();
	}
	
	public static void main(String[] args) {
		
		final ListAdd3 list2 = new ListAdd3();
//		final Object lock = new Object();
		final CountDownLatch countDownLatch = new CountDownLatch(1); // 1代表进行1次countDown,就会唤醒别的线程
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
//					synchronized (lock) {
						System.out.println("t1启动..");
						for(int i = 0; i <10; i++){
							list2.add();
							System.out.println("当前线程：" + Thread.currentThread().getName() + "添加了一个元素..");
							Thread.sleep(500);
							if(list2.size() == 5){
								System.out.println("已经发出通知..");
								// 会立即唤醒别的线程去分配cpu
								countDownLatch.countDown();
//								lock.notify();
							}
						}						
//					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
		}, "t1");
		
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
//				synchronized (lock) {
					System.out.println("t2启动..");
					if(list2.size() != 5){
						try {
							countDownLatch.await();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
//						try {
//							lock.wait();
//						} catch (InterruptedException e) {
//							e.printStackTrace();
//						}
					}
//					try {
//						Thread.sleep(2000);
//					} catch (InterruptedException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
					System.out.println("当前线程：" + Thread.currentThread().getName() + "收到通知线程停止..");
					throw new RuntimeException();
//				}
			}
		}, "t2");	
		t2.start();
		t1.start();
		
	}
	
}
