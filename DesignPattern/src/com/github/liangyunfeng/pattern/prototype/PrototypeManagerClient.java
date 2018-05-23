package com.github.liangyunfeng.pattern.prototype;

import java.util.Hashtable;

public class PrototypeManagerClient {

	public static void main(String[] args) {
		// ��ȡԭ�͹���������
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

// �����Ľӿڣ�Ҳ�ɶ���Ϊ�����࣬�ṩclone()������ʵ�֣���ҵ�񷽷�����Ϊ���󷽷�
interface OfficialDocument extends Cloneable {
	public OfficialDocument clone();

	public void display();
}

// �����Է�������(Feasibility Analysis Report)��
class FAR implements OfficialDocument {
	public OfficialDocument clone() {
		OfficialDocument far = null;
		try {
			far = (OfficialDocument) super.clone();
		} catch (CloneNotSupportedException e) {
			System.out.println("��֧�ָ��ƣ�");
		}
		return far;
	}

	public void display() {
		System.out.println("�������Է������桷");
	}
}

// ���������˵����(Software Requirements Specification)��
class SRS implements OfficialDocument {
	public OfficialDocument clone() {
		OfficialDocument srs = null;
		try {
			srs = (OfficialDocument) super.clone();
		} catch (CloneNotSupportedException e) {
			System.out.println("��֧�ָ��ƣ�");
		}
		return srs;
	}

	public void display() {
		System.out.println("�����������˵���顷");
	}
}

// ԭ�͹�������ʹ�ö���ʽ����ʵ�֣�
class PrototypeManager {
	// ����һ��Hashtable�����ڴ洢ԭ�Ͷ���
	private Hashtable ht = new Hashtable();
	private static PrototypeManager pm = new PrototypeManager();

	// ΪHashtable���ӹ��Ķ���
	private PrototypeManager() {
		ht.put("far", new FAR());
		ht.put("srs", new SRS());
	}

	// �����µĹ��Ķ���
	public void addOfficialDocument(String key, OfficialDocument doc) {
		ht.put(key, doc);
	}

	// ͨ��ǳ��¡��ȡ�µĹ��Ķ���
	public OfficialDocument getOfficialDocument(String key) {
		return ((OfficialDocument) ht.get(key)).clone();
	}

	public static PrototypeManager getPrototypeManager() {
		return pm;
	}
}
