package com.github.liangyunfeng.pattern.templatemethod;

public class TemplateMethodClient {

	public static void main(String[] args) {
		AbstractClass clsa = new ConcreteClassA();
		clsa.templateMethod();
		
		AbstractClass clsb = new ConcreteClassB();
		clsb.templateMethod();
	}

}

abstract class AbstractClass {
	// 模板方法
	public void templateMethod() {
		// ....
		PrimitiveOperation1();
		// ....
		PrimitiveOperation2();
		// ....
		if (isHook())
			PrimitiveOperation3();
		// ....
	}

	// 基本方法—具体方法
	public void PrimitiveOperation1() {
		// 实现代码
		System.out.println("AbstractClass.PrimitiveOperation1()");
	}

	// 基本方法—抽象方法
	public abstract void PrimitiveOperation2();

	// 基本方法—钩子方法
	public void PrimitiveOperation3() {
		System.out.println("AbstractClass.PrimitiveOperation3()");
	}

	public boolean isHook() {
		return true;
	}
}

class ConcreteClassA extends AbstractClass {

	@Override
	public void PrimitiveOperation2() {
		System.out.println("ConcreteClassA.PrimitiveOperation2()");
	}
	
}

class ConcreteClassB extends AbstractClass {

	@Override
	public void PrimitiveOperation2() {
		System.out.println("ConcreteClassB.PrimitiveOperation2()");
	}
	
	public boolean isHook() {
		return false;
	}
}