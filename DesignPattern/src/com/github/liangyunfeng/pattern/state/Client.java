package com.github.liangyunfeng.pattern.state;

//ʹ��״̬��ʵ��״̬ת��
public class Client {
	public static void main(String args[]) {
		Account acc = new Account("����", 0.0);
		acc.deposit(1000);
		acc.withdraw(2000);
		acc.deposit(3000);
		acc.withdraw(4000);
		acc.withdraw(1000);
		acc.computeInterest();
	}
}

// �����˻���������
class Account {
	private AccountState state; // ά��һ���Գ���״̬���������
	private String owner; // ������
	private double balance = 0; // �˻����

	public Account(String owner, double init) {
		this.owner = owner;
		this.balance = balance;
		this.state = new NormalState(this); // ���ó�ʼ״̬
		System.out.println(this.owner + "��������ʼ���Ϊ" + init);
		System.out.println("---------------------------------------------");
	}

	public double getBalance() {
		return this.balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public void setState(AccountState state) {
		this.state = state;
	}

	public void deposit(double amount) {
		System.out.println(this.owner + "���" + amount);
		state.deposit(amount); // ����״̬�����deposit()����
		System.out.println("�������Ϊ" + this.balance);
		System.out.println("�����ʻ�״̬Ϊ" + this.state.getClass().getName());
		System.out.println("---------------------------------------------");
	}

	public void withdraw(double amount) {
		System.out.println(this.owner + "ȡ��" + amount);
		state.withdraw(amount); // ����״̬�����withdraw()����
		System.out.println("�������Ϊ" + this.balance);
		System.out.println("�����ʻ�״̬Ϊ" + this.state.getClass().getName());
		System.out.println("---------------------------------------------");
	}

	public void computeInterest() {
		state.computeInterest(); // ����״̬�����computeInterest()����
	}
}

// ����״̬��
abstract class AccountState {
	protected Account acc;

	public abstract void deposit(double amount);

	public abstract void withdraw(double amount);

	public abstract void computeInterest();

	public abstract void stateCheck();
}

// ����״̬������״̬��
class NormalState extends AccountState {
	public NormalState(Account acc) {
		this.acc = acc;
	}

	public NormalState(AccountState state) {
		this.acc = state.acc;
	}

	public void deposit(double amount) {
		acc.setBalance(acc.getBalance() + amount);
		stateCheck();
	}

	public void withdraw(double amount) {
		acc.setBalance(acc.getBalance() - amount);
		stateCheck();
	}

	public void computeInterest() {
		System.out.println("����״̬������֧����Ϣ��");
	}

	// ״̬ת��
	public void stateCheck() {
		if (acc.getBalance() > -2000 && acc.getBalance() <= 0) {
			acc.setState(new OverdraftState(this));
		} else if (acc.getBalance() == -2000) {
			acc.setState(new RestrictedState(this));
		} else if (acc.getBalance() < -2000) {
			System.out.println("�������ޣ�");
		}
	}
}

// ͸֧״̬������״̬��
class OverdraftState extends AccountState {
	public OverdraftState(AccountState state) {
		this.acc = state.acc;
	}

	public void deposit(double amount) {
		acc.setBalance(acc.getBalance() + amount);
		stateCheck();
	}

	public void withdraw(double amount) {
		acc.setBalance(acc.getBalance() - amount);
		stateCheck();
	}

	public void computeInterest() {
		System.out.println("������Ϣ��");
	}

	// ״̬ת��
	public void stateCheck() {
		if (acc.getBalance() > 0) {
			acc.setState(new NormalState(this));
		} else if (acc.getBalance() == -2000) {
			acc.setState(new RestrictedState(this));
		} else if (acc.getBalance() < -2000) {
			System.out.println("�������ޣ�");
		}
	}
}

// ����״̬������״̬��
class RestrictedState extends AccountState {
	public RestrictedState(AccountState state) {
		this.acc = state.acc;
	}

	public void deposit(double amount) {
		acc.setBalance(acc.getBalance() + amount);
		stateCheck();
	}

	public void withdraw(double amount) {
		System.out.println("�ʺ����ޣ�ȡ��ʧ��");
	}

	public void computeInterest() {
		System.out.println("������Ϣ��");
	}

	// ״̬ת��
	public void stateCheck() {
		if (acc.getBalance() > 0) {
			acc.setState(new NormalState(this));
		} else if (acc.getBalance() > -2000) {
			acc.setState(new OverdraftState(this));
		}
	}
}