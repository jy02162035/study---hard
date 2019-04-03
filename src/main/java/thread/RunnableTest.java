package thread;

public class RunnableTest {
	// Thread
	// 是类，而Runnable是接口；Thread本身是实现了Runnable接口的类。我们知道“一个类只能有一个父类，但是却能实现多个接口”，因此Runnable具有更好的扩展性。
	// 此外，Runnable还可以用于“资源的共享”。即，多个线程都是基于某一个Runnable对象建立的，它们会共享Runnable对象上的资源。
	// 通常，建议通过“Runnable”实现多线程！
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyRunnable mr = new MyRunnable();

		// 主线程main创建并启动3个子线程，而且这3个子线程都是基于“mt这个Runnable对象”而创建的。
		// 运行结果是这3个子线程一共卖出了10张票。这说明它们是共享了MyThread接口的。
		Thread t1 = new Thread(mr);
		Thread t2 = new Thread(mr);
		Thread t3 = new Thread(mr);
		t1.start();
		t2.start();
		t3.start();
	}

}
