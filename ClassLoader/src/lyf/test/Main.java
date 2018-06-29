package lyf.test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import lyf.custom.disk.DiskClassLoader;
import lyf.custom.encrypt.DeClassLoader;
import lyf.custom.encrypt.FileUtils;

public class Main {

	public static void main(String[] args) {
		// 测试Disk ClassLoader
		// testDisk();

		// 测试加密/解密 ClassLoader
		// FileUtils.test("E:\\lib\\Test.class");
		// testEncrypt();

		testContext();
	}

	/**
	 * 假设我们需要一个自定义的classloader,默认加载路径为D:\lib下的jar包和资源。
	 * 
	 * 我们写编写一个测试用的类文件，Test.java
	 * 
	 * 然后将它编译过年class文件Test.class放到E:\lib这个路径下。
	 */
	public static void testDisk() {
		// 创建自定义classloader对象。
		DiskClassLoader diskLoader = new DiskClassLoader("E:\\lib");
		try {
			// 加载class文件
			Class<?> c = diskLoader.loadClass("com.lyf.test.Test");

			if (c != null) {
				try {
					Object obj = c.newInstance();
					Method method = c.getDeclaredMethod("say", null);
					// 通过反射调用Test类的say方法
					method.invoke(obj, null);
				} catch (InstantiationException | IllegalAccessException
						| NoSuchMethodException | SecurityException
						| IllegalArgumentException | InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 自定义ClassLoader还能做什么？
	 * 
	 * 突破了JDK系统内置加载路径的限制之后，我们就可以编写自定义ClassLoader，然后剩下的就叫给开发者你自己了。
	 * 你可以按照自己的意愿进行业务的定制，将ClassLoader玩出花样来。
	 * 
	 * 玩出花之Class解密类加载器 常见的用法是将Class文件按照某种加密手段进行加密，然后按照规则编写自定义的ClassLoader进行解密，
	 * 这样我们就可以在程序中加载特定了类， 并且这个类只能被我们自定义的加载器进行加载，提高了程序的安全性。
	 * 
	 * 下面，我们编写代码。
	 * 
	 * 1.定义加密解密协议 加密和解密的协议有很多种，具体怎么定看业务需要。在这里，为了便于演示，我简单地将加密解密定义为异或运算。
	 * 当一个文件进行异或运算后，产生了加密文件，再进行一次异或后，就进行了解密。
	 */
	public static void testEncrypt() {
		DeClassLoader diskLoader = new DeClassLoader("E:\\lib");
		try {
			// 加载class文件
			Class<?> c = diskLoader.loadClass("com.lyf.test.Test");

			if (c != null) {
				try {
					Object obj = c.newInstance();
					Method method = c.getDeclaredMethod("say", null);
					// 通过反射调用Test类的say方法
					method.invoke(obj, null);
				} catch (InstantiationException | IllegalAccessException
						| NoSuchMethodException | SecurityException
						| IllegalArgumentException | InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Thread.currentThread().setContextClassLoader(ClassLoader classLoader);
	 * 
	 * contextClassLoader只是一个成员变量，通过setContextClassLoader()方法设置，通过getContextClassLoader()设置。 
	 * 
	 * 每个Thread都有一个相关联的ClassLoader，默认是AppClassLoader。
	 * 并且子线程默认使用父线程的ClassLoader除非子线程特别设置。 
	 */
	public static void testContext() {
		DiskClassLoader diskLoader1 = new DiskClassLoader("E:\\lib\\test");
		Class cls1 = null;
		try {
			// 加载class文件
			cls1 = diskLoader1.loadClass("com.lyf.test.SpeakTest");
			System.out.println(cls1.getClassLoader().toString());
			if (cls1 != null) {
				try {
					Object obj = cls1.newInstance();
					// SpeakTest1 speak = (SpeakTest1) obj;
					// speak.speak();
					Method method = cls1.getDeclaredMethod("speak", null);
					// 通过反射调用Test类的speak方法
					method.invoke(obj, null);
				} catch (InstantiationException | IllegalAccessException
						| NoSuchMethodException | SecurityException
						| IllegalArgumentException | InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		DiskClassLoader diskLoader = new DiskClassLoader("E:\\lib");
		System.out.println("Thread " + Thread.currentThread().getName()
				+ " classloader: "
				+ Thread.currentThread().getContextClassLoader().toString());
		new Thread(new Runnable() {

			@Override
			public void run() {
				System.out.println("Thread "
						+ Thread.currentThread().getName()
						+ " classloader: "
						+ Thread.currentThread().getContextClassLoader()
								.toString());

				// TODO Auto-generated method stub
				try {
					/**
					 * java.lang.ClassNotFoundException: com.lyf.test.SpeakTest
					 * 	at java.net.URLClassLoader.findClass(Unknown Source)
					 * 	at java.lang.ClassLoader.loadClass(Unknown Source)
					 * 	at sun.misc.Launcher$AppClassLoader.loadClass(Unknown Source)
					 * 	at java.lang.ClassLoader.loadClass(Unknown Source)
					 * 	at lyf.test.Main$1.run(Main.java:160)
					 * 	at java.lang.Thread.run(Unknown Source)
					 * 
					 * 
					 * 我们可以得到如下的信息： 
					 * 1. DiskClassLoader1加载成功了SpeakTest.class文件并执行成功。 
					 * 2. 子线程的ContextClassLoader是AppClassLoader。 
					 * 3. AppClassLoader加载不了父线程当中已经加载的SpeakTest.class内容。 
					 * 
					 * 我们修改一下代码，在子线程开头处加上这么一句内容。:
					 * Thread.currentThread().setContextClassLoader(diskLoader);
					 */
					// 加载class文件
					// Thread.currentThread().setContextClassLoader(diskLoader);
					// Class c = diskLoader.loadClass("com.lyf.test.SpeakTest");
					ClassLoader cl = Thread.currentThread()
							.getContextClassLoader();
					Class c = cl.loadClass("com.lyf.test.SpeakTest");
					// Class c = Class.forName("com.lyf.test.SpeakTest");
					System.out.println(c.getClassLoader().toString());
					if (c != null) {
						try {
							Object obj = c.newInstance();
							// SpeakTest1 speak = (SpeakTest1) obj;
							// speak.speak();
							Method method = c.getDeclaredMethod("speak", null);
							// 通过反射调用Test类的say方法
							method.invoke(obj, null);
						} catch (InstantiationException
								| IllegalAccessException
								| NoSuchMethodException | SecurityException
								| IllegalArgumentException
								| InvocationTargetException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();
	}
}
