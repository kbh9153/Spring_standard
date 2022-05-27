package com.fastcampus.ch2;

import java.net.URLEncoder;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

/*
 * @GetMapping
 *  - 요청방식을 GET만 가능하도록 함
 * 
 * @PostMapping
 * 
 * - 요청방식을 POST만 가능하도록 함
 * - Spring 4.3부터 사용가능
 * - Maven Dependencies에서 Spring 버전 확인
 * - Spring 버전 업그레이드 (pom.xml에서 업그레이드)
 	<properties>
		<org.springframework-version>5.0.7.RELEASE</org.springframework-version>	// 이 구문에서 버전 변경
	</properties>
 *
 * !Mapping URL 경로가 중복되면 안되지만 요청 method가 다를 경우 중복되어도 요청 method로 구분되기 때문에 에러 발생 X
 * 
 * URL 인코딩 (!= Base64)
 *  - URL에 포함된 non-ASCII 문자를 문자 코드(16진수) 문자열로 변환
 *  - ex. localhost:8080/ch2/add?name=홍길동 -> 홍길동(non-ASCII)은 텍스트가 아님
 *  - 홍길동 -> %ED%99%8D%EA%B8%B8%EB%8F%99 => URLEncoder.encode()
 *  - %ED%99%8D%EA%B8%B8%EB%8F%99 -> 홍길동 => URLDecoder.decode()
 */

@Controller
public class registerController {
	
//	@RequestMapping(value="/register/add", method={RequestMethod.POST, RequestMethod.GET})	 // {} 배열방식으로 POST, GET 요청방식 둘다 허용 가능
//	@PostMapping("/register/add")	
//	@GetMapping("/register/add")	
//	public String register() {	// 단순히 신규회원 가입 화면을 보여주는 기능만을 담당
//		return "registerForm";	// WEB-INF/views/registerForm.jsp
//	}
// spring -> appServlet -> servlet-context.xml에 <view-controller path="/register/add" view-name="registerForm" /> 추가하면 register() 메소드와 동일한 기능 수행 
	
//	@RequestMapping(value="/register/save", method=RequestMethod.POST)	// 요청 GET 방식으로 회원가입 불가능. 오직 POST 방식만 가능 (= @PostMapping)
	@PostMapping("/register/save")
	public String save(User user, Model model) throws Exception {		// 회원가입 진행
		// 1. 유효성 검사
		if (!isValid(user)) {
			String msg = URLEncoder.encode("ID를 잘못 입력하셨습니다.", "utf-8");	// 밑의 return과 같이 서버에서 URL을 직접 작성시 브라우저가 인코딩 불가. URLEncoder를 사용하여 직접 인코딩 필요
			
			model.addAttribute("msg", msg);	// 모델에 msg를 저장하여 view 파일에 전달
			return "redirect:/register/add";	// 밑의 코드와 동일한 기능
			// redirect는 재요청이기 때문에 model을 사용하여 view 파일에 전달이 불가능 -> 하지만 Spring이 자동적으로 위의 2줄의 코드를 밑의 1줄의 코드로 변환해주기 때문에 사용 가능
//			return "redirect:/registerForm/add" + msg;	// URL 재작성(rewriting)
		}
		// 2. DB에 신규회원 정보를 저장
		return "registerInfo";
	}

	private boolean isValid(User user) {
		return true;
	}
}
