package com.fastcampus.ch2;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)	// �����ڵ� 500 -> 400 ����
class MyException extends RuntimeException {
	MyException(String msg) {
		super(msg);
	}
	
	MyException() {}
}

@Controller
public class ExceptionController2 {
	// @ExceptionHandler�� ����ϸ� try catch���� ��ü. �� Controller �������� ��� ����
	// ���� �ش� Controller ���ο� @ExceptionHandler�� ���� ��� ����ó���� �Ұ���
//	@ExceptionHandler({NullPointerException.class, Exception.class})
//	public String catcher2(Exception ex, Model model) {
//		model.addAttribute("ex", ex);
//		return "error";
//	}
		
	@RequestMapping("/ex4")
	public String main() throws Exception {
			throw new MyException("Exception ���ܰ� �߻��Ͽ����ϴ�.");
	}
	
	@RequestMapping("/ex5")
	public String main2() throws NullPointerException {
			throw new NullPointerException("NullPointerException ���ܰ� �߻��Ͽ����ϴ�.");
	}
}
