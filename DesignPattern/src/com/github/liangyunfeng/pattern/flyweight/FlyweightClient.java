package com.github.liangyunfeng.pattern.flyweight;

import java.util.HashMap;

public class FlyweightClient {
/*
享元模式以共享的方式高效地支持大量细粒度对象的重用，享元对象能做到共享的关键是区分了内部状态(Intrinsic State)和外部状态(Extrinsic State)。
下面将对享元的内部状态和外部状态进行简单的介绍：
	(1)	内部状态是存储在享元对象内部并且不会随环境改变而改变的状态，内部状态可以共享。如字符的内容，不会随外部环境的变化而变化，
		无论在任何环境下字符“a”始终是“a”，都不会变成“b”。
	(2)	外部状态是随环境改变而改变的、不可以共享的状态。享元对象的外部状态通常由客户端保存，并在享元对象被创建之后，需要使用的时候再传入到享元对象内部。
		一个外部状态与另一个外部状态之间是相互独立的。如字符的颜色，可以在不同的地方有不同的颜色，例如有的“a”是红色的，有的“a”是绿色的，
		字符的大小也是如此，有的“a”是五号字，有的“a”是四号字。而且字符的颜色和大小是两个独立的外部状态，它们可以独立变化，相互之间没有影响，
		客户端可以在使用时将外部状态注入享元对象中。
正因为区分了内部状态和外部状态，我们可以将具有相同内部状态的对象存储在享元池中，享元池中的对象是可以实现共享的，需要的时候就将对象从享元池中取出，
实现对象的复用。通过向取出的对象注入不同的外部状态，可以得到一系列相似的对象，而这些对象在内存中实际上只存储一份。

享元模式定义如下：
享元模式(Flyweight Pattern)：运用共享技术有效地支持大量细粒度对象的复用。
系统只使用少量的对象，而这些对象都很相似，状态变化很小，可以实现对象的多次复用。
由于享元模式要求能够共享的对象必须是细粒度对象，因此它又称为轻量级模式，它是一种对象结构型模式。
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
	// 外部状态extrinsicState在使用时由外部设置，不保存在享元对象中，即使是同一个对象，在每一次调用时也可以传入不同的外部状态
	public void operation(String extrinsicState);

}

class ConcreteFlyweight implements Flyweight {

	// 内部状态intrinsicState作为成员变量，同一个享元对象其内部状态是一致的
	private String intrinsicState;

	public ConcreteFlyweight(String intrinsicState) {
		this.intrinsicState = intrinsicState;
	}

	// 外部状态extrinsicState在使用时由外部设置，不保存在享元对象中，即使是同一个对象，在每一次调用时也可以传入不同的外部状态
	public void operation(String extrinsicState) {
		// ......
	}

}

class FlyweightFactory {

	// 定义一个HashMap用于存储享元对象，实现享元池
	private HashMap flyweights = new HashMap();

	public Flyweight getFlyweight(String key) {
		// 如果对象存在，则直接从享元池获取
		if (flyweights.containsKey(key)) {
			return (Flyweight) flyweights.get(key);
		}
		// 如果对象不存在，先创建一个新的对象添加到享元池中，然后返回
		else {
			Flyweight fw = new ConcreteFlyweight("");
			flyweights.put(key, fw);
			return fw;
		}
	}

}
