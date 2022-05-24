package com.fastcampus.ch2.prac;

import java.util.Calendar;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PracYoilTellerMVC {
	
	@RequestMapping("/pracGetYoilMVC")
	public String main(int year, int month, int day, Model model) {
		// �Է� ó�� �Ű������� �и���
		
		// 1. ��ȿ�� �˻�
		if (!isValid(year, month, day)) {
			return "./prac/pracYoilError";
		}
		
		// 2. ���� ��� (�۾� �и�)
		char yoil = getYoil(year, month, day);
		
		// 3. ���ϰ��� �𵨿� ���� -> (key, value) �������� ����
		model.addAttribute("year", year);
		model.addAttribute("month", month);
		model.addAttribute("day", day);
		model.addAttribute("yoil", yoil);
		
		// "pracYoil.jsp View" ���Ϸ� ��� �и�
		return "./prac/pracYoil";
	}

	private char getYoil(int year, int month, int day) {
		Calendar cal = Calendar.getInstance();
		cal.set(year, month - 1, day);
		
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
		return " �Ͽ�ȭ�������".charAt(dayOfWeek);
	}
	
	private boolean isValid(int year, int month, int day) {
		return true;
	}
}
