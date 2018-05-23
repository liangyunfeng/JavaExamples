package com.github.liangyunfeng.pattern.strategy;

public class StrategyClient {

	public static void main(String[] args) {
		Context context = new Context();
		AbstractStrategy strategy;
		strategy = new ConcreteStrategyA(); // ��������ʱָ������
		context.setStrategy(strategy);
		context.algorithm();
	}

}

abstract class AbstractStrategy {
	public abstract void algorithm(); // ���������㷨
}

class ConcreteStrategyA extends AbstractStrategy {
	// �㷨�ľ���ʵ��
	public void algorithm() {
		// �㷨A
	}
}

class Context {
	private AbstractStrategy strategy; // ά��һ���Գ�������������

	public void setStrategy(AbstractStrategy strategy) {
		this.strategy = strategy;
	}

	// ���ò������е��㷨
	public void algorithm() {
		strategy.algorithm();
	}
}
