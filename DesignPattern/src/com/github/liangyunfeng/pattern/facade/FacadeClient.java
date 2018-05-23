package com.github.liangyunfeng.pattern.facade;

public class FacadeClient {

	public static void main(String[] args) {
		Facade facade = new Facade();
		facade.Method();
	}

}

class SubSystemA {
	public void MethodA() {
		// ҵ��ʵ�ִ���
	}
}

class SubSystemB {
	public void MethodB() {
		// ҵ��ʵ�ִ���
	}
}

class SubSystemC {
	public void MethodC() {
		// ҵ��ʵ�ִ���
	}
}

class Facade {
	private SubSystemA obj1 = new SubSystemA();
	private SubSystemB obj2 = new SubSystemB();
	private SubSystemC obj3 = new SubSystemC();

	public void Method() {
		obj1.MethodA();
		obj2.MethodB();
		obj3.MethodC();
	}
}