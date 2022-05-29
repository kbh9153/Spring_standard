package com.fastcampus.ch2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
public class BoardController {
	@GetMapping("/list")
	public String list(HttpServletRequest request) {
		if (!loginCheck(request)) {
			return "redirect:/login/login?toURL=" + request.getRequestURL();	// session�� id�� �ִµ� �α����� �������� loginForm���� �̵�
		}
		return "boardList";	// session�� id�� �ְ� �α����� ������ boardList�� �̵�
	}
	
	private boolean loginCheck(HttpServletRequest request) {
		// 1. ������ ��
		HttpSession session = request.getSession();
		// 2. ���ǿ� id�� �ִ��� Ȯ��, ������ true
//		if (session.getAttribute("id") != null) {
//			return true;
//		} else {
//			return false;	// �α��� ���� X
//		}
		return session.getAttribute("id") != null;	// session�� id�� ������(!= null) true, id�� ������ false ��ȯ ���� if���� ������ ���
	}
}
