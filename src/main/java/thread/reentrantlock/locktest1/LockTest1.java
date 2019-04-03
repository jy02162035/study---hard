package thread.reentrantlock.locktest1;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockTest1 {
	public static void main(String[] args) {
		Depot mDepot = new Depot();
		Producer mPro = new Producer(mDepot);
		Consumer mCon = new Consumer(mDepot);
		mPro.produce(60);
		mPro.produce(120);
		mCon.consume(90);
		mCon.consume(150);
		mPro.produce(110);
		// 结果分析：
		// (01) Depot
		// 是个仓库。通过produce()能往仓库中生产货物，通过consume()能消费仓库中的货物。通过独占锁lock实现对仓库的互斥访问：在操作(生产/消费)仓库中货品前，会先通过lock()锁住仓库，操作完之后再通过unlock()解锁。
		// (02) Producer是生产者类。调用Producer中的produce()函数可以新建一个线程往仓库中生产产品。
		// (03) Customer是消费者类。调用Customer中的consume()函数可以新建一个线程消费仓库中的产品。
		// (04) 在主线程main中，我们会新建1个生产者mPro，同时新建1个消费者mCus。它们分别向仓库中生产/消费产品。
		// 根据main中的生产/消费数量，仓库最终剩余的产品应该是50。运行结果是符合我们预期的！
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
		this.lock.lock();
		try {
			size += val;
			System.out.printf("%s produce(%d) --> size=%d\n", Thread.currentThread().getName(), val, size);
		} finally {
			this.lock.unlock();
		}
	}

	public void consume(int val) {
		this.lock.lock();
		try {
			size -= val;
			System.out.printf("%s consume(%d) <-- size=%d\n", Thread.currentThread().getName(), val, size);
		} finally {
			this.lock.unlock();
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
