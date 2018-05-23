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
	// ģ�巽��
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

	// �������������巽��
	public void PrimitiveOperation1() {
		// ʵ�ִ���
		System.out.println("AbstractClass.PrimitiveOperation1()");
	}

	// �������������󷽷�
	public abstract void PrimitiveOperation2();

	// �������������ӷ���
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