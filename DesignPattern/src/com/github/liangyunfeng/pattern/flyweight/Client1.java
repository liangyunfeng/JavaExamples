package com.github.liangyunfeng.pattern.flyweight;

import java.util.*;

/**
  从输出结果可以看出，虽然我们获取了三个黑子对象和两个白子对象，但是它们的内存地址相同，也就是说，它们实际上是同一个对象。
  在实现享元工厂类时我们使用了单例模式和简单工厂模式，确保了享元工厂对象的唯一性，并提供工厂方法来向客户端返回享元对象
*/
/*
public class Client1 {
	public static void main(String args[]) {
		IgoChessman black1,black2,black3,white1,white2;
		IgoChessmanFactory factory;
        
        //获取享元工厂对象
		factory = IgoChessmanFactory.getInstance();

        //通过享元工厂获取三颗黑子
		black1 = factory.getIgoChessman("b");
		black2 = factory.getIgoChessman("b");
		black3 = factory.getIgoChessman("b");
		System.out.println("判断两颗黑子是否相同：" + (black1==black2));

        //通过享元工厂获取两颗白子
		white1 = factory.getIgoChessman("w");
		white2 = factory.getIgoChessman("w");
		System.out.println("判断两颗白子是否相同：" + (white1==white2));

        //显示棋子
		black1.display();
		black2.display();
		black3.display();
		white1.display();
		white2.display();
	}
}

//围棋棋子类：抽象享元类
abstract class IgoChessman {
	public abstract String getColor();

	public void display() {
		System.out.println("棋子颜色：" + this.getColor());	
	}
}

//黑色棋子类：具体享元类
class BlackIgoChessman extends IgoChessman {
	public String getColor() {
		return "黑色";
	}	
}

//白色棋子类：具体享元类
class WhiteIgoChessman extends IgoChessman {
	public String getColor() {
		return "白色";
	}
}

//围棋棋子工厂类：享元工厂类，使用单例模式进行设计
class IgoChessmanFactory {
	private static IgoChessmanFactory instance = new IgoChessmanFactory();
	private static Hashtable ht; //使用Hashtable来存储享元对象，充当享元池
	
	private IgoChessmanFactory() {
		ht = new Hashtable();
		IgoChessman black,white;
		black = new BlackIgoChessman();
		ht.put("b",black);
		white = new WhiteIgoChessman();
		ht.put("w",white);
	}
	
  //返回享元工厂类的唯一实例
	public static IgoChessmanFactory getInstance() {
		return instance;
	}
	
  //通过key来获取存储在Hashtable中的享元对象
	public static IgoChessman getIgoChessman(String color) {
		return (IgoChessman)ht.get(color);	
	}
}*/