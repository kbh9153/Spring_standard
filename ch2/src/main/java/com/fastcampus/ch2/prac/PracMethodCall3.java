package com.fastcampus.ch2.prac;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

import org.springframework.ui.Model;
import org.springframework.validation.support.BindingAwareModelMap;

public class PracMethodCall3 {

	public static void main(String[] args) throws Exception {
		Map map = new HashMap();
		map.put("year", "2022");
		map.put("month", "5");
		map.put("day", "25");
		
		Model model = null;
		Class classInfo = Class.forName("com.fastcampus.ch2.YoilTellerMVC");
		Object obj = classInfo.newInstance();
		
		Method main = classInfo.getDeclaredMethod("main", int.class, int.class, int.class, Model.class);
		
		Parameter[] paramArr = main.getParameters();
		Object[] argArr = new Object[main.getParameterCount()];	// main 메소드의 매개변수 개수와 동일한 방 크기의 Object 배열 생성
		
		for (int i = 0; i < paramArr.length; i++) {
			String paramName = paramArr[i].getName();
			Class paramType = paramArr[i].getType();
			Object value = map.get(paramName);	// map 중 값이 없으면 null
			
			if (paramType == Model.class) {		// main 메소드 매개변수 중 Model 타입의 매개변수가 있을 경우
				argArr[i] = model = new BindingAwareModelMap();
			} else if (value != null) {		// map 중 값이 있으면
				argArr[i] = convertTo(value, paramType);
			}
		}
		
		System.out.println("paramType : " + Arrays.toString(paramArr));
		System.out.println("argArr : " + Arrays.toString(argArr));
		
		// Controller의 main 메소드 호출
		String viewName = (String)main.invoke(obj, argArr);
		System.out.println("viewName : " + viewName);
		
		System.out.println("model : " + model);
		
		render(model, viewName);
	}

	private static void render(Model model, String viewName) throws FileNotFoundException {
		String result = "";
		
		Scanner sc = new Scanner(new File("src/main/webapp/WEB-INF/views/" + viewName + ".jsp"), "utf-8");
		
		while(sc.hasNext()) {
			result += sc.nextLine() + System.lineSeparator();
		}
		
		Map map = model.asMap();
		
		Iterator it = map.keySet().iterator();
		
		while (it.hasNext()) {
			String key = (String)it.next();
			result = result.replace("${" + key + "}", "" + map.get(key));
		}
		
		System.out.println(result);
	}
	
	private static Object convertTo(Object value, Class paramType) {
		if (value == null || paramType == null || paramType.isInstance(value)) {	// paramType(main 메소드) 타입이 value(map)의 타입의 인스턴스일 때
			return true;
		}
		
		if (String.class.isInstance(value) && paramType == int.class) {
			return Integer.valueOf((String)value);
		} else if (String.class.isInstance(value) && paramType == double.class) {
			return Double.valueOf((String)value);
		}
		
		return value;
	}

}
