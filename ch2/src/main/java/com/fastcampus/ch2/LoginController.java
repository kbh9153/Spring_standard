package com.fastcampus.ch2;

import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {
//	@RequestMapping(value="/login", method={RequestMethod.POST, RequestMethod.GET})
//	public String loginForm() {
//		return "loginForm";
//	}
	
	@GetMapping("/login")
	public String loginForm() {
		return "loginForm";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {	// 밑과 같이 직접 세션 객체를 httpServletRequest를 통해 얻어와도 되지만 HttpSession을 매개변수로 지정해도 됨	
//		HttpSession session = request.getSession();
		// 2. 세션 종료
		session.invalidate();
		// 2. 홈으로 이동
		return "redirect:/";
	}
	
	@PostMapping("/login")
	public String login(@CookieValue("id") String cookieID, String id, String pwd, String toURL, boolean rememberId, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// @CookieValue() : ()의 이름을 가진 쿠키의 값을 가져옴
		
		// 1. id와 pwd 확인
		if (!loginCheck(id, pwd)) {
			// 2-1. 일치하지 않으면 loginForm으로 이동
			String msg = URLEncoder.encode("id 또는 pwd가 일치하지 않습니다.", "utf-8");
			
			return "redirect:/login/login?msg=" + msg;
		}
		// 2-2. id와 pwd 일치할 경우 아래의 순서대로 작업 진행
		// session 객체 얻어오기
		HttpSession session = request.getSession();		
		// session 객체에 id 저장
		session.setAttribute("id", id);		
		
		if (rememberId) {
			// 1. 쿠키 생성
			Cookie cookie = new Cookie("id", id);
			// 2. 응답에 저장
			response.addCookie(cookie);
		} else {
			// 쿠키 삭제
			Cookie cookie = new Cookie("id", id);
			cookie.setMaxAge(0);	// 쿠키의 유효기간을 0초로 덮어씌어 Browser에서 바로 삭제처리 되도록 설정
			// 응답에 저장
			response.addCookie(cookie);
		}
			// 3. Home(= index)으로 이동
		toURL = toURL == null || toURL.equals("") ? "/" : toURL;
		
		return "redirect:" + toURL;
	}

	private boolean loginCheck(String id, String pwd) {
		return "asdf".equals(id) && "1234".equals(pwd);
	}
	
}
