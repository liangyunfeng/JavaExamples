package com.github.liangyunfeng.pattern.strategy;

public class Client {
	public static void main(String args[]) {
		MovieTicket mt = new MovieTicket();
		double originalPrice = 60.0;
		double currentPrice;

		mt.setPrice(originalPrice);
		System.out.println("ԭʼ��Ϊ��" + originalPrice);
		System.out.println("---------------------------------");

		Discount discount;
		discount = new StudentDiscount();
		mt.setDiscount(discount); // ע���ۿ۶���

		currentPrice = mt.getPrice();
		System.out.println("�ۺ��Ϊ��" + currentPrice);
	}
}

// ��ӰƱ�ࣺ������
class MovieTicket {
	private double price;
	private Discount discount; // ά��һ���Գ����ۿ��������

	public void setPrice(double price) {
		this.price = price;
	}

	// ע��һ���ۿ������
	public void setDiscount(Discount discount) {
		this.discount = discount;
	}

	public double getPrice() {
		// �����ۿ�����ۿۼۼ��㷽��
		return discount.calculate(this.price);
	}
}

// �ۿ��ࣺ���������
interface Discount {
	public double calculate(double price);
}

// ѧ��Ʊ�ۿ��ࣺ���������
class StudentDiscount implements Discount {
	public double calculate(double price) {
		System.out.println("ѧ��Ʊ��");
		return price * 0.8;
	}
}

// ��ͯƱ�ۿ��ࣺ���������
class ChildrenDiscount implements Discount {
	public double calculate(double price) {
		System.out.println("��ͯƱ��");
		return price - 10;
	}
}

// VIP��ԱƱ�ۿ��ࣺ���������
class VIPDiscount implements Discount {
	public double calculate(double price) {
		System.out.println("VIPƱ��");
		System.out.println("���ӻ��֣�");
		return price * 0.5;
	}
}