package com.github.liangyunfeng.pattern.mediator;

import java.util.ArrayList;

public class MediatorClient {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

abstract class Mediator {
	protected ArrayList<Colleague> colleagues; // 用于存储同事对象

	// 注册方法，用于增加同事对象
	public void register(Colleague colleague) {
		colleagues.add(colleague);
	}

	// 声明抽象的业务方法
	public abstract void operation();
}

class ConcreteMediator extends Mediator {
	// 实现业务方法，封装同事之间的调用
	public void operation() {
		// ......
		((Colleague) (colleagues.get(0))).method1(); // 通过中介者调用同事类的方法
		// ......
	}
}

abstract class Colleague {
	protected Mediator mediator; // 维持一个抽象中介者的引用

	public Colleague(Mediator mediator) {
		this.mediator = mediator;
	}

	public abstract void method1(); // 声明自身方法，处理自己的行为

	// 定义依赖方法，与中介者进行通信
	public void method2() {
		mediator.operation();
	}
}

class ConcreteColleagueA extends Colleague {
	public ConcreteColleagueA(Mediator mediator) {
		super(mediator);
	}

	// 实现自身方法
	public void method1() {
		// ......
	}
}

class ConcreteColleagueB extends Colleague {
	public ConcreteColleagueB(Mediator mediator) {
		super(mediator);
	}

	// 实现自身方法
	public void method1() {
		// ......
	}
}