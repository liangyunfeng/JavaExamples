package com.github.liangyunfeng.pattern.prototype;

public class PrototypeClient {

	/**
	 * 1.Ç³¿ËÂ¡ 2.Éî¿ËÂ¡
	 */
	public static void main(String[] args) throws CloneNotSupportedException {
		Prototype prototype1 = new Prototype();
		prototype1.setName("liangyunfeng");
		prototype1.setAge(18);
		Prototype prototype2 = (Prototype) prototype1.clone();

		System.out.println("prototype1.name : " + prototype1.getName());
		System.out.println("prototype1.age : " + prototype1.getAge());
		System.out.println("prototype2.name : " + prototype2.getName());
		System.out.println("prototype2.age : " + prototype2.getAge());
		System.out.println("prototype1 == prototype2 : "
				+ (prototype1 == prototype2));
		System.out.println("prototype1.name == prototype2.name : "
				+ (prototype1.name == prototype2.name));
		System.out.println("prototype1.name.equals(prototype2.name) : "
				+ (prototype1.name.equals(prototype2.name)));
		System.out.println("prototype1.age == prototype2.age : "
				+ (prototype1.age == prototype2.age));
	}

}

class Prototype implements Cloneable {
	public String name;
	public int age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public Prototype clone() throws CloneNotSupportedException {
		return (Prototype) super.clone();
	}
}