package com.fastcampus.ch2.prac;

import java.util.Calendar;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PracYoilTellerMVC {
	
	@RequestMapping("/pracGetYoilMVC")
	public String main(int year, int month, int day, Model model) {
		// 입력 처리 매개변수로 분리함
		
		// 1. 유효성 검사
		if (!isValid(year, month, day)) {
			return "./prac/pracYoilError";
		}
		
		// 2. 요일 계산 (작업 분리)
		char yoil = getYoil(year, month, day);
		
		// 3. 요일값을 모델에 저장 -> (key, value) 형식으로 저장
		model.addAttribute("year", year);
		model.addAttribute("month", month);
		model.addAttribute("day", day);
		model.addAttribute("yoil", yoil);
		
		// "pracYoil.jsp View" 파일로 출력 분리
		return "./prac/pracYoil";
	}

	private char getYoil(int year, int month, int day) {
		Calendar cal = Calendar.getInstance();
		cal.set(year, month - 1, day);
		
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
		return " 일월화수목금토".charAt(dayOfWeek);
	}
	
	private boolean isValid(int year, int month, int day) {
		return true;
	}
}
