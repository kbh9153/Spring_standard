package com.fastcampus.ch2;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller		// 1. 원격 호출이 가능한 프로그램으로 등록
public class HomeController {
	@RequestMapping(value = "/", method = RequestMethod.GET)	// 2. URL과 메소드 연결(맵핑, mapping) 
	public String home() {
		return "index";
	}

}