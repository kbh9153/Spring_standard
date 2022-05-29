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
			return "redirect:/login/login?toURL=" + request.getRequestURL();	// session에 id가 있는데 로그인을 안했으면 loginForm으로 이동
		}
		return "boardList";	// session에 id가 있고 로그인을 했으면 boardList로 이동
	}
	
	private boolean loginCheck(HttpServletRequest request) {
		// 1. 세션을 얻어서
		HttpSession session = request.getSession();
		// 2. 세션에 id가 있는지 확인, 있으면 true
//		if (session.getAttribute("id") != null) {
//			return true;
//		} else {
//			return false;	// 로그인 상태 X
//		}
		return session.getAttribute("id") != null;	// session에 id가 있으면(!= null) true, id가 없으면 false 반환 위의 if문과 동일한 기능
	}
}
