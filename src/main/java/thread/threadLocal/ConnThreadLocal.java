package thread.threadLocal;

/**
 * 线程本地变量，每个线程保存自己的值，线程之间不做共享
 * @author 吴鹏顺
 *
 */
public class ConnThreadLocal {
	
	public static ThreadLocal<String> th = new ThreadLocal<String>();
	public static String str = "";
	
	public void setTh(String value){
		th.set(value);
	}
	public void getTh(){
		System.out.println(Thread.currentThread().getName() + ":" + this.th.get());
	}
	
	public static void main(String[] args) throws InterruptedException {
		
		final ConnThreadLocal ct = new ConnThreadLocal();
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				ct.setTh("张三");
				ct.getTh();
				ct.str = "wupengshun";
			}
		}, "t1");
		
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(1000);
//					ct.setTh("李四");
					ct.getTh();
					System.out.println(ct.str);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, "t2");
		
		t1.start();
		t2.start();
	}
	
}
