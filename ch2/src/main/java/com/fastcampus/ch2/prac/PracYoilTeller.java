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
		// 1. 입력 처리
		String year = request.getParameter("year");
		String month = request.getParameter("month");
		String day = request.getParameter("day");
		
		int yyyy = Integer.parseInt(year);
		int mm = Integer.parseInt(month);
		int dd = Integer.parseInt(day);
		
		// 2. 작업
		Calendar cal = Calendar.getInstance();
		cal.set(yyyy, mm - 1, dd);
		
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
		char yoil = " 일월화수목금토".charAt(dayOfWeek);
		
		// 3. 출력
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.println(year + "년 " + month + "월 " + day + "일은 " + yoil + "요일입니다.");
		} catch (IOException e) {
			e.printStackTrace();
			out.println("잘못 입력하셨습니다. 년, 월, 일로 입력해주십시오.");
		}
		
	}
}
