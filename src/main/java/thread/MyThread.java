package thread;

public class MyThread extends Thread {
	private int ticket = 10;
	public void run() {
		for (int i = 0; i < 20; i++) {
			if (ticket > 0) {
				System.out.println(this.getName() + "sell ticket: " + this.ticket--);
//				System.out.println(Thread.currentThread().getName() + "sell ticket: " + this.ticket--);
			}
		}
		
	}
}
