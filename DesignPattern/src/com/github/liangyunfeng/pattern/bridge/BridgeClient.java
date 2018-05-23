package com.github.liangyunfeng.pattern.bridge;

public class BridgeClient {
	
	/**
	 * 桥接模式与装饰模式的对比
	 * https://blog.csdn.net/plgy_Y/article/details/73522812
	 * 
	 * 装饰模式可以包装很多层，桥接模式只能包装一层
	 */
	public static void main(String[] args) {
		Implementor implA = new ConcreteImplementorA();
		Abstraction ab = new RefinedAbstraction();
		ab.setImpl(implA);
		ab.operation();

		Implementor implB = new ConcreteImplementorB();
		ab.setImpl(implB);
		ab.operation();
	}

}

interface Implementor {
	public void operationImpl();
}

class ConcreteImplementorA implements Implementor {

	@Override
	public void operationImpl() {
		System.out.println("ConcreteImplementorA");
	}

}

class ConcreteImplementorB implements Implementor {

	@Override
	public void operationImpl() {
		System.out.println("ConcreteImplementorB");
	}

}

abstract class Abstraction {
	protected Implementor impl; // 定义实现类接口对象

	public void setImpl(Implementor impl) {
		this.impl = impl;
	}

	public abstract void operation(); // 声明抽象业务方法
}

class RefinedAbstraction extends Abstraction {
	public void operation() {
		// 业务代码
		System.out.println("RefinedAbstraction : 1");
		impl.operationImpl(); // 调用实现类的方法
		// 业务代码
		System.out.println("RefinedAbstraction : 2");
	}
}