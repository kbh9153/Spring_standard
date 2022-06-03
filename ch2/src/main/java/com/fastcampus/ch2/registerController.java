package com.fastcampus.ch2;

import java.net.URLEncoder;

import org.springframework.beans.propertyeditors.StringArrayPropertyEditor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/*
 * @GetMapping
 *  - ��û����� GET�� �����ϵ��� ��
 * 
 * @PostMapping
 * 
 * - ��û����� POST�� �����ϵ��� ��
 * - Spring 4.3���� ��밡��
 * - Maven Dependencies���� Spring ���� Ȯ��
 * - Spring ���� ���׷��̵� (pom.xml���� ���׷��̵�)
 	<properties>
		<org.springframework-version>5.0.7.RELEASE</org.springframework-version>	// �� �������� ���� ����
	</properties>
 *
 * !Mapping URL ��ΰ� �ߺ��Ǹ� �ȵ����� ��û method�� �ٸ� ��� �ߺ��Ǿ ��û method�� ���еǱ� ������ ���� �߻� X
 * 
 * URL ���ڵ� (!= Base64)
 *  - URL�� ���Ե� non-ASCII ���ڸ� ���� �ڵ�(16����) ���ڿ��� ��ȯ
 *  - ex. localhost:8080/ch2/add?name=ȫ�浿 -> ȫ�浿(non-ASCII)�� �ؽ�Ʈ�� �ƴ�
 *  - ȫ�浿 -> %ED%99%8D%EA%B8%B8%EB%8F%99 => URLEncoder.encode()
 *  - %ED%99%8D%EA%B8%B8%EB%8F%99 -> ȫ�浿 => URLDecoder.decode()
 *  
 * @InitBinder
 *  - �ش� Controller�� ������ ��û�� ���� �߰����� ������ ���� �� ���(�ش� Controller ���ο����� ��� ����)
 *  - ��� ��û ���� InitBinder�� ������ �޼ҵ尡 �켱 ����
 *  - Ÿ�� ��ȯ �� ���� �ʵ忡 ���ؼ� ó���� ��� ����
 *  
 * @DateTimeFormat(pattern="yyyy-MM-dd")
 *  - Date Ÿ������ ��ȯ�ϰ��� �ϴ� �ʵ� �տ� ���
 *  
 * PropertyEditor
 *  - ����� Ÿ�� ��ȯ(String -> Ÿ��, Ÿ�� -> String)
 *  - Ư�� Ÿ���̳� �̸��� �ʵ忡 ���� ����(stateful)
 *  - ����Ʈ PropertyEditor : �������� �⺻������ ����
 *  - Ŀ���� PropertyEditor : ����ڰ� ���� ����. PropertyEditorSupport�� ����ϸ� ����ϱ� ��
 *   => propertyeditors �˻� �� docs.spring.io���� ���� ����
 *   
 * Converter
 *  - �ܓ��� Ÿ�� ��ȯ(Ÿ�� A -> Ÿ�� B)
 *  - PropertyEditor�� ������ ����(stateless : Ư�� �ʵ尡 �ʿ����)
 *  - ��, ��ȯ �� ConversionService�� ����ؾ���
 *  - ConversionService : Ÿ�� ��ȯ ���񽺸� ����. ���� Converter�� ��� ����
 *  
 * Formatter
 *  - ����� Ÿ�� ��ȯ(String -> Ÿ��, Ÿ�� -> String)
 *  - ���ε��� �ʵ忡 ���� : @NumberFormat, @DateTimeForamt
 */ 

@Controller
public class RegisterController {
	@InitBinder
	public void toDate(WebDataBinder binder) {	// String -> Date Ÿ������ ��ȯ : @DateTimeForamt annotation���� ���� 2�� �ڵ� ��ü ����
//		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");	// ���� ����
//		binder.registerCustomEditor(Date.class, new CustomDateEditor(df, false));	// String -> Date Ÿ������ ��ȯ -> CustomDateEditor Ŭ���� �̿�(false�� �� ��� ����)
		ConversionService conversionseService = binder.getConversionService();
		System.out.println("conversionservice=" + conversionseService);
		binder.registerCustomEditor(String[].class, new StringArrayPropertyEditor("#"));	// String -> String �迭 Ÿ������ ��ȯ -> StringArrayPropertyEditor("������") Ŭ���� �̿�
		binder.registerCustomEditor(String[].class, "hobby", new StringArrayPropertyEditor("#"));	// "hobby"�� ���� Ư�� �ʵ带 ���� ����
	}
	
//	@PostMapping("/register/add")	
//	@GetMapping("/register/add")	
	@RequestMapping(value="/register/add", method={RequestMethod.POST, RequestMethod.GET})	 // {} �迭������� POST, GET ��û��� �Ѵ� ��� ����
	public String register() {	// �ܼ��� �ű�ȸ�� ���� ȭ���� �����ִ� ��ɸ��� ���
		return "registerForm";	// WEB-INF/views/registerForm.jsp
	}
// spring -> appServlet -> servlet-context.xml�� <view-controller path="/register/add" view-name="registerForm" /> �߰��ϸ� register() �޼ҵ�� ������ ��� ���� 
	
//	@RequestMapping(value="/register/save", method=RequestMethod.POST)	// ��û GET ������� ȸ������ �Ұ���. ���� POST ��ĸ� ���� (= @PostMapping)
	@PostMapping("/register/save")
	public String save(User user, BindingResult result, Model model) throws Exception {		// ȸ������ ����
		System.out.println("result : " + result);
		System.out.println("user : " + user);
		
		// 1. ��ȿ�� �˻�
		if (!isValid(user)) {
			String msg = URLEncoder.encode("ID�� �߸� �Է��ϼ̽��ϴ�.", "utf-8");	// ���� return�� ���� �������� URL�� ���� �ۼ��� �������� ���ڵ� �Ұ�. URLEncoder�� ����Ͽ� ���� ���ڵ� �ʿ�
			
			model.addAttribute("msg", msg);	// �𵨿� msg�� �����Ͽ� view ���Ͽ� ����
			return "forward:/register/add";	// ���� �ڵ�� ������ ���
			// redirect�� ���û�̱� ������ model�� ����Ͽ� view ���Ͽ� ������ �Ұ��� -> ������ Spring�� �ڵ������� ���� 2���� �ڵ带 ���� 1���� �ڵ�� ��ȯ���ֱ� ������ ��� ����
//			return "redirect:/registerForm/add" + msg;	// URL ���ۼ�(rewriting)
		}
		// 2. DB�� �ű�ȸ�� ������ ����
		return "registerInfo";
	}

	private boolean isValid(User user) {
		return true	;
	}
}
