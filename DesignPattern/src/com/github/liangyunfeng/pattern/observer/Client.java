package com.github.liangyunfeng.pattern.observer;

import java.util.*;

public class Client {
	public static void main(String args[]) {
		// ����۲�Ŀ�����
		AllyControlCenter acc;
		acc = new ConcreteAllyControlCenter("��ӹȺ��");

		// �����ĸ��۲��߶���
		PlayerObserver player1, player2, player3, player4;

		player1 = new Player("���");
		acc.join(player1);

		player2 = new Player("�����");
		acc.join(player2);

		player3 = new Player("���޼�");
		acc.join(player3);

		player4 = new Player("����");
		acc.join(player4);

		// ĳ��Ա���ܹ���
		player1.beAttacked(acc);
	}
}

// ����۲���
interface PlayerObserver {
	public String getName();

	public void setName(String name);

	public void help(); // ����֧Ԯ���ѷ���

	public void beAttacked(AllyControlCenter acc); // �������ܹ�������
}

// ս�ӳ�Ա�ࣺ����۲�����
class Player implements PlayerObserver {
	private String name;

	public Player(String name) {
		this.name = name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	// ֧Ԯ���ѷ�����ʵ��
	public void help() {
		System.out.println("���ס��" + this.name + "�����㣡");
	}

	// ���ܹ���������ʵ�֣������ܹ���ʱ������ս�ӿ����������֪ͨ����notifyObserver()��֪ͨ����
	public void beAttacked(AllyControlCenter acc) {
		System.out.println(this.name + "��������");
		acc.notifyObserver(name);
	}
}

// ս�ӿ��������ࣺĿ����
abstract class AllyControlCenter {
	protected String allyName; // ս������
	protected ArrayList<PlayerObserver> players = new ArrayList<PlayerObserver>(); // ����һ���������ڴ洢ս�ӳ�Ա

	public void setAllyName(String allyName) {
		this.allyName = allyName;
	}

	public String getAllyName() {
		return this.allyName;
	}

	// ע�᷽��
	public void join(PlayerObserver obs) {
		System.out.println(obs.getName() + "����" + this.allyName + "ս�ӣ�");
		players.add(obs);
	}

	// ע������
	public void quit(PlayerObserver obs) {
		System.out.println(obs.getName() + "�˳�" + this.allyName + "ս�ӣ�");
		players.remove(obs);
	}

	// ��������֪ͨ����
	public abstract void notifyObserver(String name);
}

// ����ս�ӿ��������ࣺ����Ŀ����
class ConcreteAllyControlCenter extends AllyControlCenter {
	public ConcreteAllyControlCenter(String allyName) {
		System.out.println(allyName + "ս���齨�ɹ���");
		System.out.println("----------------------------");
		this.allyName = allyName;
	}

	// ʵ��֪ͨ����
	public void notifyObserver(String name) {
		System.out.println(this.allyName + "ս�ӽ���֪ͨ������" + name + "���ܵ��˹�����");
		// �����۲��߼��ϣ�����ÿһ�����ѣ��Լ����⣩��֧Ԯ����
		for (Object obs : players) {
			if (!((PlayerObserver) obs).getName().equalsIgnoreCase(name)) {
				((PlayerObserver) obs).help();
			}
		}
	}
}