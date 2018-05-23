package com.github.liangyunfeng.pattern.builder;

public class BuilderClient {

	public static void main(String[] args) {
		Builder builder = new ConcreteBuilder(); // ��ͨ�������ļ�ʵ��
		Director director = new Director(builder);
		Product product = director.construct();
		System.out.println(product.toString());

		// =====================================

		ActorBuilder ab; // ��Գ������߱��
		ab = new AngelBuilder(); // �������ɾ��彨���߶���
		ActorController ac = new ActorController();
		Actor actor;
		actor = ac.construct(ab); // ͨ��ָ���ߴ��������Ľ����߶���
		String type = actor.getType();
		System.out.println(type + "����ۣ�");
		System.out.println("�Ա�" + actor.getSex());
		System.out.println("���ݣ�" + actor.getFace());
		System.out.println("��װ��" + actor.getCostume());
		System.out.println("���ͣ�" + actor.getHairstyle());
	}
}

class Product {
	private String partA; // ���岿���������������������ͣ�����ֵ���ͺ���������
	private String partB;
	private String partC;

	public String getPartA() {
		return partA;
	}

	public void setPartA(String partA) {
		this.partA = partA;
	}

	public String getPartB() {
		return partB;
	}

	public void setPartB(String partB) {
		this.partB = partB;
	}

	public String getPartC() {
		return partC;
	}

	public void setPartC(String partC) {
		this.partC = partC;
	}

	public String toString() {
		return partA + partB + partC;
	}
}

abstract class Builder {
	protected Product product = new Product();

	public abstract void buildPartA();

	public abstract void buildPartB();

	public abstract void buildPartC();

	public Product getResult() {
		return product;
	}
}

class ConcreteBuilder extends Builder {

	@Override
	public void buildPartA() {
		product.setPartA("AAAAAAA");
	}

	@Override
	public void buildPartB() {
		product.setPartB("BBBBB");
	}

	@Override
	public void buildPartC() {
		product.setPartC("CCCCCCCCCCC");
	}
}

class Director {
	private Builder builder;

	public Director(Builder builder) {
		this.builder = builder;
	}

	public void setBuilder(Builder builder) {
		this.builder = builder;
	}

	public Product construct() {
		builder.buildPartA();
		builder.buildPartB();
		builder.buildPartC();
		return builder.getResult();
	}
}

// ===========================================================================

// Actor��ɫ�ࣺ���Ӳ�Ʒ�����ǵ�����Ŀɶ��ԣ�ֻ�г����ֳ�Ա���ԣ��ҳ�Ա���Ե����;�ΪString����ʵ����£���Щ��Ա���Ե��������Զ���
class Actor {
	private String type; // ��ɫ����
	private String sex; // �Ա�
	private String face; // ����
	private String costume; // ��װ
	private String hairstyle; // ����

	public void setType(String type) {
		this.type = type;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public void setFace(String face) {
		this.face = face;
	}

	public void setCostume(String costume) {
		this.costume = costume;
	}

	public void setHairstyle(String hairstyle) {
		this.hairstyle = hairstyle;
	}

	public String getType() {
		return (this.type);
	}

	public String getSex() {
		return (this.sex);
	}

	public String getFace() {
		return (this.face);
	}

	public String getCostume() {
		return (this.costume);
	}

	public String getHairstyle() {
		return (this.hairstyle);
	}
}

// ��ɫ����������������
abstract class ActorBuilder {
	protected Actor actor = new Actor();

	public abstract void buildType();

	public abstract void buildSex();

	public abstract void buildFace();

	public abstract void buildCostume();

	public abstract void buildHairstyle();

	// ���ӷ���
	public boolean isBareheaded() {
		return false;
	}

	// ��������������һ����������Ϸ��ɫ����
	public Actor createActor() {
		return actor;
	}
}

// Ӣ�۽�ɫ�����������彨����
class HeroBuilder extends ActorBuilder {
	public void buildType() {
		actor.setType("Ӣ��");
	}

	public void buildSex() {
		actor.setSex("��");
	}

	public void buildFace() {
		actor.setFace("Ӣ��");
	}

	public void buildCostume() {
		actor.setCostume("����");
	}

	public void buildHairstyle() {
		actor.setHairstyle("Ʈ��");
	}
}

// ��ʹ��ɫ�����������彨����
class AngelBuilder extends ActorBuilder {
	public void buildType() {
		actor.setType("��ʹ");
	}

	public void buildSex() {
		actor.setSex("Ů");
	}

	public void buildFace() {
		actor.setFace("Ư��");
	}

	public void buildCostume() {
		actor.setCostume("��ȹ");
	}

	public void buildHairstyle() {
		actor.setHairstyle("���糤��");
	}
}

// ��ħ��ɫ�����������彨����
class DevilBuilder extends ActorBuilder {
	public void buildType() {
		actor.setType("��ħ");
	}

	public void buildSex() {
		actor.setSex("��");
	}

	public void buildFace() {
		actor.setFace("��ª");
	}

	public void buildCostume() {
		actor.setCostume("����");
	}

	public void buildHairstyle() {
		actor.setHairstyle("��ͷ");
	}

	// ���ǹ��ӷ���
	public boolean isBareheaded() {
		return true;
	}
}

// ��Ϸ��ɫ������������ָ����
class ActorController {
	// �𲽹������Ӳ�Ʒ����
	public Actor construct(ActorBuilder ab) {
		Actor actor;
		ab.buildType();
		ab.buildSex();
		ab.buildFace();
		ab.buildCostume();
		// ͨ�����ӷ��������Ʋ�Ʒ�Ĺ���
		if (!ab.isBareheaded()) {
			ab.buildHairstyle();
		}
		actor = ab.createActor();
		return actor;
	}
}
