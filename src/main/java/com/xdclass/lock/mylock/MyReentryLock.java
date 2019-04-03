package com.xdclass.lock.mylock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class MyReentryLock implements Lock {

	// 锁是否被线程持有
	private boolean isHoldedLock = false;
	// 持有锁的线程
	private Thread holdLockThread = null;
	// 重入次数
	private int reentryCount = 0;

	/**
	 * 同一时刻，能且仅能有一个线程获取到锁， 其他线程，只能等待该线程释放锁之后才能获取到锁
	 */
	@Override
	public synchronized void lock() {
		if (isHoldedLock && Thread.currentThread() != holdLockThread) {
			try {
				// 使用wait必须持有锁：sychronized
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		holdLockThread = Thread.currentThread();
		isHoldedLock = true;
		reentryCount++;
	}

	@Override
	public synchronized void unlock() {
		// 判断当前线程是否是持有锁的线程，是，重入次数减去1，不是就不做处理
		if (Thread.currentThread() == holdLockThread) {
			reentryCount--;
			// 如果当前线程不再持有锁的时候就释放锁
			if (reentryCount == 0) {
				// 使用wait必须持有锁：sychronized
				notify();
				isHoldedLock = false;
			}
		}
	}

	@Override
	public Condition newCondition() {
		return null;
	}

	@Override
	public void lockInterruptibly() throws InterruptedException {

	}

	@Override
	public boolean tryLock() {
		return false;
	}

	@Override
	public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
		return false;
	}
}
