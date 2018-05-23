package com.github.liangyunfeng.pattern.flyweight;

import java.util.HashMap;

public class FlyweightClient {
/*
��Ԫģʽ�Թ���ķ�ʽ��Ч��֧�ִ���ϸ���ȶ�������ã���Ԫ��������������Ĺؼ����������ڲ�״̬(Intrinsic State)���ⲿ״̬(Extrinsic State)��
���潫����Ԫ���ڲ�״̬���ⲿ״̬���м򵥵Ľ��ܣ�
	(1)	�ڲ�״̬�Ǵ洢����Ԫ�����ڲ����Ҳ����滷���ı���ı��״̬���ڲ�״̬���Թ������ַ������ݣ��������ⲿ�����ı仯���仯��
		�������κλ������ַ���a��ʼ���ǡ�a�����������ɡ�b����
	(2)	�ⲿ״̬���滷���ı���ı�ġ������Թ����״̬����Ԫ������ⲿ״̬ͨ���ɿͻ��˱��棬������Ԫ���󱻴���֮����Ҫʹ�õ�ʱ���ٴ��뵽��Ԫ�����ڲ���
		һ���ⲿ״̬����һ���ⲿ״̬֮�����໥�����ġ����ַ�����ɫ�������ڲ�ͬ�ĵط��в�ͬ����ɫ�������еġ�a���Ǻ�ɫ�ģ��еġ�a������ɫ�ģ�
		�ַ��Ĵ�СҲ����ˣ��еġ�a��������֣��еġ�a�����ĺ��֡������ַ�����ɫ�ʹ�С�������������ⲿ״̬�����ǿ��Զ����仯���໥֮��û��Ӱ�죬
		�ͻ��˿�����ʹ��ʱ���ⲿ״̬ע����Ԫ�����С�
����Ϊ�������ڲ�״̬���ⲿ״̬�����ǿ��Խ�������ͬ�ڲ�״̬�Ķ���洢����Ԫ���У���Ԫ���еĶ����ǿ���ʵ�ֹ���ģ���Ҫ��ʱ��ͽ��������Ԫ����ȡ����
ʵ�ֶ���ĸ��á�ͨ����ȡ���Ķ���ע�벻ͬ���ⲿ״̬�����Եõ�һϵ�����ƵĶ��󣬶���Щ�������ڴ���ʵ����ֻ�洢һ�ݡ�

��Ԫģʽ�������£�
��Ԫģʽ(Flyweight Pattern)�����ù�������Ч��֧�ִ���ϸ���ȶ���ĸ��á�
ϵͳֻʹ�������Ķ��󣬶���Щ���󶼺����ƣ�״̬�仯��С������ʵ�ֶ���Ķ�θ��á�
������ԪģʽҪ���ܹ�����Ķ��������ϸ���ȶ���������ֳ�Ϊ������ģʽ������һ�ֶ���ṹ��ģʽ��
 */
	public static void main(String[] args) {
		FlyweightFactory factory = new FlyweightFactory();
		
		factory.getFlyweight("key1");
		factory.getFlyweight("key2");
		factory.getFlyweight("key3");
		factory.getFlyweight("key1");
		factory.getFlyweight("key1");
	}

}

interface Flyweight {
	// �ⲿ״̬extrinsicState��ʹ��ʱ���ⲿ���ã�����������Ԫ�����У���ʹ��ͬһ��������ÿһ�ε���ʱҲ���Դ��벻ͬ���ⲿ״̬
	public void operation(String extrinsicState);

}

class ConcreteFlyweight implements Flyweight {

	// �ڲ�״̬intrinsicState��Ϊ��Ա������ͬһ����Ԫ�������ڲ�״̬��һ�µ�
	private String intrinsicState;

	public ConcreteFlyweight(String intrinsicState) {
		this.intrinsicState = intrinsicState;
	}

	// �ⲿ״̬extrinsicState��ʹ��ʱ���ⲿ���ã�����������Ԫ�����У���ʹ��ͬһ��������ÿһ�ε���ʱҲ���Դ��벻ͬ���ⲿ״̬
	public void operation(String extrinsicState) {
		// ......
	}

}

class FlyweightFactory {

	// ����һ��HashMap���ڴ洢��Ԫ����ʵ����Ԫ��
	private HashMap flyweights = new HashMap();

	public Flyweight getFlyweight(String key) {
		// ���������ڣ���ֱ�Ӵ���Ԫ�ػ�ȡ
		if (flyweights.containsKey(key)) {
			return (Flyweight) flyweights.get(key);
		}
		// ������󲻴��ڣ��ȴ���һ���µĶ�����ӵ���Ԫ���У�Ȼ�󷵻�
		else {
			Flyweight fw = new ConcreteFlyweight("");
			flyweights.put(key, fw);
			return fw;
		}
	}

}
