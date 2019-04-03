package master_worker;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Master {

	// 1.应该有一个承装所有并发任务的集合
	private ConcurrentLinkedQueue<Task> workQueue = new ConcurrentLinkedQueue<Task>();

	// 2.使用HashMap承装所有的执行任务的Worker对象
	private HashMap<String, Thread> workers = new HashMap<String, Thread>();

	// 3.使用一个容器去存储每个worker并发执行的结果
	private ConcurrentHashMap<String, Object> resultMap = new ConcurrentHashMap<String, Object>();

	// 4.构造方法
	public Master(Worker worker, int workerCount) {
		// 每一个worker对象都需要有Master里的所有任务集合的引用workQueue
		// 每一个worker对象都需要有Master里的所有并发结果的引用resultMap
		worker.setWorkQueue(this.workQueue);
		worker.setResultMap(this.resultMap);
		for (int i = 0; i < workerCount; i++) {
			this.workers.put("子节点" + i, new Thread(worker));
		}
	}

	// 5.有100个job进入承装任务的集合
	public void submit(Task task) {
		this.workQueue.add(task);
	}

	// 6.需要一个总控的方法---启动应用程序让所有的worker工作
	public void execute() {
		for (Map.Entry<String, Thread> me : this.workers.entrySet()) {
			me.getValue().start();
		}
	}
	
	// 7.判断线程是否执行完毕
	public boolean isComplete() {
		// 循环所有workers，如果有任意一个线程的状态不是停止就返回false
		for (Map.Entry<String, Thread> me : this.workers.entrySet()) {
			if (me.getValue().getState() != Thread.State.TERMINATED) {
				return false;
			}
		}
		return true;
	}
	
	// 8.返回执行结果
	public int getResult() {
		int ret = 0;
		// 模拟真实业务，这里只是进行了所有结果的累加操作
		for (Map.Entry<String, Object> me : resultMap.entrySet()) {
			ret += (Integer)me.getValue();
		}
		return ret;
	}

}
