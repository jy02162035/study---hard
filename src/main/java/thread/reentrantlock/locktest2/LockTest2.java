package thread.reentrantlock.locktest2;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockTest2 {
	public static void main(String[] args) {
		Depot mDepot = new Depot();
		Producer mPro = new Producer(mDepot);
		Consumer mCon = new Consumer(mDepot);
		mPro.produce(60);
		mPro.produce(120);
		mCon.consume(90);
		mCon.consume(150);
		mPro.produce(110);
		// 仓库中最终剩余的产品是-60，而不是我们期望的50。原因是我们没有实现对仓库的互斥访问。
	}
}

class Depot {
	// 仓库的实际数量
	private int size;
	// 独占锁
	private Lock lock;

	public Depot() {
		this.size = 0;
		this.lock = new ReentrantLock();
	}

	public void produce(int val) {
		// this.lock.lock();
		try {
			size += val;
			System.out.printf("%s produce(%d) --> size=%d\n", Thread.currentThread().getName(), val, size);
		} finally {
			// this.lock.unlock();
		}
	}

	public void consume(int val) {
		// this.lock.lock();
		try {
			size -= val;
			System.out.printf("%s consume(%d) <-- size=%d\n", Thread.currentThread().getName(), val, size);
		} finally {
			// this.lock.unlock();
		}
	}
}

// 生产者
class Producer {
	private Depot depot;

	public Producer(Depot depot) {
		this.depot = depot;
	}

	// 生产产品：新建一个线程向仓库中生产产品。
	public void produce(final int val) {
		new Thread() {
			public void run() {
				depot.produce(val);
			}
		}.start();
	}
}

// 消费者
class Consumer {
	private Depot depot;

	public Consumer(Depot depot) {
		this.depot = depot;
	}

	// 消费产品：新建一个线程从仓库中消费产品。
	public void consume(final int val) {
		new Thread() {
			public void run() {
				depot.consume(val);
			}
		}.start();
	}
}
