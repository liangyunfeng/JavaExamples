package com.github.liangyunfeng.pattern.facade;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Client {
	
	/**
	 * ĳϵͳ��Ҫ�ṩһ���ļ�����ģ�飬���Զ��ļ��е����ݽ��м��ܲ�������֮������ݴ洢��һ�����ļ��У�
	 * ��������̰����������֣��ֱ��Ƕ�ȡԴ�ļ������ܡ��������֮����ļ������У���ȡ�ļ��ͱ����ļ�ʹ
	 * ������ʵ�֣����ܲ���ͨ����ģ����ʵ�֡�������������Զ�����Ϊ��ʵ�ִ���Ķ������ã�����Ƹ�����
	 * ��һְ��ԭ��������������ҵ������װ��������ͬ�����С���ʹ�����ģʽ�Ը��ļ�����ģ��������
	 */
	public static void main(String args[]) {
		EncryptFacade ef = new EncryptFacade();
		ef.fileEncrypt("facade/src.txt", "facade/des.txt");
	}
}

// �ļ���ȡ�ࣺ��ϵͳ��
class FileReader {
	public String read(String fileNameSrc) {
		System.out.print("��ȡ�ļ�����ȡ���ģ�");
		StringBuffer sb = new StringBuffer();
		try {
			FileInputStream inFS = new FileInputStream(fileNameSrc);
			int data;
			while ((data = inFS.read()) != -1) {
				sb = sb.append((char) data);
			}
			inFS.close();
			System.out.println(sb.toString());
		} catch (FileNotFoundException e) {
			System.out.println("�ļ������ڣ�");
		} catch (IOException e) {
			System.out.println("�ļ���������");
		}
		return sb.toString();
	}
}

// ���ݼ����ࣺ��ϵͳ��
class CipherMachine {
	public String encrypt(String plainText) {
		System.out.print("���ݼ��ܣ�������ת��Ϊ���ģ�");
		String es = "";
		for (int i = 0; i < plainText.length(); i++) {
			String c = String.valueOf(plainText.charAt(i) % 7);
			es += c;
		}
		System.out.println(es);
		return es;
	}
}

// �ļ������ࣺ��ϵͳ��
class FileWriter {
	public void write(String encryptStr, String fileNameDes) {
		System.out.println("�������ģ�д���ļ���");
		try {
			FileOutputStream outFS = new FileOutputStream(fileNameDes);
			outFS.write(encryptStr.getBytes());
			outFS.close();
		} catch (FileNotFoundException e) {
			System.out.println("�ļ������ڣ�");
		} catch (IOException e) {
			System.out.println("�ļ���������");
		}
	}
}

// ��������ࣺ�����
class EncryptFacade {
	// ά�ֶ��������������
	private FileReader reader;
	private CipherMachine cipher;
	private FileWriter writer;

	public EncryptFacade() {
		reader = new FileReader();
		cipher = new CipherMachine();
		writer = new FileWriter();
	}

	// �������������ҵ�񷽷�
	public void fileEncrypt(String fileNameSrc, String fileNameDes) {
		String plainStr = reader.read(fileNameSrc);
		String encryptStr = cipher.encrypt(plainStr);
		writer.write(encryptStr, fileNameDes);
	}
}


//=================================���������ӿ�======================================


//��������ࣺ���������
interface AbstractEncryptFacade {
	// �������������ҵ�񷽷�
	public void fileEncrypt(String fileNameSrc, String fileNameDes);
}

//��������ࣺ�����
class NewEncryptFacade implements AbstractEncryptFacade {
	// ά�ֶ��������������
	private FileReader reader;
	private NewCipherMachine cipher;
	private FileWriter writer;

	public NewEncryptFacade() {
		reader = new FileReader();
		cipher = new NewCipherMachine();
		writer = new FileWriter();
	}

	// �������������ҵ�񷽷�
	public void fileEncrypt(String fileNameSrc, String fileNameDes) {
		String plainStr = reader.read(fileNameSrc);
		String encryptStr = cipher.encrypt(plainStr);
		writer.write(encryptStr, fileNameDes);
	}
}

//���ݼ����ࣺ��ϵͳ��
class NewCipherMachine {
	public String encrypt(String plainText) {
		System.out.print("���ݼ��ܣ�������ת��Ϊ���ģ�");
		String es = "";
		// ...
		System.out.println(es);
		return es;
	}
}