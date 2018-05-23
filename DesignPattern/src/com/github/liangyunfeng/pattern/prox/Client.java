package com.github.liangyunfeng.pattern.prox;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Client {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 两种实现AOP的方式： 
		// 1. 静态代理实现 
		// 2. JDK提供的动态代理实现 
		// 3. 通过cglib创建代理类

		//!示例1: 静态代理实现 
		ICalculator calc = new Calculator();
		ICalculator calcImpl = new CalculatorProxy(calc);
		int result = calcImpl.calculate(7, 8);
		System.out.println("result = " + result);
		
		
		//!示例2: JDK动态代理实现 
		ICalculator calc2 = new Calculator();
		ICalculator calcImpl2 = (ICalculator)Proxy.newProxyInstance(calc2.getClass().getClassLoader(), 
				new Class[]{ ICalculator.class }, new AOPTestHandler(calc2));
		int result2 = calcImpl2.calculate(100, 200);
		System.out.println("result2 = " + result2);
		
		
		//!示例3: cglib代理实现 
		//目标对象
		//TargetObject target = new TargetObject();
		//拦截器
		//MyInterceptor myInterceptor = new MyInterceptor(target);
		//代理对象，调用cglib系统方法自动生成
		//注意：代理类是目标类的子类。
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

// 静态代理实现
class CalculatorProxy implements ICalculator {

	ICalculator target;
	
	public CalculatorProxy(ICalculator obj) {
		target = obj;
	}
	
	@Override
	public int calculate(int a, int b) {
		// TODO Auto-generated method stub
		a();

        int result = target.calculate(a, b);	//调用目标类的目标方法

        b();
		
		return result;
	}
	
	private void a() {
		System.out.println("a()");			//切面方法a();
	}
	
	private void b() {
		System.out.println("b()");			//切面方法b();
	}
}

// 动态代理实现
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

        Object result = method.invoke(targetObject, params);	//调用目标类的目标方法

        g();
		
		return result;
	}
	
	private void f() {
		System.out.println("f()");			//切面方法f();
	}
	
	private void g() {
		System.out.println("g()");			//切面方法g();
	}
}