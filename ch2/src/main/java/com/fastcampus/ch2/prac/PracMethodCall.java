package com.fastcampus.ch2.prac;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

class ModelController {
	public String main(HashMap map) {
		// map�� id, pwd(key)�� asdf, 1234(value) ���� ����
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
		
		render(map, viewName);	// ����ϱ� ���� render �޼ҵ�� map�� viewName�� ����
	}
	
	static void render(HashMap map, String viewName) throws IOException {
		String result = "";
		
		Scanner sc = new Scanner(new File(viewName + ".txt"));	// scanner�� ����Ͽ� viewName(txtView1)�� .txt Ȯ���ڸ� ����
		
		while (sc.hasNext()) {	// view ������ ������ ����
			result += sc.nextLine() + System.lineSeparator();	// System.lineSeparator() : ���� ó��
		}
		
		// map�� ��� key�� �ϳ��� �о template�� ${key}�� value �ٲ�  
		Iterator it = map.keySet().iterator();
		
		while (it.hasNext()) {
			String key = (String)it.next();		// map�� ���� Ÿ�� : Object. ���׸� Ÿ���� ���� �������� �ʾ� �ֻ��� Ÿ������ ����. ������ String���� ĳ����
		
		// replace()�� ����Ͽ� view ������ key�� map�� value�� ��ȯ
		result = result.replace("${" + key + "}", (String)map.get(key));
		}
		
		// rendering ��� ���
		System.out.println(result);
	}
}
