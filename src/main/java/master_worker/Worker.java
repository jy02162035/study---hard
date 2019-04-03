package master_worker;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public abstract class Worker implements Runnable {
	// 所有任务集合
	private ConcurrentLinkedQueue<Task> workQueue;
	// 处理结果
	private ConcurrentHashMap<String, Object> resultMap;

	@Override
	public void run() {
		while (true) {
			Task inputTask = this.workQueue.poll();
			if (inputTask == null)
				break;
			// 真正的处理每一个业务逻辑(service层dao层等)
			Object outputTask = this.handler(inputTask);
			// 将任务的id当做key，处理结果当做value放入存储处理结果的并发容器中
			this.resultMap.put(String.valueOf(inputTask.getId()), outputTask);
		}
	}

	/**
	 * 实际执行业务的实现，可以将此方法提出，具体业务继承此类重写该方法，灵活运用
	 * @param inputTask
	 * @return
	 */
//	private Object handler(Task inputTask) {
//		Object retVal = null;
//		try {
//			// 处理业务每个耗时3秒。
//			Thread.sleep(500);
//			// 模仿真实业务，将task里的price当做处理结果返回
//			retVal = inputTask.getPrice();
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return retVal;
//	}
	public abstract Object handler(Task inputTask);

	public void setResultMap(ConcurrentHashMap<String, Object> resultMap) {
		this.resultMap = resultMap;
	}

	public void setWorkQueue(ConcurrentLinkedQueue<Task> workQueue) {
		this.workQueue = workQueue;
	}

}
