package com.fastcampus.ch2.prac;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

import org.springframework.ui.Model;
import org.springframework.validation.support.BindingAwareModelMap;

public class PracMethodCall2 {
	
	public static void main(String[] args) throws Exception {
		// YoilTellerMVC 클래스 정보 가져오기
		Class classInfo = Class.forName("com.fastcampus.ch2.YoilTellerMVC");
		Object obj = classInfo.newInstance();	// YoilTellerMVC 객체 생성
		
		// main 메소드 정보 가져옴 (getDeclaredMethod("메소드명", 매개변수.class); -> 꼭 매개변수를 표시해줄 것. 오버로딩된 같은 이름의 메소드가 있을 수 있기 때문
		Method main = classInfo.getDeclaredMethod("main", int.class, int.class, int.class, Model.class);
		
		// Model 생성
		Model model = new BindingAwareModelMap();	// model에는 YoilTellerMVC의 계산된 결과값이 있음
		System.out.println("before : " + model);
		
		// main 메소드 호출
		String viewName = (String)main.invoke(obj, new Object[] {2022, 5, 25, model});	// reflection API 사용. obj(Object 타입) 클래스의 main 메소드 호출(String으로 타입 변환)
		System.out.println("viewName : " + viewName);
		
		System.out.println("after : " + model);
		
		render(model, viewName);
	}
	
	static void render(Model model, String viewName) throws FileNotFoundException {
		String result = "";
		
		Scanner sc = new Scanner(new File("src/main/webapp/WEB-INF/views/" + viewName + ".jsp"), "utf-8");
		
		// view 파일의 내용 읽어옴
		while (sc.hasNext()) {
			result += sc.nextLine() + System.lineSeparator();
		}
		
		// Model을 Map으로 변환
		Map map = model.asMap();
		
		// view 파일의 ${key}를 map의 해당 값으로 변환
		Iterator it = map.keySet().iterator();	// Map의 key를 읽어옴
		
		while (it.hasNext()) {
			String key = (String)it.next();
			// view 파일의 key를 map의 해당 key 값으로 변환
			result = result.replace("${" + key + "}", "" + map.get(key));
		}
		
		System.out.println(result);
	}

}
