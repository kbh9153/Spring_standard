package com.fastcampus.ch2;

import java.io.FileNotFoundException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ExceptionController {
	// @ExceptionHandler를 사용하면 try catch문을 대체. 단 Controller 내에서만 사용 가능
	// 여러 예외를 처리시 배열방식으로 처리 가능
	@ExceptionHandler({NullPointerException.class, Exception.class})
	public String catcher2(Exception ex, Model model) {
		model.addAttribute("ex", ex);
		System.out.println("catcher2 메소드에서 예외처리");
		return "error";
	}
	
//	@ExceptionHandler(Exception.class)
//	public String catcher(Exception ex, Model model) {
//		model.addAttribute("ex", ex);
//		return "error";
//	}
	
	@ExceptionHandler(FileNotFoundException.class)
	public String catcher(FileNotFoundException ex, Model model) {
		model.addAttribute("ex", ex);
		System.out.println("model = " + model);
		System.out.println("catcher 메소드에서 예외처리");
		return "error";
	}
	
	@RequestMapping("/ex")
	public String main() throws Exception {
			throw new Exception("Exception 예외가 발생하였습니다.");
	}
	
	@RequestMapping("/ex2")
	public String main2() throws NullPointerException {
			throw new NullPointerException("NullPointerException 예외가 발생하였습니다.");
	}
	
	@RequestMapping("/ex3")
	public String main3(Model model) throws FileNotFoundException {
		// 예외처리 메소드인 catcher 메소드의 model 매개변수와 전혀 다른 매개변수임 (예외처리 정보 전달을 위한 매개변수가 X)
		// 테스트를 위해 model에 msg 값을 추가 후 catcher에서 model을 출력해보면 값이 전달되지 않았음을 알 수 있음
		model.addAttribute("msg", "message from ExceptionController.main()");
		throw new FileNotFoundException("FileNotFoundException 예외가 발생하였습니다.");
	}
}
