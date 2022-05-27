package com.fastcampus.ch2;

import java.util.Calendar;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/*
 * Controller 메소드
 *  - RequestParam : 기본형 타입, String 타입의 매개변수 앞에 자동으로 생성
 *  - ModelAttribute : 참조형 타입의 매개변수 앞에 자동으로 생성
 */

@Controller
public class YoilTellerMVC5 {
	@ExceptionHandler(Exception.class)
	public String catcher(Exception ex) {
		return "yoilError";
	}
	
    @RequestMapping("/getYoilMVC5") // http://localhost/ch2/getYoilMVC4YoilTellerMVC5.java
    // @ModelAttribute : 작업 결과를 Model에 자동으로 저장. 참조형 매개변수 앞에 사용 가능(생략 가능 -> 생략하여도 자동으로 생성됨. 위의 Controller 매개변수 메모 참조)
//  public String main(@ModelAttribute("myDate") MyDate date, Model model) {	// 아래와 동일
	public String main(@ModelAttribute MyDate date, Model model) {	// ("myDate")를 생략시 타입명의 첫글자를 소문자로 자동으로 변경하여 실행(MyDate -> myDate)
 
        // 1. 유효성 검사
    	if(!isValid(date)) 
    		return "yoilError";  // 유효하지 않으면, /WEB-INF/views/yoilError.jsp로 이동
    	
        // 2. 처리
//    	char yoil = getYoil(date);	// @ModelAttribute("yoil")가 있어 필요 X

        // 3. Model에 작업 결과 저장
//        model.addAttribute("myDate", date);	// @ModelAttribute가 main 메소드 매개변수에 작성되어 필요 X
//        model.addAttribute("yoil", yoil);		// @ModelAttribute가 main 메소드 매개변수에 작성되어 필요 X
        
        // 4. 작업 결과를 보여줄 View의 이름을 반환(View로 전달)
        return "yoil"; // /WEB-INF/views/yoil.jsp
    }
    
    private boolean isValid(MyDate date) {
		return isValid(date.getYear(), date.getMonth(), date.getDay());
	}

	private @ModelAttribute("yoil") char getYoil(MyDate date) {		// @ModelAttribute("yoil") : 해당 함수를 자동으로 호출하고 처리 결과를 Model에 자동으로 저장
		return getYoil(date.getYear(), date.getMonth(), date.getDay());
	}

	private char getYoil(int year, int month, int day) {
        Calendar cal = Calendar.getInstance();
        cal.set(year, month - 1, day);

        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
        return " 일월화수목금토".charAt(dayOfWeek);
    }
    
    private boolean isValid(int year, int month, int day) {    
    	if(year==-1 || month==-1 || day==-1) 
    		return false;
    	
    	return (1<=month && month<=12) && (1<=day && day<=31); // 간단히 체크 
    }
} 