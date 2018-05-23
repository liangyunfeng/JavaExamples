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
	public abstract void operation(); // 业务方法

	public abstract void add(Component c); // 增加成员

	public abstract void remove(Component c); // 删除成员

	public abstract Component getChild(int i); // 获取成员
}

class Leaf extends Component {
	public void add(Component c) {
		// 异常处理或错误提示
	}

	public void remove(Component c) {
		// 异常处理或错误提示
	}

	public Component getChild(int i) {
		// 异常处理或错误提示
		return null;
	}

	public void operation() {
		// 叶子构件具体业务方法的实现
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
		// 容器构件具体业务方法的实现
		// 递归调用成员构件的业务方法
		for (Object obj : list) {
			((Component) obj).operation();
		}
	}
}
