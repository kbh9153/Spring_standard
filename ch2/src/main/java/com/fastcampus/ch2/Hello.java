package com.fastcampus.ch2;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

// 1. 원격 호출 가능한 프로그램으로 등록 (Controller)
@Controller
public class Hello {
	int iv = 10;	// 인스턴스 변수(필드)
	static int cv = 20;		// static 변수(정적)

	// 2. URL과 메소드 연결 (RequestMapping. mapping을 의미)
	@RequestMapping("/hello")	// ("/hello") : URL 설정 -> /hello URL과 main 메소드가 연결
	private void main() {	// 메소드명은 main이 아니라도 상관없음. main 메소드는 인스턴스 메소드 (iv, cv 변수 모두 사용 가능)
		System.out.println("Hello");	// 웹이 아니라 콘솔창에 Hello 출력
		System.out.println(cv);		// 가능
		System.out.println(iv);		// 가능
	}

	public static void main2() {	// static 메소드 (static 변수인 cv 변수만 사용 가능)
		System.out.println(cv);		// 가능
//		System.out.println(iv);		// 불가능
	}
}

/*
 * main 메소드는 static(정적) 메소드가 아님에도 불구하고 호출이 가능
 *  -> main 메소드는 클래스 멤버인 인스턴스 메소드이기 때문에 객체가 필수로 생성되어야 호출이 가능
 *  -> 객체를 Tomcat(WAS)이 대신하여 생성해주기 때문에 static이 없어도 호출이 가능함 
 *  
 * RequestMapping으로 연결된 메소드는 접근제어자와 상관없이 웹에서 호출이 가능 (Private도 호출 가능)
 *  -> 그러나 웹이 아닌 내부에서는 호출 불가 (같은 패키지에서도 불가)
 *  -> 호출이 가능한 이유는 Reflection API를 사용하기 때문
 *  -> Reflection API : 클래스 정보를 얻고 다룰 수 있는 강력한 기능 제공
 *  -> java. lang.reflect 패키지를 제공
 */