package com.github.liangyunfeng.pattern.prox;

public class ProxClient {

	/**
	 * (1) Զ�̴���(Remote Proxy)��Ϊһ��λ�ڲ�ͬ�ĵ�ַ�ռ�Ķ����ṩһ�����صĴ�����������ͬ�ĵ�ַ�ռ��������ͬһ̨�����У�Ҳ��������һ̨�����У�Զ�̴����ֳ�Ϊ��ʹ(Ambassador)��
     * (2) �������(Virtual Proxy)�������Ҫ����һ����Դ���Ľϴ�Ķ����ȴ���һ��������Խ�С�Ķ�������ʾ����ʵ����ֻ����Ҫʱ�Żᱻ����������
     * (3) ��������(Protect Proxy)�����ƶ�һ������ķ��ʣ����Ը���ͬ���û��ṩ��ͬ�����ʹ��Ȩ�ޡ�
     * (4) �������(Cache Proxy)��Ϊĳһ��Ŀ������Ľ���ṩ��ʱ�Ĵ洢�ռ䣬�Ա����ͻ��˿��Թ�����Щ�����
     * (5) �������ô���(Smart Reference Proxy)����һ����������ʱ���ṩһЩ����Ĳ��������罫���󱻵��õĴ�����¼�����ȡ�
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
		// ҵ�񷽷�����ʵ�ִ���
	}
}

class Proxy extends Subject {
	private RealSubject realSubject = new RealSubject(); // ά��һ������ʵ������������

	public void PreRequest() {
		// ��...
	}

	@Override
	public void Request() {
		PreRequest();
		realSubject.Request(); // ������ʵ�������ķ���
		PostRequest();
	}

	public void PostRequest() {
		// ����
	}
}