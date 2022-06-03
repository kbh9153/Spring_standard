package com.fastcampus.ch2;

import java.util.Calendar;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/*
 * BindingResult
 * 
 * ex. code : public String main(@ModelAttribute MyDate date, BindingResult result) {}
 * ex. url : http://localhost:8080/ch2/getYoilMVC5?year=2022&month=5&day=26
 *  - 위 code 예시처럼 @ModelAttribute를 참조형 매개변수 앞에 사용시 Client 요청 처리결과를 자동으로 Model로 전송
 *  - url의 QueryString 값(String 타입)을 name=value 방식으로 저장
 *  - @ModelAttribute가 있어 MyDate 객체에 저장할 때 value 값을 Int 타입으로 변환이 필요
 *  - WebDataBinder이 자동으로 타입 변환을 실행하고 해당 데이터의 특정 범위가 설정되어 있으면 데이터 검증까지 실행
 *  - 실행 후 BidingResult에 변환, 검증된 값을 저장
 *  - BindingResult는 작성시 Binding할 객체 뒤에 작성해줘야함
 */

// webapp/WEB-INF/spring/root-context.xml : non-web 관련 설정파일
// webapp/WEB-INF/spring/appServlet/servlet-context.xml : web 관련 설정파일

@Controller
public class YoilTellerMVC6 {
	@ExceptionHandler(Exception.class)
	public String catcher(Exception ex, BindingResult result) {
		System.out.println("result = " + result);
		
		FieldError error = result.getFieldError();	// 에러발생시 상세정보
		
		System.out.println("code=" +error.getCode());	// 에러코드(=에러유형)
		System.out.println("field=" + error.getField());	// 에러발생필드(=에러발생key)
		System.out.println("message=" + error.getDefaultMessage());		// 에러메세지(=에러발생이유)
		
		return "yoilError";
	}
	
    @RequestMapping("/getYoilMVC6") // http://localhost/ch2/getYoilMVC4YoilTellerMVC6.java
	public String main(MyDate date, Model model, BindingResult result) {	// @ModelAttribute 생략한 상태 -> 타입명의 첫글자를 소문자로 자동으로 변경하여 실행(MyDate -> myDate)
 
    	System.out.println("result = " + result);
        // 1. 유효성 검사
    	if(!isValid(date)) 
    		return "yoilError";  // 유효하지 않으면, /WEB-INF/views/yoilError.jsp로 이동
    	
        // 2. 처리 -> @ModelAttribute("yoil")가 있어 구문(char yoil = getYoil(date);) 필요 X

        // 3. Model에 작업 결과 저장 -> @ModelAttribute가 main 메소드 매개변수에 작성되어 구문(model.addAttribute()) 필요 X
        
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