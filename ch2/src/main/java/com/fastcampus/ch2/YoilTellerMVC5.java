package com.fastcampus.ch2;

import java.util.Calendar;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/*
 * Controller �޼ҵ�
 *  - RequestParam : �⺻�� Ÿ��, String Ÿ���� �Ű����� �տ� �ڵ����� ����
 *  - ModelAttribute : ������ Ÿ���� �Ű����� �տ� �ڵ����� ����
 */

@Controller
public class YoilTellerMVC5 {
	@ExceptionHandler(Exception.class)
	public String catcher(Exception ex) {
		return "yoilError";
	}
	
    @RequestMapping("/getYoilMVC5") // http://localhost/ch2/getYoilMVC4YoilTellerMVC5.java
    // @ModelAttribute : �۾� ����� Model�� �ڵ����� ����. ������ �Ű����� �տ� ��� ����(���� ���� -> �����Ͽ��� �ڵ����� ������. ���� Controller �Ű����� �޸� ����)
//  public String main(@ModelAttribute("myDate") MyDate date, Model model) {	// �Ʒ��� ����
	public String main(@ModelAttribute MyDate date, Model model) {	// ("myDate")�� ������ Ÿ�Ը��� ù���ڸ� �ҹ��ڷ� �ڵ����� �����Ͽ� ����(MyDate -> myDate)
 
        // 1. ��ȿ�� �˻�
    	if(!isValid(date)) 
    		return "yoilError";  // ��ȿ���� ������, /WEB-INF/views/yoilError.jsp�� �̵�
    	
        // 2. ó��
//    	char yoil = getYoil(date);	// @ModelAttribute("yoil")�� �־� �ʿ� X

        // 3. Model�� �۾� ��� ����
//        model.addAttribute("myDate", date);	// @ModelAttribute�� main �޼ҵ� �Ű������� �ۼ��Ǿ� �ʿ� X
//        model.addAttribute("yoil", yoil);		// @ModelAttribute�� main �޼ҵ� �Ű������� �ۼ��Ǿ� �ʿ� X
        
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