package com.github.liangyunfeng.pattern.composite;

import java.util.*;

public class Client {
	public static void main(String args[]) {
        //��Գ��󹹼����
		AbstractFile file1,file2,file3,file4,file5,folder1,folder2,folder3,folder4;
		
		folder1 = new Folder("Sunny������");
		folder2 = new Folder("ͼ���ļ�");
		folder3 = new Folder("�ı��ļ�");
		folder4 = new Folder("��Ƶ�ļ�");
		
		file1 = new ImageFile("С��Ů.jpg");
		file2 = new ImageFile("���޼�.gif");
		file3 = new TextFile("�����澭.txt");
		file4 = new TextFile("��������.doc");
		file5 = new VideoFile("Ц������.rmvb");

		folder2.add(file1);
		folder2.add(file2);
		folder3.add(file3);
		folder3.add(file4);
		folder4.add(file5);
		folder1.add(folder2);
		folder1.add(folder3);
		folder1.add(folder4);
		
        //�ӡ�Sunny�����ϡ��ڵ㿪ʼ����ɱ������
		folder1.killVirus();
	}
}

//�����ļ��ࣺ���󹹼�
abstract class AbstractFile {
	public abstract void add(AbstractFile file);
	public abstract void remove(AbstractFile file);
	public abstract AbstractFile getChild(int i);
	public abstract void killVirus();
}

//ͼ���ļ��ࣺҶ�ӹ���
class ImageFile extends AbstractFile {
	private String name;
	
	public ImageFile(String name) {
		this.name = name;
	}
	
	public void add(AbstractFile file) {
	   System.out.println("�Բ��𣬲�֧�ָ÷�����");
	}
	
	public void remove(AbstractFile file) {
		System.out.println("�Բ��𣬲�֧�ָ÷�����");
	}
	
	public AbstractFile getChild(int i) {
		System.out.println("�Բ��𣬲�֧�ָ÷�����");
		return null;
	}
	
	public void killVirus() {
		//ģ��ɱ��
		System.out.println("----��ͼ���ļ�'" + name + "'����ɱ��");
	}
}

//�ı��ļ��ࣺҶ�ӹ���
class TextFile extends AbstractFile {
	private String name;
	
	public TextFile(String name) {
		this.name = name;
	}
	
	public void add(AbstractFile file) {
	   System.out.println("�Բ��𣬲�֧�ָ÷�����");
	}
	
	public void remove(AbstractFile file) {
		System.out.println("�Բ��𣬲�֧�ָ÷�����");
	}
	
	public AbstractFile getChild(int i) {
		System.out.println("�Բ��𣬲�֧�ָ÷�����");
		return null;
	}
	
	public void killVirus() {
		//ģ��ɱ��
		System.out.println("----���ı��ļ�'" + name + "'����ɱ��");
	}
}

//��Ƶ�ļ��ࣺҶ�ӹ���
class VideoFile extends AbstractFile {
	private String name;
	
	public VideoFile(String name) {
		this.name = name;
	}
	
	public void add(AbstractFile file) {
	   System.out.println("�Բ��𣬲�֧�ָ÷�����");
	}
	
	public void remove(AbstractFile file) {
		System.out.println("�Բ��𣬲�֧�ָ÷�����");
	}
	
	public AbstractFile getChild(int i) {
		System.out.println("�Բ��𣬲�֧�ָ÷�����");
		return null;
	}
	
	public void killVirus() {
		//ģ��ɱ��
		System.out.println("----����Ƶ�ļ�'" + name + "'����ɱ��");
	}
}

//�ļ����ࣺ��������
class Folder extends AbstractFile {
	//���弯��fileList�����ڴ洢AbstractFile���͵ĳ�Ա
	private ArrayList<AbstractFile> fileList=new ArrayList<AbstractFile>();
	private String name;
		
	public Folder(String name) {
		this.name = name;
	}
	
	public void add(AbstractFile file) {
	   fileList.add(file);	
	}
	
	public void remove(AbstractFile file) {
		fileList.remove(file);
	}
	
	public AbstractFile getChild(int i) {
		return (AbstractFile)fileList.get(i);
	}
	
	public void killVirus() {
		System.out.println("****���ļ���'" + name + "'����ɱ��");  //ģ��ɱ��
		
		//�ݹ���ó�Ա������killVirus()����
		for(Object obj : fileList) {
			((AbstractFile)obj).killVirus();
		}
	}
}