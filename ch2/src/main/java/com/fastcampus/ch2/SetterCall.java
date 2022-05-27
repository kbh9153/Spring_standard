package com.fastcampus.ch2;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.util.StringUtils;


public class SetterCall {
	
	public static void main(String[] args) throws Exception {
		// map을 사용해서 key, value 값 저장
		Map<String, String> map = new HashMap<>();
		
		map.put("year", "2022");
		map.put("month", "5");
		map.put("day", "26");
		
		// MyDate class 정보 불러오기
		Class<?> type = Class.forName("com.fastcampus.ch2.MyDate");
		
		// dataBind 메소드에서 불러온 클래스 정보를 사용하여 MyDate 객체를 생성 배열방식으로 생성 후 map의 값으로 초기화
		Object obj = dataBind(map, type);
		System.out.println("obj=" + obj);
	}
		
	private static Object dataBind(Map<String, String> map, Class<?> clazz) throws Exception {
		// MyDate 객체 생성(배열 방식으로 생성)
		Object obj = clazz.getDeclaredConstructor().newInstance(new Object[0]);	// getDeclaredConstructor() : 생성자 가져오기
		
		// MyDate 객체의 setter 메소드를 호출해서, map의 값으로 MyDate를 초기화
			
			// - 있을시 찾은 값을 setter 메소드로 객체에 저장
		Field[] ivArr = clazz.getDeclaredFields();	// Field[] : Object Field에 접근, getDeclaredField() : 필드 가져오기
		
			// MyDate의 모든 필드를 돌면서 map에도 동일한 값이 있는지 탐색
		for (int i = 0; i < ivArr.length; i++) {
			String name = ivArr[i].getName();
			Class<?> type = ivArr[i].getType();
			
			Object value = map.get(name);	//	map에 iv와 같은 이름의 key가 있으면 value에 저장
			Method method = null;	// 없으면 null
			
			try {
				if (value == null) { // map에 iv와 같은 이름의 key가 없을 때
					continue; 
				}
				
				method = clazz.getDeclaredMethod(getSetterName(name), type);		// setter 정보 얻기
				System.out.println("method=" + method);
				method.invoke(obj, convertTo(value, type));		// obj의 setter를 호출
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		System.out.println(Arrays.toString(ivArr));
		
		return obj;
	}

	private static Object convertTo(Object value, Class<?> type) {	// value = map 타입, type = iv 타입
		// value 타입과 type의 타입이 같으면 기존 value 타입 그대로 반환
		if (value == null || type == null || type.isInstance(value)) {
			return value;
		}
		
		// value 타입과 type의 타입이 다르면 value 타입을 변환하여 반환
		if (String.class.isInstance(value) && type == int.class) {
			return Integer.valueOf("" + value);
		}
		
		return value;
	}
	
	// iv의 이름으로 setter의 이름을 만들어서 반환하는 메서드("day" -> "setDay")
	private static String getSetterName(String name) {
		return "set" + StringUtils.capitalize(name);
	}

}