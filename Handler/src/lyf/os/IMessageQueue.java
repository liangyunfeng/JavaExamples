package lyf.os;

public interface IMessageQueue {
	Message next() throws InterruptedException;
    void enqueueMessage(Message message) throws InterruptedException;
}
