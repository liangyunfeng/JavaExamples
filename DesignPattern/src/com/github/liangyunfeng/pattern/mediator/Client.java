package com.github.liangyunfeng.pattern.mediator;

public class Client {

	/**
	 * 【解决方案一】增加一个界面组件类Label，修改原有的具体中介者类ConcreteMediator，增加一个对Label对象的引用，
	 * 然后修改componentChanged
	 * ()方法中其他相关组件对象的业务处理代码，原有组件类无须任何修改，客户端代码也需针对新增组件Label进行适当修改。
	 * 
	 * 【解决方案二】与方案一相同，首先增加一个Label类，但不修改原有具体中介者类ConcreteMediator的代码，
	 * 而是增加一个ConcreteMediator的子类SubConcreteMediator来实现对Label对象的引用，
	 * 然后在新增的中介者类SubConcreteMediator中通过覆盖componentChanged
	 * ()方法来实现所有组件（包括新增Label组件）之间的交互， 同样，原有组件类无须做任何修改，客户端代码需少许修改。
	 */
	public static void main(String args[]) {
		// 定义中介者对象
		// ConcreteViewMediator mediator;
		// mediator = new ConcreteViewMediator();

		// { -- 新增加一个Label功能
		SubConcreteViewMediator mediator;
		mediator = new SubConcreteViewMediator();
		Label label = new Label();
		label.setMediator(mediator);
		mediator.label = label;
		// } -- 新增加一个Label功能

		// 定义同事对象
		Button addBT = new Button();
		List list = new List();
		ComboBox cb = new ComboBox();
		TextBox userNameTB = new TextBox();

		addBT.setMediator(mediator);
		list.setMediator(mediator);
		cb.setMediator(mediator);
		userNameTB.setMediator(mediator);

		mediator.addButton = addBT;
		mediator.list = list;
		mediator.cb = cb;
		mediator.userNameTextBox = userNameTB;

		addBT.changed();
		System.out.println("-----------------------------");
		list.changed();
	}
}

// 抽象中介者
abstract class ViewMediator {
	public abstract void componentChanged(Component c);
}

// 具体中介者
class ConcreteViewMediator extends ViewMediator {
	// 维持对各个同事对象的引用
	public Button addButton;
	public List list;
	public TextBox userNameTextBox;
	public ComboBox cb;

	// 封装同事对象之间的交互
	public void componentChanged(Component c) {
		// 单击按钮
		if (c == addButton) {
			System.out.println("--单击增加按钮--");
			list.update();
			cb.update();
			userNameTextBox.update();
		}
		// 从列表框选择客户
		else if (c == list) {
			System.out.println("--从列表框选择客户--");
			cb.select();
			userNameTextBox.setText();
		}
		// 从组合框选择客户
		else if (c == cb) {
			System.out.println("--从组合框选择客户--");
			cb.select();
			userNameTextBox.setText();
		}
	}
}

// 抽象组件类：抽象同事类
abstract class Component {
	protected ViewMediator mediator;

	public void setMediator(ViewMediator mediator) {
		this.mediator = mediator;
	}

	// 转发调用
	public void changed() {
		mediator.componentChanged(this);
	}

	public abstract void update();
}

// 按钮类：具体同事类
class Button extends Component {
	public void update() {
		// 按钮不产生交互
	}
}

// 列表框类：具体同事类
class List extends Component {
	public void update() {
		System.out.println("列表框增加一项：张无忌。");
	}

	public void select() {
		System.out.println("列表框选中项：小龙女。");
	}
}

// 组合框类：具体同事类
class ComboBox extends Component {
	public void update() {
		System.out.println("组合框增加一项：张无忌。");
	}

	public void select() {
		System.out.println("组合框选中项：小龙女。");
	}
}

// 文本框类：具体同事类
class TextBox extends Component {
	public void update() {
		System.out.println("客户信息增加成功后文本框清空。");
	}

	public void setText() {
		System.out.println("文本框显示：小龙女。");
	}
}

// ==========================新增加一个Label功能=====================================

// 文本标签类：具体同事类
class Label extends Component {
	public void update() {
		System.out.println("文本标签内容改变，客户信息总数加1。");
	}
}

// 新增具体中介者类
class SubConcreteViewMediator extends ConcreteViewMediator {
	// 增加对Label对象的引用
	public Label label;

	public void componentChanged(Component c) {
		// 单击按钮
		if (c == addButton) {
			System.out.println("--单击增加按钮--");
			list.update();
			cb.update();
			userNameTextBox.update();
			label.update(); // 文本标签更新
		}
		// 从列表框选择客户
		else if (c == list) {
			System.out.println("--从列表框选择客户--");
			cb.select();
			userNameTextBox.setText();
		}
		// 从组合框选择客户
		else if (c == cb) {
			System.out.println("--从组合框选择客户--");
			cb.select();
			userNameTextBox.setText();
		}
	}
}
