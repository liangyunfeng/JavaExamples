package com.github.liangyunfeng.pattern.simplefactory;

public class SimpleFactory {

	// Client
	public static void main(String[] args) {
		Product product;
		product = Factory.getPorduct("A");		// 通过工厂类创建产品对象
		product.methodSame();
		product.methodDiff();
	}

}

abstract class Product {
	// 所有产品类的公共业务方法
	public void methodSame() {
		// 公共方法的实现
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
	// 静态工厂方法
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
