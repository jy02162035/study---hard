package thread.reentrantlock.locktestfinal;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockTest3 {
	public static void main(String[] args) {
		Depot depot = new Depot(100);
		Producer mPro = new Producer(depot);
		Consumer mCos = new Consumer(depot);
		mPro.produce(60);
		mPro.produce(100);
		mCos.consume(90);
		mCos.consume(150);
		mPro.produce(110);
	}
}

class Depot {
	private int capacity; // 仓库总容量
	private int size; // 仓库实际数量
	private Lock lock; // 独占锁
	private Condition fullCondition; // 生产条件
	private Condition emptyCondition; // 消费条件

	public Depot(int capacity) {
		this.capacity = capacity;
		this.size = 0;
		this.lock = new ReentrantLock();
		this.fullCondition = this.lock.newCondition();
		this.emptyCondition = this.lock.newCondition();
	}

	public void produce(int val) {
		lock.lock();
		try {
			// wantProVal: 想要生产的数量
			int wantProVal = val;
			while (wantProVal > 0) {
				while (size >= capacity) {
					System.out.println("仓库已满,生产者等待,消费者开始消费");
					fullCondition.await();
				}

				int actualProVal = (wantProVal + size) > capacity ? (capacity - size) : wantProVal;
				size += actualProVal;
				wantProVal -= actualProVal;

				System.out.printf("%s 要生产(总数: %3d) --> 实际生产数量=%3d, 一次生产后还未生产数量=%3d, 仓库剩余实际数量=%3d\n",
						Thread.currentThread().getName(), val, actualProVal, wantProVal, size);
				// 通知“消费者”可以消费了。
				emptyCondition.signal();
			}
		} catch (InterruptedException e) {
		} finally {
			lock.unlock();
		}
	}

	public void consume(int val) {
		lock.lock();
		try {
			int wantCosVal = val;
			while (wantCosVal > 0) {
				while (size <= 0) {
					System.out.println("仓库已空,消费者等待,生产者开始生产");
					emptyCondition.await();
				}

				int actualCosVal = size < wantCosVal ? size : wantCosVal;
				size -= actualCosVal;
				wantCosVal -= actualCosVal;

				System.out.printf("%s 要消费(总数：%3d) <-- 实际消费数量=%3d, 一次消费后还未消费数量=%3d, 仓库剩余实际数量=%3d\n",
						Thread.currentThread().getName(), val, actualCosVal, wantCosVal, size);

				fullCondition.signal();
			}
		} catch (InterruptedException e) {
		} finally {
			lock.unlock();
		}
	}
}

class Producer {
	private Depot depot;

	public Producer(Depot depot) {
		this.depot = depot;
	}

	public void produce(int val) {
		new Thread() {
			public void run() {
				depot.produce(val);
			}
		}.start();
	}
}

class Consumer {
	private Depot depot;

	public Consumer(Depot depot) {
		this.depot = depot;
	}

	public void consume(int val) {
		new Thread() {
			public void run() {
				depot.consume(val);
			}
		}.start();
	}
}