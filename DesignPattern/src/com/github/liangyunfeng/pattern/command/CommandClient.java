package com.github.liangyunfeng.pattern.command;

public class CommandClient {

	public static void main(String[] args) {
		Command command = new ConcreteCommand();
		Invoker invoker = new Invoker(command);
		invoker.call();
	}

}

abstract class Command {
	public abstract void execute();
}

class Invoker {
	private Command command;

	// ����ע��
	public Invoker(Command command) {
		this.command = command;
	}

	// ��ֵע��
	public void setCommand(Command command) {
		this.command = command;
	}

	// ҵ�񷽷������ڵ����������execute()����
	public void call() {
		System.out.println("Invoker.call()");
		command.execute();
	}
}

class ConcreteCommand extends Command {
	private Receiver receiver = new Receiver();; // ά��һ������������߶��������

	public void execute() {
		receiver.action(); // ������������ߵ�ҵ������action()
	}

}

class Receiver {
	public void action() {
		// �������
		System.out.println("Receiver.action()");
	}
}