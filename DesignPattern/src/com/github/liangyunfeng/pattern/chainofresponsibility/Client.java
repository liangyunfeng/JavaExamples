package com.github.liangyunfeng.pattern.chainofresponsibility;

public class Client {
	public static void main(String[] args) {
		Approver wjzhang, gyang, jguo, meeting;
		wjzhang = new Director("���޼�");
		gyang = new VicePresident("���");
		jguo = new President("����");
		meeting = new Congress("���»�");

		// ����ְ����
		wjzhang.setSuccessor(gyang);
		gyang.setSuccessor(jguo);
		jguo.setSuccessor(meeting);

		// �����ɹ���
		PurchaseRequest pr1 = new PurchaseRequest(45000, 10001, "�������콣");
		wjzhang.processRequest(pr1);

		PurchaseRequest pr2 = new PurchaseRequest(60000, 10002, "���򡶿������䡷");
		wjzhang.processRequest(pr2);

		PurchaseRequest pr3 = new PurchaseRequest(160000, 10003, "���򡶽�վ���");
		wjzhang.processRequest(pr3);

		PurchaseRequest pr4 = new PurchaseRequest(800000, 10004, "�����һ���");
		wjzhang.processRequest(pr4);
	}
}

// �ɹ�����������
class PurchaseRequest {
	private double amount; // �ɹ����
	private int number; // �ɹ������
	private String purpose; // �ɹ�Ŀ��

	public PurchaseRequest(double amount, int number, String purpose) {
		this.amount = amount;
		this.number = number;
		this.purpose = purpose;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getAmount() {
		return this.amount;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getNumber() {
		return this.number;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public String getPurpose() {
		return this.purpose;
	}
}

// �������ࣺ��������
abstract class Approver {
	protected Approver successor; // �����̶���
	protected String name; // ����������

	public Approver(String name) {
		this.name = name;
	}

	// ���ú����
	public void setSuccessor(Approver successor) {
		this.successor = successor;
	}

	// ������������
	public abstract void processRequest(PurchaseRequest request);
}

// �����ࣺ���崦����
class Director extends Approver {
	public Director(String name) {
		super(name);
	}

	// ������������
	public void processRequest(PurchaseRequest request) {
		if (request.getAmount() < 50000) {
			System.out.println("����" + this.name + "�����ɹ�����"
					+ request.getNumber() + "����" + request.getAmount()
					+ "Ԫ���ɹ�Ŀ�ģ�" + request.getPurpose() + "��"); // ��������
		} else {
			this.successor.processRequest(request); // ת������
		}
	}
}

// �����³��ࣺ���崦����
class VicePresident extends Approver {
	public VicePresident(String name) {
		super(name);
	}

	// ������������
	public void processRequest(PurchaseRequest request) {
		if (request.getAmount() < 100000) {
			System.out.println("�����³�" + this.name + "�����ɹ�����"
					+ request.getNumber() + "����" + request.getAmount()
					+ "Ԫ���ɹ�Ŀ�ģ�" + request.getPurpose() + "��"); // ��������
		} else {
			this.successor.processRequest(request); // ת������
		}
	}
}

// ���³��ࣺ���崦����
class President extends Approver {
	public President(String name) {
		super(name);
	}

	// ������������
	public void processRequest(PurchaseRequest request) {
		if (request.getAmount() < 500000) {
			System.out.println("���³�" + this.name + "�����ɹ�����"
					+ request.getNumber() + "����" + request.getAmount()
					+ "Ԫ���ɹ�Ŀ�ģ�" + request.getPurpose() + "��"); // ��������
		} else {
			this.successor.processRequest(request); // ת������
		}
	}
}

// ���»��ࣺ���崦����
class Congress extends Approver {
	public Congress(String name) {
		super(name);
	}

	// ������������
	public void processRequest(PurchaseRequest request) {
		System.out.println("�ٿ����»������ɹ�����" + request.getNumber() + "����"
				+ request.getAmount() + "Ԫ���ɹ�Ŀ�ģ�" + request.getPurpose() + "��"); // ��������
	}
}