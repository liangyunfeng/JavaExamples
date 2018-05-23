package com.github.liangyunfeng.pattern.adapter;

public class ClassAdapterClient {

	public static void main(String[] args) {
		// ¿‡  ≈‰∆˜
		Target target = new Adapter();
		target.request();
	}

}

interface Adaptee {
	public void specificRequest();
}

abstract class Target {
	public abstract void request();
}

class Adapter extends Target implements Adaptee {

	@Override
	public void specificRequest() {
		System.out.println("specificRequest()");
	}

	@Override
	public void request() {
		specificRequest();
		System.out.println("request()");
	}
}
