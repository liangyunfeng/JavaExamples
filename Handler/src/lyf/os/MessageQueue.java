package lyf.os;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 生产者消费者模式
 */
public class MessageQueue implements IMessageQueue {

	private final BlockingQueue<Message> queue;
	
	public MessageQueue(int cap) {
		queue = new LinkedBlockingQueue<>(cap);
	}
	
	@Override
	public Message next() throws InterruptedException {
		return queue.take();
	}

	@Override
	public void enqueueMessage(Message message) throws InterruptedException {
		try {
            queue.put(message);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
	}

}
