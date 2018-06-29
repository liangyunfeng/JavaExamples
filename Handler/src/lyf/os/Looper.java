package lyf.os;

public class Looper {

	static final ThreadLocal<Looper> sThreadLocal = new ThreadLocal<Looper>();
	private static Looper sMainLooper;
	
	final IMessageQueue mQueue;

	public Looper() {
		//mQueue = new MessageQueue(2);
		//mQueue = new SignalMessageQueue(2);
		mQueue = new LockMessageQueue(2);
		//mQueue = new OptimizedLockMessageQueue(2);
	}

	public static void prepare() {
		if (sThreadLocal.get() != null) {
			throw new RuntimeException(
					"Only one Looper may be created per thread");
		}
		sThreadLocal.set(new Looper());
	}

	public static void prepareMainLooper() {
		prepare();
		synchronized (Looper.class) {
			if (sMainLooper != null) {
				throw new IllegalStateException(
						"The main Looper has already been prepared.");
			}
			sMainLooper = myLooper();
		}
	}

	public static Looper getMainLooper() {
		return sMainLooper;
	}

	public static Looper myLooper() {
		return sThreadLocal.get();
	}

	public static void loop() {
		final Looper me = myLooper();
		if (me == null) {
			throw new RuntimeException(
					"No Looper; Looper.prepare() wasn't called on this thread.");
		}
		for (;;) {
			// 消费Message，如果MessageQueen为null，则等待
			Message message = null;
			try {
				message = me.mQueue.next();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (message != null) {
				message.target.handleMessage(message);
			}
		}
	}
}
