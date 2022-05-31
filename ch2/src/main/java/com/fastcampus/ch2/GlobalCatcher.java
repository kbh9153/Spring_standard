package com.fastcampus.ch2;

import java.io.FileNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/*
 * ���� ó�� �޼ҵ尡 �ߺ��� ���(ex. globalCatcher() and catcher()), ��Ʈ�ѷ� ��(������ Ŭ����)�� ���� ó�� �޼ҵ尡 �켱������ ����
 * 
 * ���������� ó���� ���ܴ� ������ ����ó�� Ŭ������ �ۼ� ��, ���������� ó���� ���ܴ� �ش� Ŭ������ ���� ó���� �ۼ�
 * 
 * @ResponseStatus(HttpStatus.����) : ���� ó���� http �����ڵ带 ����
 * 
 * ���� view ���� -> <% isErrorPage="true" %> : ���� Model�� ���Ͽ� ���� ��ü�� ���� view �������� �������� �ʾƵ� ���� ��ü�� ��� ����
 * 
 * web.xml -> <error-page></error-page>�� ���� �����ڵ庰 view �������� ���� ����
 * 
 * Exception Mapping : servlet-context.xml���� <beans:bean></beans:bean> �������� ���� �������� ���� view ������ ���� ����
 * 	-> ����! ���� view ������ <% isErrorPage="true %>�� ��� �����ڵ带 500���� ������ ��ȯ��. false�� ����� ������ �����ڵ�� ���
 */

// @ControllerAdvice�� ���� ��� Contoller���� �߻��ϴ� ���ܸ� ó�� ����
@ControllerAdvice	// ��Ű�� �������� ���, ��� ��Ű���� ����
// @ControllerAdvice("com.fastcampus.ch2")		// ��Ű�� ������ ���, �ش� ��Ű������ ����
public class GlobalCatcher {
	@ExceptionHandler({NullPointerException.class, FileNotFoundException.class})	// �迭�� ���� ó��
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)	// �����ڵ� 200 -> 500 ����
	public String globalCatcher(Exception e, Model model) {
		model.addAttribute("ex", e);
		System.out.println("globalCatcher ����ó��");
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
