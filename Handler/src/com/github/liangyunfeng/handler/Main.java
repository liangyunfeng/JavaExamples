package com.github.liangyunfeng.handler;

import java.util.Random;

import lyf.os.Handler;
import lyf.os.Looper;
import lyf.os.Message;

/**
 * https://www.jianshu.com/p/25a05bf42e05
 * https://blog.csdn.net/c10wtiybq1ye3/article/details/80809708
 */
public class Main {
	public static void main(String[] args) {
		MainThread mainThread = new MainThread();
		mainThread.start();
		// 确保mainLooper构建完成
		while (Looper.getMainLooper() == null) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		Handler handler = new Handler(Looper.getMainLooper()) {
			@Override
			public void handleMessage(Message msg) {
				System.out.println("execute in : "
						+ Thread.currentThread().getName());
				switch (msg.getCode()) {
				case 0:
					System.out.println("code 0 : " + msg.getMessage());
					break;
				case 1:
					System.out.println("code 1 : " + msg.getMessage());
					break;
				default:
					System.out.println("other code : " + msg.getMessage());
				}
			}
		};

		Message message1 = new Message(0, "I am the first message!");
		WorkThread workThread1 = new WorkThread(handler, message1);

		Message message2 = new Message(1, "I am the second message!");
		WorkThread workThread2 = new WorkThread(handler, message2);

		Message message3 = new Message(34, "I am the other message!");
		WorkThread workThread3 = new WorkThread(handler, message3);

		workThread1.start();
		workThread2.start();
		workThread3.start();
	}

	/** 模拟工作线程 **/
	public static class WorkThread extends Thread {
		private Handler handler;
		private Message message;

		public WorkThread(Handler handler, Message message) {
			setName("WorkThread");
			this.handler = handler;
			this.message = message;
		}

		@Override
		public void run() {
			super.run();
			// 模拟耗时操作
			Random random = new Random();
			try {
				Thread.sleep(random.nextInt(10) * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			// 任务执行完，sendMessage
			handler.sendMessage(message);
		}
	}

	/** 模拟主线程 */
	public static class MainThread extends Thread {
		public MainThread() {
			setName("MainThread");
		}

		@Override
		public void run() {
			super.run();
			// 这里是不是与系统的调用一样，哈哈
			Looper.prepareMainLooper();
			System.out.println(getName() + " the looper is prepared");
			Looper.loop();
		}
	}
}
