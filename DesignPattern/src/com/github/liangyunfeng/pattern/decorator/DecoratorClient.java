package com.github.liangyunfeng.pattern.decorator;

public class DecoratorClient {

	/**
	 * 桥接模式与装饰模式的对比
	 * https://blog.csdn.net/plgy_Y/article/details/73522812
	 * 
	 * 装饰模式可以包装很多层，桥接模式只能包装一层
	 */
	public static void main(String[] args) {
		Component component = new ConcreteComponent();
		Decorator decorator = new ConcreteDecorator(component);
		decorator.operation();
	}

}

interface Component {
	public abstract void operation(); // 业务方法
}

class ConcreteComponent implements Component {

	@Override
	public void operation() {
		System.out.println("Create basic Component.");
	}

}

class Decorator implements Component {
	private Component component; // 维持一个对抽象构件对象的引用

	public Decorator(Component component) // 注入一个抽象构件类型的对象
	{
		this.component = component;
	}

	public void operation() {
		component.operation(); // 调用原有业务方法
	}
}

class ConcreteDecorator extends Decorator {

	public ConcreteDecorator(Component component) {
		super(component);
	}

	public void operation() {
		super.operation(); // 调用原有业务方法
		addedBehavior(); // 调用新增业务方法
	}

	// 新增业务方法
	public void addedBehavior() {
		System.out.println("Add 1st function to Component.");
	}
}
