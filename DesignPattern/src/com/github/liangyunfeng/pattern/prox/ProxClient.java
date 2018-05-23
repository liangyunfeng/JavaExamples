package com.github.liangyunfeng.pattern.prox;

public class ProxClient {

	/**
	 * (1) 远程代理(Remote Proxy)：为一个位于不同的地址空间的对象提供一个本地的代理对象，这个不同的地址空间可以是在同一台主机中，也可是在另一台主机中，远程代理又称为大使(Ambassador)。
     * (2) 虚拟代理(Virtual Proxy)：如果需要创建一个资源消耗较大的对象，先创建一个消耗相对较小的对象来表示，真实对象只在需要时才会被真正创建。
     * (3) 保护代理(Protect Proxy)：控制对一个对象的访问，可以给不同的用户提供不同级别的使用权限。
     * (4) 缓冲代理(Cache Proxy)：为某一个目标操作的结果提供临时的存储空间，以便多个客户端可以共享这些结果。
     * (5) 智能引用代理(Smart Reference Proxy)：当一个对象被引用时，提供一些额外的操作，例如将对象被调用的次数记录下来等。
	 */
	public static void main(String[] args) {
		Subject real = new Proxy();
		real.Request();
	}

}

abstract class Subject {
	public abstract void Request();
}

class RealSubject extends Subject {
	@Override
	public void Request() {
		// 业务方法具体实现代码
	}
}

class Proxy extends Subject {
	private RealSubject realSubject = new RealSubject(); // 维持一个对真实主题对象的引用

	public void PreRequest() {
		// …...
	}

	@Override
	public void Request() {
		PreRequest();
		realSubject.Request(); // 调用真实主题对象的方法
		PostRequest();
	}

	public void PostRequest() {
		// ……
	}
}