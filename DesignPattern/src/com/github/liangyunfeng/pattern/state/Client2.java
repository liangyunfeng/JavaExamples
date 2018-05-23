package com.github.liangyunfeng.pattern.state;

// 使用环境类实现状态转换
public class Client2 {
	public static void main(String args[]) {
		Screen screen = new Screen();
		screen.onClick();
		screen.onClick();
		screen.onClick();
	}
}

// 屏幕类
class Screen {
	// 枚举所有的状态，currentState表示当前状态
	private ScreenState currentState, normalState, largerState, largestState;

	public Screen() {
		this.normalState = new NormalScreenState(); // 创建正常状态对象
		this.largerState = new LargerScreenState(); // 创建二倍放大状态对象
		this.largestState = new LargestScreenState(); // 创建四倍放大状态对象
		this.currentState = normalState; // 设置初始状态
		this.currentState.display();
	}

	public void setState(ScreenState state) {
		this.currentState = state;
	}

	// 单击事件处理方法，封转了对状态类中业务方法的调用和状态的转换
	public void onClick() {
		if (this.currentState == normalState) {
			this.setState(largerState);
			this.currentState.display();
		} else if (this.currentState == largerState) {
			this.setState(largestState);
			this.currentState.display();
		} else if (this.currentState == largestState) {
			this.setState(normalState);
			this.currentState.display();
		}
	}
}

// 抽象状态类
abstract class ScreenState {
	public abstract void display();
}

// 正常状态类
class NormalScreenState extends ScreenState {
	public void display() {
		System.out.println("正常大小！");
	}
}

// 二倍状态类
class LargerScreenState extends ScreenState {
	public void display() {
		System.out.println("二倍大小！");
	}
}

// 四倍状态类
class LargestScreenState extends ScreenState {
	public void display() {
		System.out.println("四倍大小！");
	}
}