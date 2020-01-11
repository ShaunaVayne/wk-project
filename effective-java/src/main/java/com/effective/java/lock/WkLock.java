package com.effective.java.lock;

import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;

/**
 * @Author: WangKun
 * @Description:
 * @Date: Created in 2020/1/5 9:03 PM
 * @ProjectName: wk-project
 * @Version: 1.0.0
 */
public class WkLock implements Lock {

	//令牌, 记录当前线程
	AtomicReference owners = new AtomicReference<Thread>();
	//线程安全的队列,linked(存放没有抢到令牌的线程)
	LinkedBlockingDeque<Thread> waiters = new LinkedBlockingDeque<>();

	@Override
	public void lock() {
		// CAS机制来确保原子性, 当前为空则可以拿到令牌权限
		while (!owners.compareAndSet(null,Thread.currentThread())){
			// 加入等待列表
			waiters.add(Thread.currentThread());
			// 阻塞
			LockSupport.park();// 可以让当前线程阻塞,等待
			waiters.remove(Thread.currentThread());// 为了没有内存泄露
		}
	}

	@Override
	public void unlock() {
		// 多线程操作, CAS机制
		if (owners.compareAndSet(Thread.currentThread(),null)){
			// 要去释放被阻塞的线程
			for (Object o : waiters.toArray()) {
				Thread next = (Thread) o;// 转成线程, 阻塞线程
				// 释放
				LockSupport.unpark(next);
			}
		}
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

	@Override
	public Condition newCondition() {
		return null;
	}
}
