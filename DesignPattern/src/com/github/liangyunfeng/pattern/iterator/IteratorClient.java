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
	public void first(); // ���α�ָ���һ��Ԫ��

	public void next(); // ���α�ָ����һ��Ԫ��

	public boolean hasNext(); // �ж��Ƿ������һ��Ԫ��

	public Object currentItem(); // ��ȡ�α�ָ��ĵ�ǰԪ��
}

class ConcreteIterator implements Iterator {
	private ConcreteAggregate objects; // ά��һ���Ծ���ۺ϶�������ã��Ա��ڷ��ʴ洢�ھۺ϶����е�����
	private int cursor; // ����һ���α꣬���ڼ�¼��ǰ����λ��
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
