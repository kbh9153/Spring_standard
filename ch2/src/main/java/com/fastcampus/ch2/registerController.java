package com.fastcampus.ch2;

import java.net.URLEncoder;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

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
 */

@Controller
public class registerController {
	
//	@RequestMapping(value="/register/add", method={RequestMethod.POST, RequestMethod.GET})	 // {} �迭������� POST, GET ��û��� �Ѵ� ��� ����
//	@PostMapping("/register/add")	
//	@GetMapping("/register/add")	
//	public String register() {	// �ܼ��� �ű�ȸ�� ���� ȭ���� �����ִ� ��ɸ��� ���
//		return "registerForm";	// WEB-INF/views/registerForm.jsp
//	}
// spring -> appServlet -> servlet-context.xml�� <view-controller path="/register/add" view-name="registerForm" /> �߰��ϸ� register() �޼ҵ�� ������ ��� ���� 
	
//	@RequestMapping(value="/register/save", method=RequestMethod.POST)	// ��û GET ������� ȸ������ �Ұ���. ���� POST ��ĸ� ���� (= @PostMapping)
	@PostMapping("/register/save")
	public String save(User user, Model model) throws Exception {		// ȸ������ ����
		// 1. ��ȿ�� �˻�
		if (!isValid(user)) {
			String msg = URLEncoder.encode("ID�� �߸� �Է��ϼ̽��ϴ�.", "utf-8");	// ���� return�� ���� �������� URL�� ���� �ۼ��� �������� ���ڵ� �Ұ�. URLEncoder�� ����Ͽ� ���� ���ڵ� �ʿ�
			
			model.addAttribute("msg", msg);	// �𵨿� msg�� �����Ͽ� view ���Ͽ� ����
			return "redirect:/register/add";	// ���� �ڵ�� ������ ���
			// redirect�� ���û�̱� ������ model�� ����Ͽ� view ���Ͽ� ������ �Ұ��� -> ������ Spring�� �ڵ������� ���� 2���� �ڵ带 ���� 1���� �ڵ�� ��ȯ���ֱ� ������ ��� ����
//			return "redirect:/registerForm/add" + msg;	// URL ���ۼ�(rewriting)
		}
		// 2. DB�� �ű�ȸ�� ������ ����
		return "registerInfo";
	}

	private boolean isValid(User user) {
		return true;
	}
}
