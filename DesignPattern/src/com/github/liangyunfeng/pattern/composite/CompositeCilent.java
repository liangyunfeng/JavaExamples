package com.github.liangyunfeng.pattern.composite;

import java.util.ArrayList;

public class CompositeCilent {

	public static void main(String[] args) {
		Component leaf1, leaf2, leaf3, Composite1, Composite2;
		leaf1 = new Leaf();
		leaf2 = new Leaf();
		leaf3 = new Leaf();
		
		Composite1 = new Composite();
		Composite2 = new Composite();
		
		Composite1.add(Composite2);
		Composite1.add(leaf1);
		Composite2.add(leaf2);
		Composite2.add(leaf3);
	}

}

abstract class Component {
	public abstract void operation(); // ҵ�񷽷�

	public abstract void add(Component c); // ���ӳ�Ա

	public abstract void remove(Component c); // ɾ����Ա

	public abstract Component getChild(int i); // ��ȡ��Ա
}

class Leaf extends Component {
	public void add(Component c) {
		// �쳣����������ʾ
	}

	public void remove(Component c) {
		// �쳣����������ʾ
	}

	public Component getChild(int i) {
		// �쳣����������ʾ
		return null;
	}

	public void operation() {
		// Ҷ�ӹ�������ҵ�񷽷���ʵ��
	}
}

class Composite extends Component {
	private ArrayList<Component> list = new ArrayList<Component>();

	public void add(Component c) {
		list.add(c);
	}

	public void remove(Component c) {
		list.remove(c);
	}

	public Component getChild(int i) {
		return (Component) list.get(i);
	}

	public void operation() {
		// ������������ҵ�񷽷���ʵ��
		// �ݹ���ó�Ա������ҵ�񷽷�
		for (Object obj : list) {
			((Component) obj).operation();
		}
	}
}
