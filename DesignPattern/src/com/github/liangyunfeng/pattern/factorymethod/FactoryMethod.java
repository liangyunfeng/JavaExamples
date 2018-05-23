package com.github.liangyunfeng.pattern.factorymethod;

import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import java.io.*;

public class FactoryMethod {

	// Client
	public static void main(String[] args) {
		Factory factory;
		factory = new ConcreteFactoryA(); // ��ͨ������ʵ��
		Product product;
		product = factory.factoryMethod();
		product.methodSame();
		product.methodDiff();
	}

}

abstract class Product {
	public void methodSame() {

	}

	public abstract void methodDiff();
}

class ConcreteProductA extends Product {

	@Override
	public void methodDiff() {

	}
}

class ConcreteProductB extends Product {

	@Override
	public void methodDiff() {

	}
}

interface Factory {
	public Product factoryMethod();
}

class ConcreteFactoryA implements Factory {
	@Override
	public Product factoryMethod() {
		return new ConcreteProductA();
	}
}

class ConcreteFactoryB implements Factory {
	@Override
	public Product factoryMethod() {
		return new ConcreteProductB();
	}
}



/*

<!-- config.xml --> 
<?xml version="1.0"?> <config>
	<className>ConcreteFactoryA</className> 
</config>

// ������XMLUtil.java
class XMLUtil {
	// �÷������ڴ�XML�����ļ�����ȡ������������������һ��ʵ������
	public static Object getBean() {
		try {
			// ����DOM�ĵ�����
			DocumentBuilderFactory dFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder builder = dFactory.newDocumentBuilder();
			Document doc;
			doc = builder.parse(new File("config.xml"));

			// ��ȡ�����������ı��ڵ�
			NodeList nl = doc.getElementsByTagName("className");
			Node classNode = nl.item(0).getFirstChild();
			String cName = classNode.getNodeValue();

			// ͨ����������ʵ�����󲢽��䷵��
			Class c = Class.forName(cName);
			Object obj = c.newInstance();
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}

class Client {
	public static void main(String args[]) {
		ConcreteFactoryA factory;
		Product product;
		factory = (ConcreteFactoryA)XMLUtil.getBean(); //getBean()�ķ�������ΪObject����Ҫ����ǿ������ת��
		product = factory.factoryMethod();
		product.methodSame();
		product.methodDiff();
	}
}

*/