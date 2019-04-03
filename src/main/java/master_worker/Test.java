package master_worker;

import java.util.Random;

public class Test {

	public static void main(String[] args) {
		// 当前运行环境活跃的线程数
		System.out.println(Runtime.getRuntime().availableProcessors());
		Master master = new Master(new MyWorker(), 100);
		Random ran = new Random();
		// 创建100个任务交给master
		for (int i = 1; i < 1000; i++) {
			Task task = new Task();
			task.setId(i);
			task.setName("任务" + i);
			task.setPrice(ran.nextInt(1000));
			master.submit(task);
		}

		long start = System.currentTimeMillis();
		// master执行任务
		master.execute();

		// 总结master的执行结果
		while (true) {
			if (master.isComplete()) {
				long costTime = System.currentTimeMillis() - start;
				int ret = master.getResult();
				// 一共100个任务，一个任务耗时0.5秒，一共10个worker一起工作，计算总共耗时应该在5秒多一点
				// 某一次的运行结果【结果是：44410, 执行任务一共耗时：5011】
				System.out.println("结果是：" + ret + ", 执行任务一共耗时：" + costTime);
				break;
			}

		}
	}

}
