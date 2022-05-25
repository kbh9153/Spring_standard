package com.fastcampus.ch2.prac;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PracYoilTeller {

	@RequestMapping("/pracGetYoil")
	public void main(HttpServletRequest request, HttpServletResponse response) {
		// 1. �Է� ó��
		String year = request.getParameter("year");
		String month = request.getParameter("month");
		String day = request.getParameter("day");
		
		int yyyy = Integer.parseInt(year);
		int mm = Integer.parseInt(month);
		int dd = Integer.parseInt(day);
		
		// 2. �۾�
		Calendar cal = Calendar.getInstance();
		cal.set(yyyy, mm - 1, dd);
		
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
		char yoil = " �Ͽ�ȭ�������".charAt(dayOfWeek);
		
		// 3. ���
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.println(year + "�� " + month + "�� " + day + "���� " + yoil + "�����Դϴ�.");
		} catch (IOException e) {
			e.printStackTrace();
			out.println("�߸� �Է��ϼ̽��ϴ�. ��, ��, �Ϸ� �Է����ֽʽÿ�.");
		}
		
	}
}
