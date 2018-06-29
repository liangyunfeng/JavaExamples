package lyf.test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import lyf.custom.disk.DiskClassLoader;
import lyf.custom.encrypt.DeClassLoader;
import lyf.custom.encrypt.FileUtils;

public class Main {

	public static void main(String[] args) {
		// ����Disk ClassLoader
		// testDisk();

		// ���Լ���/���� ClassLoader
		// FileUtils.test("E:\\lib\\Test.class");
		// testEncrypt();

		testContext();
	}

	/**
	 * ����������Ҫһ���Զ����classloader,Ĭ�ϼ���·��ΪD:\lib�µ�jar������Դ��
	 * 
	 * ����д��дһ�������õ����ļ���Test.java
	 * 
	 * Ȼ�����������class�ļ�Test.class�ŵ�E:\lib���·���¡�
	 */
	public static void testDisk() {
		// �����Զ���classloader����
		DiskClassLoader diskLoader = new DiskClassLoader("E:\\lib");
		try {
			// ����class�ļ�
			Class<?> c = diskLoader.loadClass("com.lyf.test.Test");

			if (c != null) {
				try {
					Object obj = c.newInstance();
					Method method = c.getDeclaredMethod("say", null);
					// ͨ���������Test���say����
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
	 * �Զ���ClassLoader������ʲô��
	 * 
	 * ͻ����JDKϵͳ���ü���·��������֮�����ǾͿ��Ա�д�Զ���ClassLoader��Ȼ��ʣ�µľͽи����������Լ��ˡ�
	 * ����԰����Լ�����Ը����ҵ��Ķ��ƣ���ClassLoader�����������
	 * 
	 * �����֮Class����������� �������÷��ǽ�Class�ļ�����ĳ�ּ����ֶν��м��ܣ�Ȼ���չ����д�Զ����ClassLoader���н��ܣ�
	 * �������ǾͿ����ڳ����м����ض����࣬ ���������ֻ�ܱ������Զ���ļ��������м��أ�����˳���İ�ȫ�ԡ�
	 * 
	 * ���棬���Ǳ�д���롣
	 * 
	 * 1.������ܽ���Э�� ���ܺͽ��ܵ�Э���кܶ��֣�������ô����ҵ����Ҫ�������Ϊ�˱�����ʾ���Ҽ򵥵ؽ����ܽ��ܶ���Ϊ������㡣
	 * ��һ���ļ������������󣬲����˼����ļ����ٽ���һ�����󣬾ͽ����˽��ܡ�
	 */
	public static void testEncrypt() {
		DeClassLoader diskLoader = new DeClassLoader("E:\\lib");
		try {
			// ����class�ļ�
			Class<?> c = diskLoader.loadClass("com.lyf.test.Test");

			if (c != null) {
				try {
					Object obj = c.newInstance();
					Method method = c.getDeclaredMethod("say", null);
					// ͨ���������Test���say����
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
	 * contextClassLoaderֻ��һ����Ա������ͨ��setContextClassLoader()�������ã�ͨ��getContextClassLoader()���á� 
	 * 
	 * ÿ��Thread����һ���������ClassLoader��Ĭ����AppClassLoader��
	 * �������߳�Ĭ��ʹ�ø��̵߳�ClassLoader�������߳��ر����á� 
	 */
	public static void testContext() {
		DiskClassLoader diskLoader1 = new DiskClassLoader("E:\\lib\\test");
		Class cls1 = null;
		try {
			// ����class�ļ�
			cls1 = diskLoader1.loadClass("com.lyf.test.SpeakTest");
			System.out.println(cls1.getClassLoader().toString());
			if (cls1 != null) {
				try {
					Object obj = cls1.newInstance();
					// SpeakTest1 speak = (SpeakTest1) obj;
					// speak.speak();
					Method method = cls1.getDeclaredMethod("speak", null);
					// ͨ���������Test���speak����
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
					 * ���ǿ��Եõ����µ���Ϣ�� 
					 * 1. DiskClassLoader1���سɹ���SpeakTest.class�ļ���ִ�гɹ��� 
					 * 2. ���̵߳�ContextClassLoader��AppClassLoader�� 
					 * 3. AppClassLoader���ز��˸��̵߳����Ѿ����ص�SpeakTest.class���ݡ� 
					 * 
					 * �����޸�һ�´��룬�����߳̿�ͷ��������ôһ�����ݡ�:
					 * Thread.currentThread().setContextClassLoader(diskLoader);
					 */
					// ����class�ļ�
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
							// ͨ���������Test���say����
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
