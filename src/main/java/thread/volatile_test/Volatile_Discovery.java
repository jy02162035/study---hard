package thread.volatile_test;

/**
 * volatile修饰的变量可以保证线程间的可见性
 * 
 * @author pengshun.wu
 *
 */
public class Volatile_Discovery extends Thread {

	private volatile boolean isRunning = true;

	private void setIsRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}

	public void run() {
		System.out.println("进入run方法.......");

		while (isRunning) {
			// ...
		}
		System.out.println("run方法结束");
	}

	public static void main(String[] args) throws InterruptedException {
		Volatile_Discovery t = new Volatile_Discovery();
		t.start();
		Thread.sleep(2000);
		t.setIsRunning(false);
		System.out.println("主线程已经设置isRunning为false");
		Thread.sleep(1000);
		System.out.println(t.isRunning);

	}

}
