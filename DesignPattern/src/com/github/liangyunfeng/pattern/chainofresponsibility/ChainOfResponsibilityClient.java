package com.github.liangyunfeng.pattern.chainofresponsibility;

public class ChainOfResponsibilityClient {

	public static void main(String[] args) {
		Handler successor1 = new ConcreteHandler();
		Handler successor2 = new ConcreteHandler2();
		Handler successor3 = new ConcreteHandler3();
		successor1.setSuccessor(successor2);
		successor2.setSuccessor(successor3);
		successor1.handleRequest("90");
	}

}

abstract class Handler {
	// ά�ֶ��¼ҵ�����
	protected Handler successor;

	public void setSuccessor(Handler successor) {
		this.successor = successor;
	}

	public abstract void handleRequest(String request);
}

class ConcreteHandler extends Handler {
	public void handleRequest(String request) {
		if (Integer.parseInt(request) < 100/* ������������ */) {
			// ��������
			System.out.println("ConcreteHandler.handleRequest()");
		} else {
			this.successor.handleRequest(request); // ת������
		}
	}
}

class ConcreteHandler2 extends Handler {
	public void handleRequest(String request) {
		if (Integer.parseInt(request) < 1000/* ������������ */) {
			// ��������
			System.out.println("ConcreteHandler2.handleRequest()");
		} else {
			this.successor.handleRequest(request); // ת������
		}
	}
}

class ConcreteHandler3 extends Handler {
	public void handleRequest(String request) {
		if (Integer.parseInt(request) < 10000/* ������������ */) {
			// ��������
			System.out.println("ConcreteHandler3.handleRequest()");
		} else {
			this.successor.handleRequest(request); // ת������
		}
	}
}