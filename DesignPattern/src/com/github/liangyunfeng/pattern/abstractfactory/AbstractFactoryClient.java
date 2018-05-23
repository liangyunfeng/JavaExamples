package com.github.liangyunfeng.pattern.abstractfactory;

import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import java.io.*;

public class AbstractFactoryClient {

	// Client
	public static void main(String[] args) {
		// 使用抽象层定义
		SkinFactory factory;
		Button bt;
		TextField tf;
		ComboBox cb;
		factory = (SkinFactory) XMLUtil.getBean();
		bt = factory.createButton();
		tf = factory.createTextField();
		cb = factory.createComboBox();
		bt.display();
		tf.display();
		cb.display();

		// ===================================

		AbstractProductA productA1;
		AbstractProductB productB1;
		Factory f;
		f = new ConcreteFactory1();
		productA1 = f.createProductA();
		productB1 = f.createProductB();
		productA1.display();
		productB1.display();
	}

}

abstract class AbstractProductA {
	public abstract void display();
}

class ConcreteProductA1 extends AbstractProductA {
	@Override
	public void display() {

	}
}

class ConcreteProductA2 extends AbstractProductA {
	@Override
	public void display() {

	}
}

abstract class AbstractProductB {
	public abstract void display();
}

class ConcreteProductB1 extends AbstractProductB {
	@Override
	public void display() {

	}
}

class ConcreteProductB2 extends AbstractProductB {
	@Override
	public void display() {

	}
}

interface Factory {
	public AbstractProductA createProductA();

	public AbstractProductB createProductB();
}

class ConcreteFactory1 implements Factory {

	@Override
	public AbstractProductA createProductA() {
		return new ConcreteProductA1();
	}

	@Override
	public AbstractProductB createProductB() {
		return new ConcreteProductB1();
	}
}

class ConcreteFactory2 implements Factory {

	@Override
	public AbstractProductA createProductA() {
		return new ConcreteProductA2();
	}

	@Override
	public AbstractProductB createProductB() {
		return new ConcreteProductB2();
	}
}





// ==============================================================================================




abstract class Button {
	public abstract void display();
}

class SpringButton extends Button {
	@Override
	public void display() {
		System.out.println("显示浅绿色按钮。");
	}
}

class SummerButton extends Button {
	@Override
	public void display() {
		System.out.println("显示浅蓝色按钮。");
	}
}

abstract class TextField {
	public abstract void display();
}

class SpringTextField extends TextField {
	@Override
	public void display() {
		System.out.println("显示绿色边框文本框。");
	}
}

class SummerTextField extends TextField {
	@Override
	public void display() {
		System.out.println("显示蓝色边框文本框。");
	}
}

abstract class ComboBox {
	public abstract void display();
}

class SpringComboBox extends ComboBox {
	@Override
	public void display() {
		System.out.println("显示绿色边框组合框。");
	}
}

class SummerComboBox extends ComboBox {
	@Override
	public void display() {
		System.out.println("显示蓝色边框组合框。");
	}
}

interface SkinFactory {
	public Button createButton();

	public TextField createTextField();

	public ComboBox createComboBox();
}

class SpringSkinFactory implements SkinFactory {

	@Override
	public Button createButton() {
		return new SpringButton();
	}

	@Override
	public TextField createTextField() {
		return new SpringTextField();
	}

	@Override
	public ComboBox createComboBox() {
		return new SpringComboBox();
	}

}

class SummerSkinFactory implements SkinFactory {

	@Override
	public Button createButton() {
		return new SummerButton();
	}

	@Override
	public TextField createTextField() {
		return new SummerTextField();
	}

	@Override
	public ComboBox createComboBox() {
		return new SummerComboBox();
	}

}

class XMLUtil {
	// 该方法用于从XML配置文件中提取具体类类名，并返回一个实例对象
	public static Object getBean() {
		try {
			// 创建文档对象
			DocumentBuilderFactory dFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder builder = dFactory.newDocumentBuilder();
			Document doc;
			doc = builder.parse(new File(XMLUtil.class.getResource("")
					.getPath() + "config.xml"));

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
