package com.github.liangyunfeng.pattern.visitor;

import java.util.*;

public class Client {
	public static void main(String args[]) {
		EmployeeList list = new EmployeeList();
		Employee fte1, fte2, fte3, pte1, pte2;

		fte1 = new FulltimeEmployee("���޼�", 3200.00, 45);
		fte2 = new FulltimeEmployee("���", 2000.00, 40);
		fte3 = new FulltimeEmployee("����", 2400.00, 38);
		pte1 = new ParttimeEmployee("���߹�", 80.00, 20);
		pte2 = new ParttimeEmployee("����", 60.00, 18);

		list.addEmployee(fte1);
		list.addEmployee(fte2);
		list.addEmployee(fte3);
		list.addEmployee(pte1);
		list.addEmployee(pte2);

		Department dep;
		dep = new FADepartment();
		list.accept(dep);
	}
}

// Ա���ࣺ����Ԫ����
interface Employee {
	public void accept(Department handler); // ����һ����������߷���
}

// ȫְԱ���ࣺ����Ԫ����
class FulltimeEmployee implements Employee {
	private String name;
	private double weeklyWage;
	private int workTime;

	public FulltimeEmployee(String name, double weeklyWage, int workTime) {
		this.name = name;
		this.weeklyWage = weeklyWage;
		this.workTime = workTime;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setWeeklyWage(double weeklyWage) {
		this.weeklyWage = weeklyWage;
	}

	public void setWorkTime(int workTime) {
		this.workTime = workTime;
	}

	public String getName() {
		return (this.name);
	}

	public double getWeeklyWage() {
		return (this.weeklyWage);
	}

	public int getWorkTime() {
		return (this.workTime);
	}

	public void accept(Department handler) {
		handler.visit(this); // ���÷����ߵķ��ʷ���
	}
}

// ��ְԱ���ࣺ����Ԫ����
class ParttimeEmployee implements Employee {
	private String name;
	private double hourWage;
	private int workTime;

	public ParttimeEmployee(String name, double hourWage, int workTime) {
		this.name = name;
		this.hourWage = hourWage;
		this.workTime = workTime;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setHourWage(double hourWage) {
		this.hourWage = hourWage;
	}

	public void setWorkTime(int workTime) {
		this.workTime = workTime;
	}

	public String getName() {
		return (this.name);
	}

	public double getHourWage() {
		return (this.hourWage);
	}

	public int getWorkTime() {
		return (this.workTime);
	}

	public void accept(Department handler) {
		handler.visit(this); // ���÷����ߵķ��ʷ���
	}
}

// �����ࣺ�����������
abstract class Department {
	// ����һ�����صķ��ʷ��������ڷ��ʲ�ͬ���͵ľ���Ԫ��
	public abstract void visit(FulltimeEmployee employee);

	public abstract void visit(ParttimeEmployee employee);
}

// �����ࣺ�����������
class FADepartment extends Department {
	// ʵ�ֲ��񲿶�ȫְԱ���ķ���
	public void visit(FulltimeEmployee employee) {
		int workTime = employee.getWorkTime();
		double weekWage = employee.getWeeklyWage();
		if (workTime > 40) {
			weekWage = weekWage + (workTime - 40) * 100;
		} else if (workTime < 40) {
			weekWage = weekWage - (40 - workTime) * 80;
			if (weekWage < 0) {
				weekWage = 0;
			}
		}
		System.out.println("��ʽԱ��" + employee.getName() + "ʵ�ʹ���Ϊ��" + weekWage
				+ "Ԫ��");
	}

	// ʵ�ֲ��񲿶Լ�ְԱ���ķ���
	public void visit(ParttimeEmployee employee) {
		int workTime = employee.getWorkTime();
		double hourWage = employee.getHourWage();
		System.out.println("��ʱ��" + employee.getName() + "ʵ�ʹ���Ϊ��" + workTime
				* hourWage + "Ԫ��");
	}
}

// ������Դ���ࣺ�����������
class HRDepartment extends Department {
	// ʵ��������Դ����ȫְԱ���ķ���
	public void visit(FulltimeEmployee employee) {
		int workTime = employee.getWorkTime();
		System.out.println("��ʽԱ��" + employee.getName() + "ʵ�ʹ���ʱ��Ϊ��" + workTime
				+ "Сʱ��");
		if (workTime > 40) {
			System.out.println("��ʽԱ��" + employee.getName() + "�Ӱ�ʱ��Ϊ��"
					+ (workTime - 40) + "Сʱ��");
		} else if (workTime < 40) {
			System.out.println("��ʽԱ��" + employee.getName() + "���ʱ��Ϊ��"
					+ (40 - workTime) + "Сʱ��");
		}
	}

	// ʵ��������Դ���Լ�ְԱ���ķ���
	public void visit(ParttimeEmployee employee) {
		int workTime = employee.getWorkTime();
		System.out.println("��ʱ��" + employee.getName() + "ʵ�ʹ���ʱ��Ϊ��" + workTime
				+ "Сʱ��");
	}
}

// Ա���б��ࣺ����ṹ
class EmployeeList {
	// ����һ���������ڴ洢Ա������
	private ArrayList<Employee> list = new ArrayList<Employee>();

	public void addEmployee(Employee employee) {
		list.add(employee);
	}

	// ��������Ա�������е�ÿһ��Ա������
	public void accept(Department handler) {
		for (Object obj : list) {
			((Employee) obj).accept(handler);
		}
	}
}