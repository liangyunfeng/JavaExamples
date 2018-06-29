package lyf.os;

public abstract class Handler {

	final Looper mLooper;
	final IMessageQueue mQueue;

	public Handler() {
		mLooper = Looper.myLooper();
		mQueue = mLooper.mQueue;
	}

	public Handler(Looper looper) {
		mLooper = looper;
		mQueue = mLooper.mQueue;
	}
	
	/*public Handler(IMessageQueue queue) {
		mQueue = queue;
	}*/

	public void sendMessage(Message message) {
		// ָ������Message��Handler������ص�
		message.target = this;
		try {
			mQueue.enqueueMessage(message);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public abstract void handleMessage(Message msg);
}
