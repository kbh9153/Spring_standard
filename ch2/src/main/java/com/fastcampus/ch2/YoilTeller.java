package com.fastcampus.ch2;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

// YoilTeller : 년월일을 입력하면 무슨 요일인지 알려주는 프로그램

@Controller
public class YoilTeller {
	
	@RequestMapping("/getYoil")
	public void main(HttpServletRequest request, HttpServletResponse response) throws IOException {
	//public static void main(String[] args) {
		
		// 1. 입력
		String year = request.getParameter("year");
		String month = request.getParameter("month");
		String day = request.getParameter("day");
		
		// 2. 작업
		// URL은 String. request를 통해 전달받은 값을 정수로 변환
		int yyyy = Integer.parseInt(year);
		int mm = Integer.parseInt(month);
		int dd = Integer.parseInt(day);
		
		Calendar cal = Calendar.getInstance();
		cal.set(yyyy, mm - 1, dd);
		
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);		// Calendar.DAY_OF_WEEK : 1. 일요일, 2. 월요일, 3. 화요일 ... 순으로 요일번호값을 반환
		char yoil = " 일월화수목금토".charAt(dayOfWeek);	// dayOfWeek이 1이면 일, 2이면 월, 3이면 화. 0은 공백
		
		// 3. 출력
		// 브라우저는 전송받는 내용이 텍스트인지 바이너리인지 구분하지 못하기 때문에 데이터 타입, 인코딩 타입이 무엇인지 브라우저에게 알려줘야함
		response.setContentType("text/html");	// 출력하기 전 텍스트타입을 지정해야함. 
		response.setCharacterEncoding("utf-8");		// 출력하기 전 인코딩타입을 지정해야함
		
		PrintWriter out = response.getWriter();	// response 객체에서 브라우저로의 출력 스트림을 얻음
		
		out.println(year + "년 " + month + "월 " + day + "일은 ");
		out.println(yoil + "요일입니다.");
	}
}
