package lyf.os;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 生产者消费者模式
 */
public class SignalMessageQueue implements IMessageQueue {

	private Queue<Message> queue;
	private final AtomicInteger integer = new AtomicInteger(0);
	private volatile int count;
	private final Object BUFFER_LOCK = new Object();

	public SignalMessageQueue(int cap) {
		this.count = cap;
		queue = new LinkedList<>();
	}

	@Override
	public Message next() throws InterruptedException {
		synchronized (BUFFER_LOCK) {
			while (queue.size() == 0) {
				BUFFER_LOCK.wait();
			}
			Message message = queue.poll();
			BUFFER_LOCK.notifyAll();
			return message;
		}
	}

	@Override
	public void enqueueMessage(Message message) throws InterruptedException {
		synchronized (BUFFER_LOCK) {
			while (queue.size() == count) {
				BUFFER_LOCK.wait();
			}
			queue.offer(message);
			BUFFER_LOCK.notifyAll();
		}
	}
}