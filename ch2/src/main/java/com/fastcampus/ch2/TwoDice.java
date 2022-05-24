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
		// �������� ���۹޴� ������ �ؽ�Ʈ���� ���̳ʸ����� �������� ���ϱ� ������ �ؽ�Ʈ Ÿ��, ���ڵ� Ÿ���� �������� ���������� �˷������
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		
		PrintWriter out = response.getWriter();		// response ��ü���� ���������� ��� ��Ʈ���� ����
		
		int idx1 = (int)(Math.random() * 6) + 1;		// Math.random�� �⺻������ double Ÿ�� ���� ��ȯ�ϱ� ������ Ÿ�� ĳ���� �ʿ�
		int idx2 = (int)(Math.random() * 6) + 1;		// + 1�� �ִ� ���� : �ֻ��� ���ϸ��� 1���� �����Ͽ� 0�� ������ ��� �̹����� ��¾ȵǱ� ����
		
		out.println("<html>");
		out.println("<head>");
		out.println("</head>");
		out.println("<body>"); 		
		out.println("<img src='./resources/img/dice" + idx1 + ".jpg' />");	// �̹����� ��¾ȵǴ� ��� ��κ� ��� ����
		out.println("<img src='./resources/img/dice" + idx2 + ".jpg' />");
		out.println("</body>");
		out.println("</html>");
	}
}
