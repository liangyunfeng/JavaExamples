package com.github.liangyunfeng.pattern.decorator;
/*
public class Client {
	public static void main(String args[]) {
		Component component, componentSB; // ʹ�ó��󹹼�����
		component = new Window(); // ������幹��
		componentSB = new ScrollBarDecorator(component); // ����װ�κ�Ĺ���
		componentSB.display();
		
		//Component  component,componentSB,componentBB; //ȫ��ʹ�ó��󹹼�����
        //component = new Window();
        //componentSB = new  ScrollBarDecorator(component);
        //componentBB = new  BlackBorderDecorator(componentSB); //��װ����һ��֮��Ķ������ע�뵽��һ��װ�����У����еڶ���װ��
        //componentBB.display();
	}
}

// ������湹���ࣺ���󹹼��࣬Ϊ��ͻ����ģʽ��صĺ��Ĵ��룬��ԭ�пؼ���������˴����ļ�
abstract class Component {
	public abstract void display();
}

// �����ࣺ���幹����
class Window extends Component {
	public void display() {
		System.out.println("��ʾ���壡");
	}
}

// �ı����ࣺ���幹����
class TextBox extends Component {
	public void display() {
		System.out.println("��ʾ�ı���");
	}
}

// �б���ࣺ���幹����
class ListBox extends Component {
	public void display() {
		System.out.println("��ʾ�б��");
	}
}

// ����װ���ࣺ����װ����
class ComponentDecorator extends Component {
	private Component component; // ά�ֶԳ��󹹼����Ͷ��������

	public ComponentDecorator(Component component) // ע����󹹼����͵Ķ���
	{
		this.component = component;
	}

	public void display() {
		component.display();
	}
}

// ������װ���ࣺ����װ����
class ScrollBarDecorator extends ComponentDecorator {
	public ScrollBarDecorator(Component component) {
		super(component);
	}

	public void display() {
		this.setScrollBar();
		super.display();
	}

	public void setScrollBar() {
		System.out.println("Ϊ�������ӹ�������");
	}
}

// ��ɫ�߿�װ���ࣺ����װ����
class BlackBorderDecorator extends ComponentDecorator {
	public BlackBorderDecorator(Component component) {
		super(component);
	}

	public void display() {
		this.setBlackBorder();
		super.display();
	}

	public void setBlackBorder() {
		System.out.println("Ϊ�������Ӻ�ɫ�߿�");
	}
}*/
