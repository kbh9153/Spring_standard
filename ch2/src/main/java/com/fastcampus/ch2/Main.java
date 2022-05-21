package com.fastcampus.ch2;

import java.lang.reflect.Method;

public class Main {

	public static void main(String[] args) throws Exception {
//		Hello hello = new Hello();
//		hello.main();	// Hello Ŭ������ main �޼ҵ��� ���������ڰ� private�̱� ������ ȣ�� �Ұ�
		
		// Hello Ŭ������ Class ��ü(Ŭ������ ������ ��� �ִ� ��ü)�� ����
		Class helloClass = Class.forName("com.fastcampus.ch2.hello");
		Hello hello = (Hello)helloClass.newInstance();		// Class ��ü�� ���� ������ ��ü ����
		Method main = helloClass.getDeclaredMethod("Main");		// main �̸��� �޼ҵ� ������ ������
		main.setAccessible(true);	// private�� main() �޼ҵ带 ȣ�� �����ϵ��� ������ true�� ����
		
		main.invoke(hello);		// hello.main()�� ����
	}
}
