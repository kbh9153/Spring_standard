package com.fastcampus.ch2.prac;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

class ModelController {
	public String main(HashMap map) {
		// map에 id, pwd(key)와 asdf, 1234(value) 값을 넣음
		map.put("id", "asdf");
		map.put("pwd", "1234");
		
		return "txtView1";
	}
}

public class PracMethodCall {
	
	public static void main(String[] args) throws Exception {
		HashMap map = new HashMap();
		System.out.println("before : " + map);
		
		ModelController mc = new ModelController();
		String viewName = mc.main(map);		// viewName : txtView1
		
		System.out.println("after : " + map);
		
		render(map, viewName);	// 출력하기 위해 render 메소드로 map과 viewName을 전달
	}
	
	static void render(HashMap map, String viewName) throws IOException {
		String result = "";
		
		Scanner sc = new Scanner(new File(viewName + ".txt"));	// scanner를 사용하여 viewName(txtView1)에 .txt 확장자를 붙임
		
		while (sc.hasNext()) {	// view 파일의 내용을 읽음
			result += sc.nextLine() + System.lineSeparator();	// System.lineSeparator() : 개행 처리
		}
		
		// map에 담긴 key를 하나씩 읽어서 template의 ${key}를 value 바꿈  
		Iterator it = map.keySet().iterator();
		
		while (it.hasNext()) {
			String key = (String)it.next();		// map의 기존 타입 : Object. 제네릭 타입을 따로 지정하지 않아 최상위 타입으로 설정. 때문에 String으로 캐스팅
		
		// replace()를 사용하여 view 파일의 key를 map의 value로 변환
		result = result.replace("${" + key + "}", (String)map.get(key));
		}
		
		// rendering 결과 출력
		System.out.println(result);
	}
}
