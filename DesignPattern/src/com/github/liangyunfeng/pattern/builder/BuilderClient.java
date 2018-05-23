package com.github.liangyunfeng.pattern.builder;

public class BuilderClient {

	public static void main(String[] args) {
		Builder builder = new ConcreteBuilder(); // 可通过配置文件实现
		Director director = new Director(builder);
		Product product = director.construct();
		System.out.println(product.toString());

		// =====================================

		ActorBuilder ab; // 针对抽象建造者编程
		ab = new AngelBuilder(); // 反射生成具体建造者对象
		ActorController ac = new ActorController();
		Actor actor;
		actor = ac.construct(ab); // 通过指挥者创建完整的建造者对象
		String type = actor.getType();
		System.out.println(type + "的外观：");
		System.out.println("性别：" + actor.getSex());
		System.out.println("面容：" + actor.getFace());
		System.out.println("服装：" + actor.getCostume());
		System.out.println("发型：" + actor.getHairstyle());
	}
}

class Product {
	private String partA; // 定义部件，部件可以是任意类型，包括值类型和引用类型
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

// Actor角色类：复杂产品，考虑到代码的可读性，只列出部分成员属性，且成员属性的类型均为String，真实情况下，有些成员属性的类型需自定义
class Actor {
	private String type; // 角色类型
	private String sex; // 性别
	private String face; // 脸型
	private String costume; // 服装
	private String hairstyle; // 发型

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

// 角色建造器：抽象建造者
abstract class ActorBuilder {
	protected Actor actor = new Actor();

	public abstract void buildType();

	public abstract void buildSex();

	public abstract void buildFace();

	public abstract void buildCostume();

	public abstract void buildHairstyle();

	// 钩子方法
	public boolean isBareheaded() {
		return false;
	}

	// 工厂方法，返回一个完整的游戏角色对象
	public Actor createActor() {
		return actor;
	}
}

// 英雄角色建造器：具体建造者
class HeroBuilder extends ActorBuilder {
	public void buildType() {
		actor.setType("英雄");
	}

	public void buildSex() {
		actor.setSex("男");
	}

	public void buildFace() {
		actor.setFace("英俊");
	}

	public void buildCostume() {
		actor.setCostume("盔甲");
	}

	public void buildHairstyle() {
		actor.setHairstyle("飘逸");
	}
}

// 天使角色建造器：具体建造者
class AngelBuilder extends ActorBuilder {
	public void buildType() {
		actor.setType("天使");
	}

	public void buildSex() {
		actor.setSex("女");
	}

	public void buildFace() {
		actor.setFace("漂亮");
	}

	public void buildCostume() {
		actor.setCostume("白裙");
	}

	public void buildHairstyle() {
		actor.setHairstyle("披肩长发");
	}
}

// 恶魔角色建造器：具体建造者
class DevilBuilder extends ActorBuilder {
	public void buildType() {
		actor.setType("恶魔");
	}

	public void buildSex() {
		actor.setSex("妖");
	}

	public void buildFace() {
		actor.setFace("丑陋");
	}

	public void buildCostume() {
		actor.setCostume("黑衣");
	}

	public void buildHairstyle() {
		actor.setHairstyle("光头");
	}

	// 覆盖钩子方法
	public boolean isBareheaded() {
		return true;
	}
}

// 游戏角色创建控制器：指挥者
class ActorController {
	// 逐步构建复杂产品对象
	public Actor construct(ActorBuilder ab) {
		Actor actor;
		ab.buildType();
		ab.buildSex();
		ab.buildFace();
		ab.buildCostume();
		// 通过钩子方法来控制产品的构建
		if (!ab.isBareheaded()) {
			ab.buildHairstyle();
		}
		actor = ab.createActor();
		return actor;
	}
}
