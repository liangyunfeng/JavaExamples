package com.github.liangyunfeng.pattern.decorator;

public class Client2 {
	
	public static void main(String[] args) {
		Document doc; // ʹ�ó��󹹼����Ͷ���
		doc = new PurchaseRequest();
		Approver newDoc; // ʹ�þ���װ�����Ͷ���
		newDoc = new Approver(doc);
		newDoc.display();// ����ԭ��ҵ�񷽷�
		newDoc.approve();// ��������ҵ�񷽷�
	}
	
}

interface Document {
	public void display();
}

class PurchaseRequest implements Document {

	@Override
	public void display() {
		System.out.println("�ɹ��ļ�");
	}

}

class LeaveRequest implements Document {

	@Override
	public void display() {
		System.out.println("����ļ�");
	}

}

// ����װ����
class Decorator1 implements Document {
	private Document document;

	public Decorator1(Document document) {
		this.document = document;
	}

	public void display() {
		document.display();
	}
}

// ����װ����
class Approver extends Decorator1 {
	public Approver(Document document) {
		super(document);
		System.out.println("�����������ܣ�");
	}

	public void approve() {
		System.out.println("�����ļ���");
	}

	public void display() {
		super.display();
		approve();
	}
}

// ����װ����
class Deletor extends Decorator1 {
	public Deletor(Document document) {
		super(document);
		System.out.println("����ɾ�����ܣ�");
	}

	public void delete() {
		System.out.println("ɾ���ļ���");
	}

	public void display() {
		super.display();
		delete();
	}
}
