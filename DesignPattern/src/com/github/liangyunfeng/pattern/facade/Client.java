package com.github.liangyunfeng.pattern.facade;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Client {
	
	/**
	 * 某系统需要提供一个文件加密模块，可以对文件中的数据进行加密并将加密之后的数据存储在一个新文件中，
	 * 具体的流程包括三个部分，分别是读取源文件、加密、保存加密之后的文件，其中，读取文件和保存文件使
	 * 用流来实现，加密操作通过求模运算实现。这三个操作相对独立，为了实现代码的独立重用，让设计更符合
	 * 单一职责原则，这三个操作的业务代码封装在三个不同的类中。现使用外观模式对该文件加密模块进行设计
	 */
	public static void main(String args[]) {
		EncryptFacade ef = new EncryptFacade();
		ef.fileEncrypt("facade/src.txt", "facade/des.txt");
	}
}

// 文件读取类：子系统类
class FileReader {
	public String read(String fileNameSrc) {
		System.out.print("读取文件，获取明文：");
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
			System.out.println("文件不存在！");
		} catch (IOException e) {
			System.out.println("文件操作错误！");
		}
		return sb.toString();
	}
}

// 数据加密类：子系统类
class CipherMachine {
	public String encrypt(String plainText) {
		System.out.print("数据加密，将明文转换为密文：");
		String es = "";
		for (int i = 0; i < plainText.length(); i++) {
			String c = String.valueOf(plainText.charAt(i) % 7);
			es += c;
		}
		System.out.println(es);
		return es;
	}
}

// 文件保存类：子系统类
class FileWriter {
	public void write(String encryptStr, String fileNameDes) {
		System.out.println("保存密文，写入文件。");
		try {
			FileOutputStream outFS = new FileOutputStream(fileNameDes);
			outFS.write(encryptStr.getBytes());
			outFS.close();
		} catch (FileNotFoundException e) {
			System.out.println("文件不存在！");
		} catch (IOException e) {
			System.out.println("文件操作错误！");
		}
	}
}

// 加密外观类：外观类
class EncryptFacade {
	// 维持对其他对象的引用
	private FileReader reader;
	private CipherMachine cipher;
	private FileWriter writer;

	public EncryptFacade() {
		reader = new FileReader();
		cipher = new CipherMachine();
		writer = new FileWriter();
	}

	// 调用其他对象的业务方法
	public void fileEncrypt(String fileNameSrc, String fileNameDes) {
		String plainStr = reader.read(fileNameSrc);
		String encryptStr = cipher.encrypt(plainStr);
		writer.write(encryptStr, fileNameDes);
	}
}


//=================================抽象外观类接口======================================


//加密外观类：抽象外观类
interface AbstractEncryptFacade {
	// 调用其他对象的业务方法
	public void fileEncrypt(String fileNameSrc, String fileNameDes);
}

//加密外观类：外观类
class NewEncryptFacade implements AbstractEncryptFacade {
	// 维持对其他对象的引用
	private FileReader reader;
	private NewCipherMachine cipher;
	private FileWriter writer;

	public NewEncryptFacade() {
		reader = new FileReader();
		cipher = new NewCipherMachine();
		writer = new FileWriter();
	}

	// 调用其他对象的业务方法
	public void fileEncrypt(String fileNameSrc, String fileNameDes) {
		String plainStr = reader.read(fileNameSrc);
		String encryptStr = cipher.encrypt(plainStr);
		writer.write(encryptStr, fileNameDes);
	}
}

//数据加密类：子系统类
class NewCipherMachine {
	public String encrypt(String plainText) {
		System.out.print("数据加密，将明文转换为密文：");
		String es = "";
		// ...
		System.out.println(es);
		return es;
	}
}