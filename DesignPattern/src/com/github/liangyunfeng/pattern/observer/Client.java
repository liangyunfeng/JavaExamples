package com.github.liangyunfeng.pattern.observer;

import java.util.*;

public class Client {
	public static void main(String args[]) {
		// 定义观察目标对象
		AllyControlCenter acc;
		acc = new ConcreteAllyControlCenter("金庸群侠");

		// 定义四个观察者对象
		PlayerObserver player1, player2, player3, player4;

		player1 = new Player("杨过");
		acc.join(player1);

		player2 = new Player("令狐冲");
		acc.join(player2);

		player3 = new Player("张无忌");
		acc.join(player3);

		player4 = new Player("段誉");
		acc.join(player4);

		// 某成员遭受攻击
		player1.beAttacked(acc);
	}
}

// 抽象观察类
interface PlayerObserver {
	public String getName();

	public void setName(String name);

	public void help(); // 声明支援盟友方法

	public void beAttacked(AllyControlCenter acc); // 声明遭受攻击方法
}

// 战队成员类：具体观察者类
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

	// 支援盟友方法的实现
	public void help() {
		System.out.println("坚持住，" + this.name + "来救你！");
	}

	// 遭受攻击方法的实现，当遭受攻击时将调用战队控制中心类的通知方法notifyObserver()来通知盟友
	public void beAttacked(AllyControlCenter acc) {
		System.out.println(this.name + "被攻击！");
		acc.notifyObserver(name);
	}
}

// 战队控制中心类：目标类
abstract class AllyControlCenter {
	protected String allyName; // 战队名称
	protected ArrayList<PlayerObserver> players = new ArrayList<PlayerObserver>(); // 定义一个集合用于存储战队成员

	public void setAllyName(String allyName) {
		this.allyName = allyName;
	}

	public String getAllyName() {
		return this.allyName;
	}

	// 注册方法
	public void join(PlayerObserver obs) {
		System.out.println(obs.getName() + "加入" + this.allyName + "战队！");
		players.add(obs);
	}

	// 注销方法
	public void quit(PlayerObserver obs) {
		System.out.println(obs.getName() + "退出" + this.allyName + "战队！");
		players.remove(obs);
	}

	// 声明抽象通知方法
	public abstract void notifyObserver(String name);
}

// 具体战队控制中心类：具体目标类
class ConcreteAllyControlCenter extends AllyControlCenter {
	public ConcreteAllyControlCenter(String allyName) {
		System.out.println(allyName + "战队组建成功！");
		System.out.println("----------------------------");
		this.allyName = allyName;
	}

	// 实现通知方法
	public void notifyObserver(String name) {
		System.out.println(this.allyName + "战队紧急通知，盟友" + name + "遭受敌人攻击！");
		// 遍历观察者集合，调用每一个盟友（自己除外）的支援方法
		for (Object obs : players) {
			if (!((PlayerObserver) obs).getName().equalsIgnoreCase(name)) {
				((PlayerObserver) obs).help();
			}
		}
	}
}