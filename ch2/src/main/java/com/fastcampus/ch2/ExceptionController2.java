package com.fastcampus.ch2;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)	// 상태코드 500 -> 400 변경
class MyException extends RuntimeException {
	MyException(String msg) {
		super(msg);
	}
	
	MyException() {}
}

@Controller
public class ExceptionController2 {
	// @ExceptionHandler를 사용하면 try catch문을 대체. 단 Controller 내에서만 사용 가능
	// 현재 해당 Controller 내부에 @ExceptionHandler가 없을 경우 예외처리가 불가능
//	@ExceptionHandler({NullPointerException.class, Exception.class})
//	public String catcher2(Exception ex, Model model) {
//		model.addAttribute("ex", ex);
//		return "error";
//	}
		
	@RequestMapping("/ex4")
	public String main() throws Exception {
			throw new MyException("Exception 예외가 발생하였습니다.");
	}
	
	@RequestMapping("/ex5")
	public String main2() throws NullPointerException {
			throw new NullPointerException("NullPointerException 예외가 발생하였습니다.");
	}
}
