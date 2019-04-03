package thread.bjsxt.concurrent018;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 自定义拒绝策略 
 * 实现RejectedExecutionHandler重写rejectedExecution方法即可
 * 
 * @author pengshun.wu
 *
 */
public class MyRejected implements RejectedExecutionHandler {

	public MyRejected() {
	}

	/**
	 * Runnable r 当前的任务对象
	 * ThreadPoolExecutor executor 执行的线程池
	 */
	@Override
	public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
		System.out.println("自定义处理..");
		System.out.println("当前被拒绝任务为：" + r.toString());

	}

}
