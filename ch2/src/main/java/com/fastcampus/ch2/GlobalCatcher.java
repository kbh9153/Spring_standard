package com.fastcampus.ch2;

import java.io.FileNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/*
 * 예외 처리 메소드가 중복된 경우(ex. globalCatcher() and catcher()), 컨트롤러 내(각각의 클래스)의 예외 처리 메소드가 우선적으로 실행
 * 
 * 공통적으로 처리할 예외는 별도의 예외처리 클래스를 작성 후, 개별적으로 처리할 예외는 해당 클래스에 예외 처리를 작성
 * 
 * @ResponseStatus(HttpStatus.상태) : 예외 처리시 http 상태코드를 변경
 * 
 * 에러 view 파일 -> <% isErrorPage="true" %> : 사용시 Model을 통하여 에러 객체를 에러 view 페이지에 전달하지 않아도 에러 객체를 사용 가능
 * 
 * web.xml -> <error-page></error-page>을 통해 상태코드별 view 페이지를 지정 가능
 * 
 * Exception Mapping : servlet-context.xml에서 <beans:bean></beans:bean> 구문으로 예외 종류별로 에러 view 페이지 지정 가능
 * 	-> 주의! 에러 view 파일의 <% isErrorPage="true %>의 경우 상태코드를 500으로 강제로 변환됨. false로 변경시 지정된 상태코드로 출력
 */

// @ControllerAdvice를 사용시 모든 Contoller에서 발생하는 예외를 처리 가능
@ControllerAdvice	// 패키지 지정안할 경우, 모든 패키지에 적용
// @ControllerAdvice("com.fastcampus.ch2")		// 패키지 지정할 경우, 해당 패키지에만 적용
public class GlobalCatcher {
	@ExceptionHandler({NullPointerException.class, FileNotFoundException.class})	// 배열로 예외 처리
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)	// 상태코드 200 -> 500 변경
	public String globalCatcher(Exception e, Model model) {
		model.addAttribute("ex", e);
		System.out.println("globalCatcher 예외처리");
		return "error";
	}
	
//	@ExceptionHandler(Exception.class)
//	public String catcher1(Exception ex, Model model) {
//		model.addAttribute("ex", ex);
//		return "error";
//	}
	
//	@ExceptionHandler(NullPointerException.class)
//	public String catcher1(NullPointerException ex, Model model) {
//		model.addAttribute("ex", ex);
//		return "error";
//	}
	
//	@ExceptionHandler(FileNotFoundException.class)
//	public String catcher1(FileNotFoundException ex, Model model) {
//		model.addAttribute("ex", ex);
//		return "error";
//	}
}
