package com.fastcampus.ch2;

import java.io.FileNotFoundException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ExceptionController {
	// @ExceptionHandler�� ����ϸ� try catch���� ��ü. �� Controller �������� ��� ����
	// ���� ���ܸ� ó���� �迭������� ó�� ����
	@ExceptionHandler({NullPointerException.class, Exception.class})
	public String catcher2(Exception ex, Model model) {
		model.addAttribute("ex", ex);
		System.out.println("catcher2 �޼ҵ忡�� ����ó��");
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
		System.out.println("catcher �޼ҵ忡�� ����ó��");
		return "error";
	}
	
	@RequestMapping("/ex")
	public String main() throws Exception {
			throw new Exception("Exception ���ܰ� �߻��Ͽ����ϴ�.");
	}
	
	@RequestMapping("/ex2")
	public String main2() throws NullPointerException {
			throw new NullPointerException("NullPointerException ���ܰ� �߻��Ͽ����ϴ�.");
	}
	
	@RequestMapping("/ex3")
	public String main3(Model model) throws FileNotFoundException {
		// ����ó�� �޼ҵ��� catcher �޼ҵ��� model �Ű������� ���� �ٸ� �Ű������� (����ó�� ���� ������ ���� �Ű������� X)
		// �׽�Ʈ�� ���� model�� msg ���� �߰� �� catcher���� model�� ����غ��� ���� ���޵��� �ʾ����� �� �� ����
		model.addAttribute("msg", "message from ExceptionController.main()");
		throw new FileNotFoundException("FileNotFoundException ���ܰ� �߻��Ͽ����ϴ�.");
	}
}
