package com.github.liangyunfeng.pattern.singleton;

//����ʽ����ģʽ
public class Singleton {
	private volatile static Singleton instance = null; 	// ʹ��volatile��ԭ���ǿ����߳�A������Singleton�����û�м�ʱ�ѹ����ڴ��е����ݸ��µ������ڴ��У��߳�B�еĹ����ڴ汣��ı�������null���ͻ��ظ���������

	private Singleton() {

	}

	public static Singleton getInstance() {
		if (instance == null) { 				// ��Ϊ������ʹ��synchronizedͬ���飬��һ���߳�B�����Ѿ�ͨ����һ��null�ж�
			synchronized (Singleton.class) { 	// ��Ҫ����Singlton.class������this����Ϊ���Ǿ�̬��������������ģ����������ڶ����
				if (instance == null) { 		// ��һ���߳�B�����Ѿ�ͨ����һ��null�ж���synchronized�ȴ���һ���߳�A�������������Ҫ�ж�instance�Ƿ�Ϊnull�������ظ�����
					instance = new Singleton();
				}
			}
		}
		return instance;
	}
}


// ����ʽ����ģʽ
class EagerSingleton {
	private static EagerSingleton instance = new EagerSingleton();

	private EagerSingleton() {

	}

	public static EagerSingleton getInstance() {
		return instance;
	}
}



  
//Initialization on Demand Holder
class IoDHSingleton {
	private IoDHSingleton() {
		
	}
	
	private static class HolderClass {
          private final static IoDHSingleton instance = new IoDHSingleton();
	}
	
	public static IoDHSingleton getInstance() {
	    return HolderClass.instance;
	}
}
/*
���ھ�̬��������û����ΪSingleton�ĳ�Ա����ֱ��ʵ��������������ʱ����ʵ����Singleton��
��һ�ε���getInstance()ʱ�������ڲ���HolderClass���ڸ��ڲ����ж�����һ��static���͵ı���instance��
��ʱ�����ȳ�ʼ�������Ա��������Java���������֤���̰߳�ȫ�ԣ�ȷ���ó�Ա����ֻ�ܳ�ʼ��һ�Ρ�
����getInstance()����û���κ��߳���������������ܲ�������κ�Ӱ�졣

ͨ��ʹ��IoDH�����Ǽȿ���ʵ���ӳټ��أ��ֿ��Ա�֤�̰߳�ȫ����Ӱ��ϵͳ���ܣ�
��ʧΪһ����õ�Java���Ե���ģʽʵ�ַ�ʽ����ȱ�����������Ա����������أ��ܶ�����������Բ�֧��IoDH����
*/