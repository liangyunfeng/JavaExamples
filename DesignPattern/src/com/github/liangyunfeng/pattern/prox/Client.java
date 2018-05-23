package com.github.liangyunfeng.pattern.prox;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Client {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// ����ʵ��AOP�ķ�ʽ�� 
		// 1. ��̬����ʵ�� 
		// 2. JDK�ṩ�Ķ�̬����ʵ�� 
		// 3. ͨ��cglib����������

		//!ʾ��1: ��̬����ʵ�� 
		ICalculator calc = new Calculator();
		ICalculator calcImpl = new CalculatorProxy(calc);
		int result = calcImpl.calculate(7, 8);
		System.out.println("result = " + result);
		
		
		//!ʾ��2: JDK��̬����ʵ�� 
		ICalculator calc2 = new Calculator();
		ICalculator calcImpl2 = (ICalculator)Proxy.newProxyInstance(calc2.getClass().getClassLoader(), 
				new Class[]{ ICalculator.class }, new AOPTestHandler(calc2));
		int result2 = calcImpl2.calculate(100, 200);
		System.out.println("result2 = " + result2);
		
		
		//!ʾ��3: cglib����ʵ�� 
		//Ŀ�����
		//TargetObject target = new TargetObject();
		//������
		//MyInterceptor myInterceptor = new MyInterceptor(target);
		//������󣬵���cglibϵͳ�����Զ�����
		//ע�⣺��������Ŀ��������ࡣ
		//TargetObject proxyObj = (TargetObject) myInterceptor.createProxy();
		//proxyObj.business();
	}
}


interface ICalculator {
	public int calculate(int a, int b);
}

class Calculator implements ICalculator {

	@Override
	public int calculate(int a, int b) {
		// TODO Auto-generated method stub
		System.out.println(a + "+" + b);
		return a + b;
	}
}

// ��̬����ʵ��
class CalculatorProxy implements ICalculator {

	ICalculator target;
	
	public CalculatorProxy(ICalculator obj) {
		target = obj;
	}
	
	@Override
	public int calculate(int a, int b) {
		// TODO Auto-generated method stub
		a();

        int result = target.calculate(a, b);	//����Ŀ�����Ŀ�귽��

        b();
		
		return result;
	}
	
	private void a() {
		System.out.println("a()");			//���淽��a();
	}
	
	private void b() {
		System.out.println("b()");			//���淽��b();
	}
}

// ��̬����ʵ��
class AOPTestHandler implements InvocationHandler {

	Object targetObject;
	
	public AOPTestHandler(Object obj) {
		targetObject = obj;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] params)
			throws Throwable {
		// TODO Auto-generated method stub
		
		f();

        Object result = method.invoke(targetObject, params);	//����Ŀ�����Ŀ�귽��

        g();
		
		return result;
	}
	
	private void f() {
		System.out.println("f()");			//���淽��f();
	}
	
	private void g() {
		System.out.println("g()");			//���淽��g();
	}
}