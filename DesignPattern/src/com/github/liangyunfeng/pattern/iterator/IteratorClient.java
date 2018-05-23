package com.github.liangyunfeng.pattern.iterator;

import java.util.ArrayList;
import java.util.List;

public class IteratorClient {

	public static void main(String[] args) {
		Aggregate aggregate = new ConcreteAggregate();
		Iterator iterator = aggregate.createIterator();
		iterator.first();
		if (iterator.hasNext())
			iterator.next();
		iterator.currentItem();
	}

}

interface Iterator {
	public void first(); // 将游标指向第一个元素

	public void next(); // 将游标指向下一个元素

	public boolean hasNext(); // 判断是否存在下一个元素

	public Object currentItem(); // 获取游标指向的当前元素
}

class ConcreteIterator implements Iterator {
	private ConcreteAggregate objects; // 维持一个对具体聚合对象的引用，以便于访问存储在聚合对象中的数据
	private int cursor; // 定义一个游标，用于记录当前访问位置
	List<String> list;

	public ConcreteIterator(ConcreteAggregate objects) {
		this.objects = objects;
		list = objects.getList();
	}

	public void first() {
		cursor = 0;
	}

	public void next() {
		cursor++;
	}

	public boolean hasNext() {
		return cursor < list.size();
	}

	public Object currentItem() {
		return list.get(cursor);
	}
}

interface Aggregate {
	Iterator createIterator();
}

class ConcreteAggregate implements Aggregate {
	List<String> list = new ArrayList<String>();

	public Iterator createIterator() {
		return new ConcreteIterator(this);
	}

	public String get(int index) {
		return list.get(index);
	}

	public void add(String s) {
		list.add(s);
	}

	public List<String> getList() {
		return list;
	}
}
