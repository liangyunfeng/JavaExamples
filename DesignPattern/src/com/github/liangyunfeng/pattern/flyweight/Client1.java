package com.github.liangyunfeng.pattern.flyweight;

import java.util.*;

/**
  �����������Կ�������Ȼ���ǻ�ȡ���������Ӷ�����������Ӷ��󣬵������ǵ��ڴ��ַ��ͬ��Ҳ����˵������ʵ������ͬһ������
  ��ʵ����Ԫ������ʱ����ʹ���˵���ģʽ�ͼ򵥹���ģʽ��ȷ������Ԫ���������Ψһ�ԣ����ṩ������������ͻ��˷�����Ԫ����
*/
/*
public class Client1 {
	public static void main(String args[]) {
		IgoChessman black1,black2,black3,white1,white2;
		IgoChessmanFactory factory;
        
        //��ȡ��Ԫ��������
		factory = IgoChessmanFactory.getInstance();

        //ͨ����Ԫ������ȡ���ź���
		black1 = factory.getIgoChessman("b");
		black2 = factory.getIgoChessman("b");
		black3 = factory.getIgoChessman("b");
		System.out.println("�ж����ź����Ƿ���ͬ��" + (black1==black2));

        //ͨ����Ԫ������ȡ���Ű���
		white1 = factory.getIgoChessman("w");
		white2 = factory.getIgoChessman("w");
		System.out.println("�ж����Ű����Ƿ���ͬ��" + (white1==white2));

        //��ʾ����
		black1.display();
		black2.display();
		black3.display();
		white1.display();
		white2.display();
	}
}

//Χ�������ࣺ������Ԫ��
abstract class IgoChessman {
	public abstract String getColor();

	public void display() {
		System.out.println("������ɫ��" + this.getColor());	
	}
}

//��ɫ�����ࣺ������Ԫ��
class BlackIgoChessman extends IgoChessman {
	public String getColor() {
		return "��ɫ";
	}	
}

//��ɫ�����ࣺ������Ԫ��
class WhiteIgoChessman extends IgoChessman {
	public String getColor() {
		return "��ɫ";
	}
}

//Χ�����ӹ����ࣺ��Ԫ�����࣬ʹ�õ���ģʽ�������
class IgoChessmanFactory {
	private static IgoChessmanFactory instance = new IgoChessmanFactory();
	private static Hashtable ht; //ʹ��Hashtable���洢��Ԫ���󣬳䵱��Ԫ��
	
	private IgoChessmanFactory() {
		ht = new Hashtable();
		IgoChessman black,white;
		black = new BlackIgoChessman();
		ht.put("b",black);
		white = new WhiteIgoChessman();
		ht.put("w",white);
	}
	
  //������Ԫ�������Ψһʵ��
	public static IgoChessmanFactory getInstance() {
		return instance;
	}
	
  //ͨ��key����ȡ�洢��Hashtable�е���Ԫ����
	public static IgoChessman getIgoChessman(String color) {
		return (IgoChessman)ht.get(color);	
	}
}*/