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
 *  - �� code ����ó�� @ModelAttribute�� ������ �Ű����� �տ� ���� Client ��û ó������� �ڵ����� Model�� ����
 *  - url�� QueryString ��(String Ÿ��)�� name=value ������� ����
 *  - @ModelAttribute�� �־� MyDate ��ü�� ������ �� value ���� Int Ÿ������ ��ȯ�� �ʿ�
 *  - WebDataBinder�� �ڵ����� Ÿ�� ��ȯ�� �����ϰ� �ش� �������� Ư�� ������ �����Ǿ� ������ ������ �������� ����
 *  - ���� �� BidingResult�� ��ȯ, ������ ���� ����
 *  - BindingResult�� �ۼ��� Binding�� ��ü �ڿ� �ۼ��������
 */

// webapp/WEB-INF/spring/root-context.xml : non-web ���� ��������
// webapp/WEB-INF/spring/appServlet/servlet-context.xml : web ���� ��������

@Controller
public class YoilTellerMVC6 {
	@ExceptionHandler(Exception.class)
	public String catcher(Exception ex, BindingResult result) {
		System.out.println("result = " + result);
		
		FieldError error = result.getFieldError();	// �����߻��� ������
		
		System.out.println("code=" +error.getCode());	// �����ڵ�(=��������)
		System.out.println("field=" + error.getField());	// �����߻��ʵ�(=�����߻�key)
		System.out.println("message=" + error.getDefaultMessage());		// �����޼���(=�����߻�����)
		
		return "yoilError";
	}
	
    @RequestMapping("/getYoilMVC6") // http://localhost/ch2/getYoilMVC4YoilTellerMVC6.java
	public String main(MyDate date, Model model, BindingResult result) {	// @ModelAttribute ������ ���� -> Ÿ�Ը��� ù���ڸ� �ҹ��ڷ� �ڵ����� �����Ͽ� ����(MyDate -> myDate)
 
    	System.out.println("result = " + result);
        // 1. ��ȿ�� �˻�
    	if(!isValid(date)) 
    		return "yoilError";  // ��ȿ���� ������, /WEB-INF/views/yoilError.jsp�� �̵�
    	
        // 2. ó�� -> @ModelAttribute("yoil")�� �־� ����(char yoil = getYoil(date);) �ʿ� X

        // 3. Model�� �۾� ��� ���� -> @ModelAttribute�� main �޼ҵ� �Ű������� �ۼ��Ǿ� ����(model.addAttribute()) �ʿ� X
        
        // 4. �۾� ����� ������ View�� �̸��� ��ȯ(View�� ����)
        return "yoil"; // /WEB-INF/views/yoil.jsp
    }
    
    private boolean isValid(MyDate date) {
		return isValid(date.getYear(), date.getMonth(), date.getDay());
	}

	private @ModelAttribute("yoil") char getYoil(MyDate date) {		// @ModelAttribute("yoil") : �ش� �Լ��� �ڵ����� ȣ���ϰ� ó�� ����� Model�� �ڵ����� ����
		return getYoil(date.getYear(), date.getMonth(), date.getDay());
	}

	private char getYoil(int year, int month, int day) {
        Calendar cal = Calendar.getInstance();
        cal.set(year, month - 1, day);

        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
        return " �Ͽ�ȭ�������".charAt(dayOfWeek);
    }
    
    private boolean isValid(int year, int month, int day) {    
    	if(year==-1 || month==-1 || day==-1) 
    		return false;
    	
    	return (1<=month && month<=12) && (1<=day && day<=31); // ������ üũ 
    }
} 