package com.github.liangyunfeng.pattern.decorator;

public class DecoratorClient {

	/**
	 * �Ž�ģʽ��װ��ģʽ�ĶԱ�
	 * https://blog.csdn.net/plgy_Y/article/details/73522812
	 * 
	 * װ��ģʽ���԰�װ�ܶ�㣬�Ž�ģʽֻ�ܰ�װһ��
	 */
	public static void main(String[] args) {
		Component component = new ConcreteComponent();
		Decorator decorator = new ConcreteDecorator(component);
		decorator.operation();
	}

}

interface Component {
	public abstract void operation(); // ҵ�񷽷�
}

class ConcreteComponent implements Component {

	@Override
	public void operation() {
		System.out.println("Create basic Component.");
	}

}

class Decorator implements Component {
	private Component component; // ά��һ���Գ��󹹼����������

	public Decorator(Component component) // ע��һ�����󹹼����͵Ķ���
	{
		this.component = component;
	}

	public void operation() {
		component.operation(); // ����ԭ��ҵ�񷽷�
	}
}

class ConcreteDecorator extends Decorator {

	public ConcreteDecorator(Component component) {
		super(component);
	}

	public void operation() {
		super.operation(); // ����ԭ��ҵ�񷽷�
		addedBehavior(); // ��������ҵ�񷽷�
	}

	// ����ҵ�񷽷�
	public void addedBehavior() {
		System.out.println("Add 1st function to Component.");
	}
}
