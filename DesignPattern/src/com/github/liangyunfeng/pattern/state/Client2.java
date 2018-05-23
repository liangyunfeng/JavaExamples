package com.github.liangyunfeng.pattern.state;

// ʹ�û�����ʵ��״̬ת��
public class Client2 {
	public static void main(String args[]) {
		Screen screen = new Screen();
		screen.onClick();
		screen.onClick();
		screen.onClick();
	}
}

// ��Ļ��
class Screen {
	// ö�����е�״̬��currentState��ʾ��ǰ״̬
	private ScreenState currentState, normalState, largerState, largestState;

	public Screen() {
		this.normalState = new NormalScreenState(); // ��������״̬����
		this.largerState = new LargerScreenState(); // ���������Ŵ�״̬����
		this.largestState = new LargestScreenState(); // �����ı��Ŵ�״̬����
		this.currentState = normalState; // ���ó�ʼ״̬
		this.currentState.display();
	}

	public void setState(ScreenState state) {
		this.currentState = state;
	}

	// �����¼�����������ת�˶�״̬����ҵ�񷽷��ĵ��ú�״̬��ת��
	public void onClick() {
		if (this.currentState == normalState) {
			this.setState(largerState);
			this.currentState.display();
		} else if (this.currentState == largerState) {
			this.setState(largestState);
			this.currentState.display();
		} else if (this.currentState == largestState) {
			this.setState(normalState);
			this.currentState.display();
		}
	}
}

// ����״̬��
abstract class ScreenState {
	public abstract void display();
}

// ����״̬��
class NormalScreenState extends ScreenState {
	public void display() {
		System.out.println("������С��");
	}
}

// ����״̬��
class LargerScreenState extends ScreenState {
	public void display() {
		System.out.println("������С��");
	}
}

// �ı�״̬��
class LargestScreenState extends ScreenState {
	public void display() {
		System.out.println("�ı���С��");
	}
}