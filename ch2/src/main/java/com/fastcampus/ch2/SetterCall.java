package com.fastcampus.ch2;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.util.StringUtils;


public class SetterCall {
	
	public static void main(String[] args) throws Exception {
		// map�� ����ؼ� key, value �� ����
		Map<String, String> map = new HashMap<>();
		
		map.put("year", "2022");
		map.put("month", "5");
		map.put("day", "26");
		
		// MyDate class ���� �ҷ�����
		Class<?> type = Class.forName("com.fastcampus.ch2.MyDate");
		
		// dataBind �޼ҵ忡�� �ҷ��� Ŭ���� ������ ����Ͽ� MyDate ��ü�� ���� �迭������� ���� �� map�� ������ �ʱ�ȭ
		Object obj = dataBind(map, type);
		System.out.println("obj=" + obj);
	}
		
	private static Object dataBind(Map<String, String> map, Class<?> clazz) throws Exception {
		// MyDate ��ü ����(�迭 ������� ����)
		Object obj = clazz.getDeclaredConstructor().newInstance(new Object[0]);	// getDeclaredConstructor() : ������ ��������
		
		// MyDate ��ü�� setter �޼ҵ带 ȣ���ؼ�, map�� ������ MyDate�� �ʱ�ȭ
			
			// - ������ ã�� ���� setter �޼ҵ�� ��ü�� ����
		Field[] ivArr = clazz.getDeclaredFields();	// Field[] : Object Field�� ����, getDeclaredField() : �ʵ� ��������
		
			// MyDate�� ��� �ʵ带 ���鼭 map���� ������ ���� �ִ��� Ž��
		for (int i = 0; i < ivArr.length; i++) {
			String name = ivArr[i].getName();
			Class<?> type = ivArr[i].getType();
			
			Object value = map.get(name);	//	map�� iv�� ���� �̸��� key�� ������ value�� ����
			Method method = null;	// ������ null
			
			try {
				if (value == null) { // map�� iv�� ���� �̸��� key�� ���� ��
					continue; 
				}
				
				method = clazz.getDeclaredMethod(getSetterName(name), type);		// setter ���� ���
				System.out.println("method=" + method);
				method.invoke(obj, convertTo(value, type));		// obj�� setter�� ȣ��
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		System.out.println(Arrays.toString(ivArr));
		
		return obj;
	}

	private static Object convertTo(Object value, Class<?> type) {	// value = map Ÿ��, type = iv Ÿ��
		// value Ÿ�԰� type�� Ÿ���� ������ ���� value Ÿ�� �״�� ��ȯ
		if (value == null || type == null || type.isInstance(value)) {
			return value;
		}
		
		// value Ÿ�԰� type�� Ÿ���� �ٸ��� value Ÿ���� ��ȯ�Ͽ� ��ȯ
		if (String.class.isInstance(value) && type == int.class) {
			return Integer.valueOf("" + value);
		}
		
		return value;
	}
	
	// iv�� �̸����� setter�� �̸��� ���� ��ȯ�ϴ� �޼���("day" -> "setDay")
	private static String getSetterName(String name) {
		return "set" + StringUtils.capitalize(name);
	}

}