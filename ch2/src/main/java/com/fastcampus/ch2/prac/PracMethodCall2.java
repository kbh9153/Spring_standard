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
		// YoilTellerMVC Ŭ���� ���� ��������
		Class classInfo = Class.forName("com.fastcampus.ch2.YoilTellerMVC");
		Object obj = classInfo.newInstance();	// YoilTellerMVC ��ü ����
		
		// main �޼ҵ� ���� ������ (getDeclaredMethod("�޼ҵ��", �Ű�����.class); -> �� �Ű������� ǥ������ ��. �����ε��� ���� �̸��� �޼ҵ尡 ���� �� �ֱ� ����
		Method main = classInfo.getDeclaredMethod("main", int.class, int.class, int.class, Model.class);
		
		// Model ����
		Model model = new BindingAwareModelMap();	// model���� YoilTellerMVC�� ���� ������� ����
		System.out.println("before : " + model);
		
		// main �޼ҵ� ȣ��
		String viewName = (String)main.invoke(obj, new Object[] {2022, 5, 25, model});	// reflection API ���. obj(Object Ÿ��) Ŭ������ main �޼ҵ� ȣ��(String���� Ÿ�� ��ȯ)
		System.out.println("viewName : " + viewName);
		
		System.out.println("after : " + model);
		
		render(model, viewName);
	}
	
	static void render(Model model, String viewName) throws FileNotFoundException {
		String result = "";
		
		Scanner sc = new Scanner(new File("src/main/webapp/WEB-INF/views/" + viewName + ".jsp"), "utf-8");
		
		// view ������ ���� �о��
		while (sc.hasNext()) {
			result += sc.nextLine() + System.lineSeparator();
		}
		
		// Model�� Map���� ��ȯ
		Map map = model.asMap();
		
		// view ������ ${key}�� map�� �ش� ������ ��ȯ
		Iterator it = map.keySet().iterator();	// Map�� key�� �о��
		
		while (it.hasNext()) {
			String key = (String)it.next();
			// view ������ key�� map�� �ش� key ������ ��ȯ
			result = result.replace("${" + key + "}", "" + map.get(key));
		}
		
		System.out.println(result);
	}

}
