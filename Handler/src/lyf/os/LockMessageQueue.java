package lyf.os;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 生产者消费者模式
 */
public class LockMessageQueue implements IMessageQueue {
	private final Queue<Message> queue;
	private int cap = 0;
	private final Lock lock = new ReentrantLock();
	private final Condition BUFFER_CONDITION = lock.newCondition();

	public LockMessageQueue(int cap) {
		this.cap = cap;
		queue = new LinkedList<>();
	}

	@Override
	public Message next() throws InterruptedException {
		try {
			lock.lock();
			while (queue.size() == 0) {
				BUFFER_CONDITION.await();
			}
			Message message = queue.poll();
			BUFFER_CONDITION.signalAll();
			return message;
		} finally {
			lock.unlock();
		}
	}

	@Override
	public void enqueueMessage(Message message) throws InterruptedException {
		try {
			lock.lock();
			while (queue.size() == cap) {
				BUFFER_CONDITION.await();
			}
			queue.offer(message);
			BUFFER_CONDITION.signalAll();
		} finally {
			lock.unlock();
		}
	}
}