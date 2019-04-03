package master_worker;

/**
 * 具体业务的各自实现类
 * @author 吴鹏顺
 *
 */
public class MyWorker extends Worker {
	
	public Object handler(Task inputTask) {
		Object retVal = null;
		try {
			// 处理业务每个耗时3秒。
			Thread.sleep(500);
			// 模仿真实业务，将task里的price当做处理结果返回
			retVal = inputTask.getPrice();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retVal;
	}
}
