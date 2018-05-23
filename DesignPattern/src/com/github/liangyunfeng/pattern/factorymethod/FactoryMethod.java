package com.github.liangyunfeng.pattern.factorymethod;

import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import java.io.*;

public class FactoryMethod {

	// Client
	public static void main(String[] args) {
		Factory factory;
		factory = new ConcreteFactoryA(); // 可通过配置实现
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

// 工具类XMLUtil.java
class XMLUtil {
	// 该方法用于从XML配置文件中提取具体类类名，并返回一个实例对象
	public static Object getBean() {
		try {
			// 创建DOM文档对象
			DocumentBuilderFactory dFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder builder = dFactory.newDocumentBuilder();
			Document doc;
			doc = builder.parse(new File("config.xml"));

			// 获取包含类名的文本节点
			NodeList nl = doc.getElementsByTagName("className");
			Node classNode = nl.item(0).getFirstChild();
			String cName = classNode.getNodeValue();

			// 通过类名生成实例对象并将其返回
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
		factory = (ConcreteFactoryA)XMLUtil.getBean(); //getBean()的返回类型为Object，需要进行强制类型转换
		product = factory.factoryMethod();
		product.methodSame();
		product.methodDiff();
	}
}

*/