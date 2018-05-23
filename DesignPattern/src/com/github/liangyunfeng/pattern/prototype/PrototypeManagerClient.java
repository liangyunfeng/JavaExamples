package com.github.liangyunfeng.pattern.prototype;

import java.util.Hashtable;

public class PrototypeManagerClient {

	public static void main(String[] args) {
		// 获取原型管理器对象
		PrototypeManager pm = PrototypeManager.getPrototypeManager();
		OfficialDocument doc1, doc2, doc3, doc4;

		doc1 = pm.getOfficialDocument("far");
		doc1.display();
		doc2 = pm.getOfficialDocument("far");
		doc2.display();
		System.out.println(doc1 == doc2);

		doc3 = pm.getOfficialDocument("srs");
		doc3.display();
		doc4 = pm.getOfficialDocument("srs");
		doc4.display();
		System.out.println(doc3 == doc4);
	}

}

// 抽象公文接口，也可定义为抽象类，提供clone()方法的实现，将业务方法声明为抽象方法
interface OfficialDocument extends Cloneable {
	public OfficialDocument clone();

	public void display();
}

// 可行性分析报告(Feasibility Analysis Report)类
class FAR implements OfficialDocument {
	public OfficialDocument clone() {
		OfficialDocument far = null;
		try {
			far = (OfficialDocument) super.clone();
		} catch (CloneNotSupportedException e) {
			System.out.println("不支持复制！");
		}
		return far;
	}

	public void display() {
		System.out.println("《可行性分析报告》");
	}
}

// 软件需求规格说明书(Software Requirements Specification)类
class SRS implements OfficialDocument {
	public OfficialDocument clone() {
		OfficialDocument srs = null;
		try {
			srs = (OfficialDocument) super.clone();
		} catch (CloneNotSupportedException e) {
			System.out.println("不支持复制！");
		}
		return srs;
	}

	public void display() {
		System.out.println("《软件需求规格说明书》");
	}
}

// 原型管理器（使用饿汉式单例实现）
class PrototypeManager {
	// 定义一个Hashtable，用于存储原型对象
	private Hashtable ht = new Hashtable();
	private static PrototypeManager pm = new PrototypeManager();

	// 为Hashtable增加公文对象
	private PrototypeManager() {
		ht.put("far", new FAR());
		ht.put("srs", new SRS());
	}

	// 增加新的公文对象
	public void addOfficialDocument(String key, OfficialDocument doc) {
		ht.put(key, doc);
	}

	// 通过浅克隆获取新的公文对象
	public OfficialDocument getOfficialDocument(String key) {
		return ((OfficialDocument) ht.get(key)).clone();
	}

	public static PrototypeManager getPrototypeManager() {
		return pm;
	}
}
