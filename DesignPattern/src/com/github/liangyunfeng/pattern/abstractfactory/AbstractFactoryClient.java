package com.github.liangyunfeng.pattern.abstractfactory;

import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import java.io.*;

public class AbstractFactoryClient {

	// Client
	public static void main(String[] args) {
		// ʹ�ó���㶨��
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
		System.out.println("��ʾǳ��ɫ��ť��");
	}
}

class SummerButton extends Button {
	@Override
	public void display() {
		System.out.println("��ʾǳ��ɫ��ť��");
	}
}

abstract class TextField {
	public abstract void display();
}

class SpringTextField extends TextField {
	@Override
	public void display() {
		System.out.println("��ʾ��ɫ�߿��ı���");
	}
}

class SummerTextField extends TextField {
	@Override
	public void display() {
		System.out.println("��ʾ��ɫ�߿��ı���");
	}
}

abstract class ComboBox {
	public abstract void display();
}

class SpringComboBox extends ComboBox {
	@Override
	public void display() {
		System.out.println("��ʾ��ɫ�߿���Ͽ�");
	}
}

class SummerComboBox extends ComboBox {
	@Override
	public void display() {
		System.out.println("��ʾ��ɫ�߿���Ͽ�");
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
	// �÷������ڴ�XML�����ļ�����ȡ������������������һ��ʵ������
	public static Object getBean() {
		try {
			// �����ĵ�����
			DocumentBuilderFactory dFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder builder = dFactory.newDocumentBuilder();
			Document doc;
			doc = builder.parse(new File(XMLUtil.class.getResource("")
					.getPath() + "config.xml"));

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
