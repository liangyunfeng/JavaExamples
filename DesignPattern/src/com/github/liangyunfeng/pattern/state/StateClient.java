package com.github.liangyunfeng.pattern.state;

public class StateClient {

	/**
	 * (1) ͳһ�ɻ�����������״̬֮���ת��
	 * (2) �ɾ���״̬��������״̬֮���ת��
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

abstract class State {
	// ��������ҵ�񷽷�����ͬ�ľ���״̬����Բ�ͬ��ʵ��
	public abstract void handle();

	/**
	 * (2) �ɾ���״̬��������״̬֮���ת���������ھ���״̬���ҵ�񷽷����жϻ������ĳЩ����ֵ�ٸ������Ϊ�����������µ�״̬����
	 * ʵ��״̬ת����ͬ����Ҳ�����ṩһ��ר�ŵķ�������������ֵ���жϺ�״̬ת������ʱ��״̬���뻷����֮��ͽ����������������ϵ��
	 * ��Ϊ״̬����Ҫ���ʻ������е�����ֵ�� ���´���Ƭ����ʾ��
	 */
	public void changeState(Context ctx) {
		// ���ݻ��������е�����ֵ����״̬ת��
		if (ctx.getValue() == 1) {
			ctx.setState(new ConcreteStateB());
		} else if (ctx.getValue() == 2) {
			ctx.setState(new ConcreteStateC());
		}

	}
}

class ConcreteStateA extends State {
	public void handle() {
		// ��������ʵ�ִ���
	}
}

class ConcreteStateB extends State {
	public void handle() {
		// ��������ʵ�ִ���
	}
}

class ConcreteStateC extends State {
	public void handle() {
		// ��������ʵ�ִ���
	}
}

class Context {
	private State state; // ά��һ���Գ���״̬���������
	private int value; // ��������ֵ��������ֵ�ı仯���ܻᵼ�¶���״̬�����仯

	// ����״̬����
	public void setState(State state) {
		this.state = state;
	}

	public void request() {
		// ��������
		state.handle(); // ����״̬�����ҵ�񷽷�
		// ��������
	}

	/*
	 * (1) ͳһ�ɻ�����������״̬֮���ת������ʱ�������໹�䵱��״̬������(State Manager)��ɫ��
	 * �ڻ������ҵ�񷽷���ͨ����ĳЩ����ֵ���ж�ʵ��״̬ת�����������ṩһ��ר�ŵķ�������ʵ�������жϺ�״̬ת���� ���´���Ƭ����ʾ��
	 */
	public void changeState() {
		// �ж�����ֵ����������ֵ����״̬ת��
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