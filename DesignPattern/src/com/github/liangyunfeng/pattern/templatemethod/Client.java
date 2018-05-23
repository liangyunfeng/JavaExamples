package com.github.liangyunfeng.pattern.templatemethod;

public class Client {

	public static void main(String[] args) {
		Account account;
		account = new CurrentAccount();
		account.handle("���޼�", "123456");
	}

}

abstract class Account {
	// ���������������巽��
	public boolean validate(String account, String password) {
		System.out.println("�˺ţ�" + account);
		System.out.println("���룺" + password);
		// ģ���¼
		if (account.equals("���޼�") && password.equals("123456")) {
			return true;
		} else {
			return false;
		}
	}

	// ���������������󷽷�
	public abstract void calculateInterest();

	// ���������������巽��
	public void display() {
		System.out.println("��ʾ��Ϣ��");
	}

	// ģ�巽��
	public void handle(String account, String password) {
		if (!validate(account, password)) {
			System.out.println("�˻����������");
			return;
		}
		calculateInterest();
		display();
	}
}

class CurrentAccount extends Account {
	// ���Ǹ���ĳ����������
	@Override
	public void calculateInterest() {
		System.out.println("���������ʼ�����Ϣ��");
	}
}

class SavingAccount extends Account {
	// ���Ǹ���ĳ����������
	@Override
	public void calculateInterest() {
		System.out.println("���������ʼ�����Ϣ��");
	}
}
