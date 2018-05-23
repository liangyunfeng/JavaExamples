package com.github.liangyunfeng.pattern.abstractfactory;

public class FactoryMethodClient {
	// Client
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
}

/*
abstract class Button {
	public abstract void display();
}

class SpringButton extends Button {
	@Override
	public void display() {

	}
}

class SummerButton extends Button {
	@Override
	public void display() {

	}
}


abstract class TextField {
	public abstract void display();
}

class SpringTextField extends TextField {
	@Override
	public void display() {

	}
}

class SummerTextField extends TextField {
	@Override
	public void display() {

	}
}


abstract class ComboBox {
	public abstract void display();
}

class SpringComboBox extends ComboBox {
	@Override
	public void display() {

	}
}

class SummerComboBox extends ComboBox {
	@Override
	public void display() {

	}
}



interface ButtonFactory {
	public Button create();
}

class SpringButtonFactory implements ButtonFactory {
	@Override
	public SpringButton create() {
		return new SpringButton();
	}
}

class SummerButtonFactory implements ButtonFactory {
	@Override
	public SummerButton create() {
		return new SummerButton();
	}
}


interface TextFiledFactory {
	public TextField create();
}

class SpringTextFiledFactory implements TextFiledFactory {
	@Override
	public SpringTextField create() {
		return new SpringTextField();
	}
}

class SummerTextFiledFactory implements TextFiledFactory {
	@Override
	public SummerTextField create() {
		return new SummerTextField();
	}
}


interface ComboBoxFactory {
	public ComboBox create();
}

class SpringComboBoxFactory implements ComboBoxFactory {
	@Override
	public SpringComboBox create() {
		return new SpringComboBox();
	}
}

class SummerComboBoxFactory implements ComboBoxFactory {
	@Override
	public SummerComboBox create() {
		return new SummerComboBox();
	}
}
*/