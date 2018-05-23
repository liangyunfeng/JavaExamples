package com.github.liangyunfeng.pattern.observer;

import java.util.*;

public class ObserverClient {

	public static void main(String[] args) {
		Observer observer1 = new ConcreteObserver();
		Observer observer2 = new ConcreteObserver();
		Observer observer3 = new ConcreteObserver();
		Subject subject = new ConcreteSubject();
		subject.attach(observer1);
		subject.attach(observer2);
		subject.attach(observer3);
		subject.notifyObservers();
	}

}

abstract class Subject {
	// 定义一个观察者集合用于存储所有观察者对象
	protected ArrayList<Observer> observers = new ArrayList<Observer>();

	// 注册方法，用于向观察者集合中增加一个观察者
	public void attach(Observer observer) {
		observers.add(observer);
	}

	// 注销方法，用于在观察者集合中删除一个观察者
	public void detach(Observer observer) {
		observers.remove(observer);
	}

	// 声明抽象通知方法
	public abstract void notifyObservers();
}

class ConcreteSubject extends Subject {
	// 实现通知方法
	public void notifyObservers() {
		// 遍历观察者集合，调用每一个观察者的响应方法
		for (Observer obs : observers) {
			obs.update();
		}
	}
}

interface Observer {
	// 声明响应方法
	public void update();
}

class ConcreteObserver implements Observer {
	// 实现响应方法
	public void update() {
		// 具体响应代码
	}
}