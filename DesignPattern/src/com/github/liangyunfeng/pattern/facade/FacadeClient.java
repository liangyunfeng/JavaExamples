package com.github.liangyunfeng.pattern.facade;

public class FacadeClient {

	public static void main(String[] args) {
		Facade facade = new Facade();
		facade.Method();
	}

}

class SubSystemA {
	public void MethodA() {
		// 业务实现代码
	}
}

class SubSystemB {
	public void MethodB() {
		// 业务实现代码
	}
}

class SubSystemC {
	public void MethodC() {
		// 业务实现代码
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