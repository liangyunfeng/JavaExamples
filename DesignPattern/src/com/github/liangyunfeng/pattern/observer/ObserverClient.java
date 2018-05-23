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
	// ����һ���۲��߼������ڴ洢���й۲��߶���
	protected ArrayList<Observer> observers = new ArrayList<Observer>();

	// ע�᷽����������۲��߼���������һ���۲���
	public void attach(Observer observer) {
		observers.add(observer);
	}

	// ע�������������ڹ۲��߼�����ɾ��һ���۲���
	public void detach(Observer observer) {
		observers.remove(observer);
	}

	// ��������֪ͨ����
	public abstract void notifyObservers();
}

class ConcreteSubject extends Subject {
	// ʵ��֪ͨ����
	public void notifyObservers() {
		// �����۲��߼��ϣ�����ÿһ���۲��ߵ���Ӧ����
		for (Observer obs : observers) {
			obs.update();
		}
	}
}

interface Observer {
	// ������Ӧ����
	public void update();
}

class ConcreteObserver implements Observer {
	// ʵ����Ӧ����
	public void update() {
		// ������Ӧ����
	}
}