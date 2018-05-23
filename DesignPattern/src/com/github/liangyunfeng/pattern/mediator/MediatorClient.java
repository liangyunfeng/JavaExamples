package com.github.liangyunfeng.pattern.mediator;

import java.util.ArrayList;

public class MediatorClient {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

abstract class Mediator {
	protected ArrayList<Colleague> colleagues; // ���ڴ洢ͬ�¶���

	// ע�᷽������������ͬ�¶���
	public void register(Colleague colleague) {
		colleagues.add(colleague);
	}

	// ���������ҵ�񷽷�
	public abstract void operation();
}

class ConcreteMediator extends Mediator {
	// ʵ��ҵ�񷽷�����װͬ��֮��ĵ���
	public void operation() {
		// ......
		((Colleague) (colleagues.get(0))).method1(); // ͨ���н��ߵ���ͬ����ķ���
		// ......
	}
}

abstract class Colleague {
	protected Mediator mediator; // ά��һ�������н��ߵ�����

	public Colleague(Mediator mediator) {
		this.mediator = mediator;
	}

	public abstract void method1(); // �����������������Լ�����Ϊ

	// �����������������н��߽���ͨ��
	public void method2() {
		mediator.operation();
	}
}

class ConcreteColleagueA extends Colleague {
	public ConcreteColleagueA(Mediator mediator) {
		super(mediator);
	}

	// ʵ��������
	public void method1() {
		// ......
	}
}

class ConcreteColleagueB extends Colleague {
	public ConcreteColleagueB(Mediator mediator) {
		super(mediator);
	}

	// ʵ��������
	public void method1() {
		// ......
	}
}