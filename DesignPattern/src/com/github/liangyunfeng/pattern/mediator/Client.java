package com.github.liangyunfeng.pattern.mediator;

public class Client {

	/**
	 * ���������һ������һ�����������Label���޸�ԭ�еľ����н�����ConcreteMediator������һ����Label��������ã�
	 * Ȼ���޸�componentChanged
	 * ()���������������������ҵ������룬ԭ������������κ��޸ģ��ͻ��˴���Ҳ������������Label�����ʵ��޸ġ�
	 * 
	 * ��������������뷽��һ��ͬ����������һ��Label�࣬�����޸�ԭ�о����н�����ConcreteMediator�Ĵ��룬
	 * ��������һ��ConcreteMediator������SubConcreteMediator��ʵ�ֶ�Label��������ã�
	 * Ȼ�����������н�����SubConcreteMediator��ͨ������componentChanged
	 * ()������ʵ�������������������Label�����֮��Ľ����� ͬ����ԭ��������������κ��޸ģ��ͻ��˴����������޸ġ�
	 */
	public static void main(String args[]) {
		// �����н��߶���
		// ConcreteViewMediator mediator;
		// mediator = new ConcreteViewMediator();

		// { -- ������һ��Label����
		SubConcreteViewMediator mediator;
		mediator = new SubConcreteViewMediator();
		Label label = new Label();
		label.setMediator(mediator);
		mediator.label = label;
		// } -- ������һ��Label����

		// ����ͬ�¶���
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

// �����н���
abstract class ViewMediator {
	public abstract void componentChanged(Component c);
}

// �����н���
class ConcreteViewMediator extends ViewMediator {
	// ά�ֶԸ���ͬ�¶��������
	public Button addButton;
	public List list;
	public TextBox userNameTextBox;
	public ComboBox cb;

	// ��װͬ�¶���֮��Ľ���
	public void componentChanged(Component c) {
		// ������ť
		if (c == addButton) {
			System.out.println("--�������Ӱ�ť--");
			list.update();
			cb.update();
			userNameTextBox.update();
		}
		// ���б��ѡ��ͻ�
		else if (c == list) {
			System.out.println("--���б��ѡ��ͻ�--");
			cb.select();
			userNameTextBox.setText();
		}
		// ����Ͽ�ѡ��ͻ�
		else if (c == cb) {
			System.out.println("--����Ͽ�ѡ��ͻ�--");
			cb.select();
			userNameTextBox.setText();
		}
	}
}

// ��������ࣺ����ͬ����
abstract class Component {
	protected ViewMediator mediator;

	public void setMediator(ViewMediator mediator) {
		this.mediator = mediator;
	}

	// ת������
	public void changed() {
		mediator.componentChanged(this);
	}

	public abstract void update();
}

// ��ť�ࣺ����ͬ����
class Button extends Component {
	public void update() {
		// ��ť����������
	}
}

// �б���ࣺ����ͬ����
class List extends Component {
	public void update() {
		System.out.println("�б������һ����޼ɡ�");
	}

	public void select() {
		System.out.println("�б��ѡ���С��Ů��");
	}
}

// ��Ͽ��ࣺ����ͬ����
class ComboBox extends Component {
	public void update() {
		System.out.println("��Ͽ�����һ����޼ɡ�");
	}

	public void select() {
		System.out.println("��Ͽ�ѡ���С��Ů��");
	}
}

// �ı����ࣺ����ͬ����
class TextBox extends Component {
	public void update() {
		System.out.println("�ͻ���Ϣ���ӳɹ����ı�����ա�");
	}

	public void setText() {
		System.out.println("�ı�����ʾ��С��Ů��");
	}
}

// ==========================������һ��Label����=====================================

// �ı���ǩ�ࣺ����ͬ����
class Label extends Component {
	public void update() {
		System.out.println("�ı���ǩ���ݸı䣬�ͻ���Ϣ������1��");
	}
}

// ���������н�����
class SubConcreteViewMediator extends ConcreteViewMediator {
	// ���Ӷ�Label���������
	public Label label;

	public void componentChanged(Component c) {
		// ������ť
		if (c == addButton) {
			System.out.println("--�������Ӱ�ť--");
			list.update();
			cb.update();
			userNameTextBox.update();
			label.update(); // �ı���ǩ����
		}
		// ���б��ѡ��ͻ�
		else if (c == list) {
			System.out.println("--���б��ѡ��ͻ�--");
			cb.select();
			userNameTextBox.setText();
		}
		// ����Ͽ�ѡ��ͻ�
		else if (c == cb) {
			System.out.println("--����Ͽ�ѡ��ͻ�--");
			cb.select();
			userNameTextBox.setText();
		}
	}
}
