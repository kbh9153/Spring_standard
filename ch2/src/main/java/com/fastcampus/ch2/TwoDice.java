package com.fastcampus.ch2;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TwoDice {
	
	@RequestMapping("/rollDice")
	public void main(HttpServletResponse response) throws IOException {
		// 브라우저는 전송받는 내용이 텍스트인지 바이너리인지 구분하지 못하기 때문에 텍스트 타입, 인코딩 타입이 무엇인지 브라우저에게 알려줘야함
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		
		PrintWriter out = response.getWriter();		// response 객체에서 브라우저로의 출력 스트림을 얻음
		
		int idx1 = (int)(Math.random() * 6) + 1;		// Math.random은 기본적으로 double 타입 값을 반환하기 때문에 타입 캐스팅 필요
		int idx2 = (int)(Math.random() * 6) + 1;		// + 1이 있는 이유 : 주사위 파일명이 1부터 시작하여 0이 나오는 경우 이미지가 출력안되기 때문
		
		out.println("<html>");
		out.println("<head>");
		out.println("</head>");
		out.println("<body>"); 		
		out.println("<img src='./resources/img/dice" + idx1 + ".jpg' />");	// 이미지가 출력안되는 경우 대부분 경로 문제
		out.println("<img src='./resources/img/dice" + idx2 + ".jpg' />");
		out.println("</body>");
		out.println("</html>");
	}
}
