package com.github.liangyunfeng.pattern.adapter;

public class ObjectAdapterClient {

	public static void main(String[] args) {
		// ¶ÔÏóÊÊÅäÆ÷
		Target1 target1 = new Adapter1();
		target1.request();
	}

}

class Adaptee1 {
	public void specificRequest() {
		System.out.println("specificRequest()");
	}
}

abstract class Target1 {
	public abstract void request();
}

class Adapter1 extends Target1 {

	Adaptee1 adaptee = new Adaptee1();

	@Override
	public void request() {
		adaptee.specificRequest();
		System.out.println("request()");
	}
}
