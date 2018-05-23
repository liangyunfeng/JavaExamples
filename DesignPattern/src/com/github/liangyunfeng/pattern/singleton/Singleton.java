package com.github.liangyunfeng.pattern.singleton;

//懒汉式单例模式
public class Singleton {
	private volatile static Singleton instance = null; 	// 使用volatile的原因是可能线程A创建完Singleton对象后，没有及时把工作内存中的内容更新到物理内存中，线程B中的工作内存保存的变量还是null，就会重复创建对象

	private Singleton() {

	}

	public static Singleton getInstance() {
		if (instance == null) { 				// 因为在这里使用synchronized同步块，另一个线程B可能已经通过第一层null判断
			synchronized (Singleton.class) { 	// 需要锁着Singlton.class而不是this，因为这是静态方法，是属于类的，而不是属于对象的
				if (instance == null) { 		// 另一个线程B可能已经通过第一层null判断在synchronized等待第一个线程A结束后进来还需要判断instance是否为null，以免重复创建
					instance = new Singleton();
				}
			}
		}
		return instance;
	}
}


// 饿汉式单例模式
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
由于静态单例对象没有作为Singleton的成员变量直接实例化，因此类加载时不会实例化Singleton，
第一次调用getInstance()时将加载内部类HolderClass，在该内部类中定义了一个static类型的变量instance，
此时会首先初始化这个成员变量，由Java虚拟机来保证其线程安全性，确保该成员变量只能初始化一次。
由于getInstance()方法没有任何线程锁定，因此其性能不会造成任何影响。

通过使用IoDH，我们既可以实现延迟加载，又可以保证线程安全，不影响系统性能，
不失为一种最好的Java语言单例模式实现方式（其缺点是与编程语言本身的特性相关，很多面向对象语言不支持IoDH）。
*/