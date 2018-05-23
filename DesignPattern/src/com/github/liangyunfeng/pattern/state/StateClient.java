package com.github.liangyunfeng.pattern.state;

public class StateClient {

	/**
	 * (1) 统一由环境类来负责状态之间的转换
	 * (2) 由具体状态类来负责状态之间的转换
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

abstract class State {
	// 声明抽象业务方法，不同的具体状态类可以不同的实现
	public abstract void handle();

	/**
	 * (2) 由具体状态类来负责状态之间的转换，可以在具体状态类的业务方法中判断环境类的某些属性值再根据情况为环境类设置新的状态对象，
	 * 实现状态转换，同样，也可以提供一个专门的方法来负责属性值的判断和状态转换。此时，状态类与环境类之间就将存在依赖或关联关系，
	 * 因为状态类需要访问环境类中的属性值， 如下代码片段所示：
	 */
	public void changeState(Context ctx) {
		// 根据环境对象中的属性值进行状态转换
		if (ctx.getValue() == 1) {
			ctx.setState(new ConcreteStateB());
		} else if (ctx.getValue() == 2) {
			ctx.setState(new ConcreteStateC());
		}

	}
}

class ConcreteStateA extends State {
	public void handle() {
		// 方法具体实现代码
	}
}

class ConcreteStateB extends State {
	public void handle() {
		// 方法具体实现代码
	}
}

class ConcreteStateC extends State {
	public void handle() {
		// 方法具体实现代码
	}
}

class Context {
	private State state; // 维持一个对抽象状态对象的引用
	private int value; // 其他属性值，该属性值的变化可能会导致对象状态发生变化

	// 设置状态对象
	public void setState(State state) {
		this.state = state;
	}

	public void request() {
		// 其他代码
		state.handle(); // 调用状态对象的业务方法
		// 其他代码
	}

	/*
	 * (1) 统一由环境类来负责状态之间的转换，此时，环境类还充当了状态管理器(State Manager)角色，
	 * 在环境类的业务方法中通过对某些属性值的判断实现状态转换，还可以提供一个专门的方法用于实现属性判断和状态转换， 如下代码片段所示：
	 */
	public void changeState() {
		// 判断属性值，根据属性值进行状态转换
		if (value == 0) {
			this.setState(new ConcreteStateA());
		} else if (value == 1) {
			this.setState(new ConcreteStateB());
		}
	}

	public int getValue() {
		return value;
	}
}