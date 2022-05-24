package com.fastcampus.ch2;

import java.lang.reflect.Method;

public class PrivateMethodCall {

	public static void main(String[] args) throws Exception {
//		Hello hello = new Hello();
//		hello.main();	// Hello 클래스의 main 메소드의 접근제어자가 private이기 때문에 호출 불가
		
		// Hello 클래스의 Class 객체(클래스의 정보를 담고 있는 객체)를 얻어옴
		Class helloClass = Class.forName("com.fastcampus.ch2.hello");
		Hello hello = (Hello)helloClass.newInstance();		// Class 객체가 가진 정보로 객체 생성
		Method main = helloClass.getDeclaredMethod("Main");		// main 이름의 메소드 정보를 가져옴
		main.setAccessible(true);	// private인 main() 메소드를 호출 가능하도록 접근을 true로 설정
		
		main.invoke(hello);		// hello.main()과 동일
	}
}
