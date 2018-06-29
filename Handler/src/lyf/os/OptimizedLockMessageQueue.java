package lyf.os;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 生产者消费者模式
 */
public class OptimizedLockMessageQueue implements IMessageQueue {
	private final Lock putLock = new ReentrantLock();
	private final Condition notFull = putLock.newCondition();
	private final Lock takeLock = new ReentrantLock();
	private final Condition notEmpty = takeLock.newCondition();
	private Node head; // 队头
	private Node last; // 队尾
	private AtomicInteger count = new AtomicInteger(0); // 记录大小
	private int cap = 10; // 容量，默认为10

	public OptimizedLockMessageQueue(int cap) {
		this.cap = cap;
	}

	@Override
	public Message next() throws InterruptedException {
		Node node;
		int c = -1;
		takeLock.lock();
		try {
			while (count.get() == 0) {
				notEmpty.await();
			}
			node = head;
			head = head.next;
			c = count.getAndDecrement();
			if (c > 0) {
				notEmpty.signal();
			}
		} finally {
			takeLock.unlock();
		}
		if (count.get() < cap) {
			signalNotFull();
		}
		return node.data;
	}

	@Override
	public void enqueueMessage(Message message) throws InterruptedException {
		Node node = new Node(message);
		int c = -1;
		putLock.lock();
		try {
			while (count.get() == cap) {
				notFull.await();
			}
			// 初始状态
			if (head == null && last == null) {
				head = last = node;
			} else {
				last.next = node;
				last = last.next;
			}

			c = count.getAndIncrement();
			if (c < cap) {
				notFull.signal();
			}
		} finally {
			putLock.unlock();
		}
		if (c > 0) {
			signalNotEmpty();
		}
	}

	private void signalNotEmpty() {
		takeLock.lock();
		try {
			notEmpty.signal();
		} finally {
			takeLock.unlock();
		}
	}

	private void signalNotFull() {
		putLock.lock();
		try {
			notFull.signal();
		} finally {
			putLock.unlock();
		}
	}

	static class Node {
		Message data;
		Node next;

		public Node(Message data) {
			this.data = data;
		}
	}
}