package com.github.liangyunfeng.pattern.simplefactory;

public class SimpleFactory {

	// Client
	public static void main(String[] args) {
		Product product;
		product = Factory.getPorduct("A");		// ͨ�������ഴ����Ʒ����
		product.methodSame();
		product.methodDiff();
	}

}

abstract class Product {
	// ���в�Ʒ��Ĺ���ҵ�񷽷�
	public void methodSame() {
		// ����������ʵ��
	}
	
	public abstract void methodDiff();
}

class ConcreteProductA extends Product {

	@Override
	public void methodDiff() {
		
	}
}

class ConcreteProductB extends Product {

	@Override
	public void methodDiff() {
		
	}
}

class Factory {
	// ��̬��������
	public static Product getPorduct(String tag) {
		Product product = null;
		if(tag.equalsIgnoreCase("A")) {
			product = new ConcreteProductA();
		} else if(tag.equalsIgnoreCase("B")) {
			product = new ConcreteProductB();
		}
		return product;
	}
}
