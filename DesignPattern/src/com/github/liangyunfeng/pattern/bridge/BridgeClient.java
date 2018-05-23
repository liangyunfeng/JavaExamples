package com.github.liangyunfeng.pattern.bridge;

public class BridgeClient {
	
	/**
	 * �Ž�ģʽ��װ��ģʽ�ĶԱ�
	 * https://blog.csdn.net/plgy_Y/article/details/73522812
	 * 
	 * װ��ģʽ���԰�װ�ܶ�㣬�Ž�ģʽֻ�ܰ�װһ��
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
	protected Implementor impl; // ����ʵ����ӿڶ���

	public void setImpl(Implementor impl) {
		this.impl = impl;
	}

	public abstract void operation(); // ��������ҵ�񷽷�
}

class RefinedAbstraction extends Abstraction {
	public void operation() {
		// ҵ�����
		System.out.println("RefinedAbstraction : 1");
		impl.operationImpl(); // ����ʵ����ķ���
		// ҵ�����
		System.out.println("RefinedAbstraction : 2");
	}
}