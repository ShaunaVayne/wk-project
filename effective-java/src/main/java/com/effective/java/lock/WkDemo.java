package com.effective.java.lock;

/**
 * @Author: WangKun
 * @Description:
 * @Date: Created in 2020/1/5 9:31 PM
 * @ProjectName: wk-project
 * @Version: 1.0.0
 */
public class WkDemo {

	private int i = 0;
	WkLock wkLock = new WkLock();
	public void add(){
		try {
			wkLock.lock();
			i++;
		}finally {
			wkLock.unlock();
		}
	}

	public static void main(String[] args) throws InterruptedException {
		WkDemo wkDemo = new WkDemo();
		for(int j = 0; j < 6; j++){
			Runnable runnable = new Runnable() {
				@Override
				public void run() {
					for (int j = 0; j < 10000; j++)
					wkDemo.add();
				}
			};
			Thread thread = new Thread(runnable);
			thread.start();
		}
		Thread.sleep(3000l);
		System.out.println(wkDemo.i);
	}
}
