package com.github.liangyunfeng.pattern.decorator;

public class Client2 {
	
	public static void main(String[] args) {
		Document doc; // 使用抽象构件类型定义
		doc = new PurchaseRequest();
		Approver newDoc; // 使用具体装饰类型定义
		newDoc = new Approver(doc);
		newDoc.display();// 调用原有业务方法
		newDoc.approve();// 调用新增业务方法
	}
	
}

interface Document {
	public void display();
}

class PurchaseRequest implements Document {

	@Override
	public void display() {
		System.out.println("采购文件");
	}

}

class LeaveRequest implements Document {

	@Override
	public void display() {
		System.out.println("请假文件");
	}

}

// 抽象装饰类
class Decorator1 implements Document {
	private Document document;

	public Decorator1(Document document) {
		this.document = document;
	}

	public void display() {
		document.display();
	}
}

// 具体装饰类
class Approver extends Decorator1 {
	public Approver(Document document) {
		super(document);
		System.out.println("增加审批功能！");
	}

	public void approve() {
		System.out.println("审批文件！");
	}

	public void display() {
		super.display();
		approve();
	}
}

// 具体装饰类
class Deletor extends Decorator1 {
	public Deletor(Document document) {
		super(document);
		System.out.println("增加删除功能！");
	}

	public void delete() {
		System.out.println("删除文件！");
	}

	public void display() {
		super.display();
		delete();
	}
}
