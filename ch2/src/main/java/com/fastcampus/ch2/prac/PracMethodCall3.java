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
		Object[] argArr = new Object[main.getParameterCount()];	// main �޼ҵ��� �Ű����� ������ ������ �� ũ���� Object �迭 ����
		
		for (int i = 0; i < paramArr.length; i++) {
			String paramName = paramArr[i].getName();
			Class paramType = paramArr[i].getType();
			Object value = map.get(paramName);	// map �� ���� ������ null
			
			if (paramType == Model.class) {		// main �޼ҵ� �Ű����� �� Model Ÿ���� �Ű������� ���� ���
				argArr[i] = model = new BindingAwareModelMap();
			} else if (value != null) {		// map �� ���� ������
				argArr[i] = convertTo(value, paramType);
			}
		}
		
		System.out.println("paramType : " + Arrays.toString(paramArr));
		System.out.println("argArr : " + Arrays.toString(argArr));
		
		// Controller�� main �޼ҵ� ȣ��
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
		if (value == null || paramType == null || paramType.isInstance(value)) {	// paramType(main �޼ҵ�) Ÿ���� value(map)�� Ÿ���� �ν��Ͻ��� ��
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
