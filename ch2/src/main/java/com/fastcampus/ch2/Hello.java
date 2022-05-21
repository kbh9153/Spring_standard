package com.fastcampus.ch2;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

// 1. ���� ȣ�� ������ ���α׷����� ��� (Controller)
@Controller
public class Hello {
	int iv = 10;	// �ν��Ͻ� ����(�ʵ�)
	static int cv = 20;		// static ����(����)
	
	// 2. URL�� �޼ҵ� ���� (RequestMapping. mapping�� �ǹ�)
	@RequestMapping("/hello")	// ("/hello") : URL ���� -> /hello URL�� main �޼ҵ尡 ����
	private void main() {	// �޼ҵ���� main�� �ƴ϶� �������. main �޼ҵ�� �ν��Ͻ� �޼ҵ� (iv, cv ���� ��� ��� ����)
		System.out.println("Hello");	// ���� �ƴ϶� �ܼ�â�� Hello ���
		System.out.println(cv);		// ����
		System.out.println(iv);		// ����
	}
	
	public static void main2() {	// static �޼ҵ� (static ������ cv ������ ��� ����)
		System.out.println(cv);		// ����
//		System.out.println(iv);		// �Ұ���
	}
}

/*
 * main �޼ҵ�� static(����) �޼ҵ尡 �ƴԿ��� �ұ��ϰ� ȣ���� ����
 *  -> main �޼ҵ�� Ŭ���� ����� �ν��Ͻ� �޼ҵ��̱� ������ ��ü�� �ʼ��� �����Ǿ�� ȣ���� ����
 *  -> ��ü�� Tomcat(WAS)�� ����Ͽ� �������ֱ� ������ static�� ��� ȣ���� ������ 
 *  
 * RequestMapping���� ����� �޼ҵ�� ���������ڿ� ������� ������ ȣ���� ���� (Private�� ȣ�� ����)
 *  -> �׷��� ���� �ƴ� ���ο����� ȣ�� �Ұ� (���� ��Ű�������� �Ұ�)
 *  -> ȣ���� ������ ������ Reflection API�� ����ϱ� ����
 *  -> Reflection API : Ŭ���� ������ ��� �ٷ� �� �ִ� ������ ��� ����
 *  -> java. lang.reflect ��Ű���� ����
 */
	