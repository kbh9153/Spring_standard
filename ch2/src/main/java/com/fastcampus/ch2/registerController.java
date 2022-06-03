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
 *  
 * @InitBinder
 *  - 해당 Controller로 들어오는 요청에 대해 추가적인 설정을 원할 때 사용(해당 Controller 내부에서만 사용 가능)
 *  - 모든 요청 전에 InitBinder를 선언한 메소드가 우선 실행
 *  - 타입 변환 등 여러 필드에 대해서 처리할 경우 유용
 *  
 * @DateTimeFormat(pattern="yyyy-MM-dd")
 *  - Date 타입으로 변환하고자 하는 필드 앞에 사용
 *  
 * PropertyEditor
 *  - 양방향 타입 변환(String -> 타입, 타입 -> String)
 *  - 특정 타입이나 이름의 필드에 적용 가능(stateful)
 *  - 디폴트 PropertyEditor : 스프링이 기본적으로 제공
 *  - 커스텀 PropertyEditor : 사용자가 직접 구현. PropertyEditorSupport를 상속하면 사용하기 편리
 *   => propertyeditors 검색 후 docs.spring.io에서 종류 참조
 *   
 * Converter
 *  - 단뱡향 타입 변환(타입 A -> 타입 B)
 *  - PropertyEditor의 단점을 개선(stateless : 특정 필드가 필요없음)
 *  - 단, 변환 후 ConversionService에 등록해야함
 *  - ConversionService : 타입 변환 서비스를 제공. 여러 Converter를 등록 가능
 *  
 * Formatter
 *  - 양방향 타입 변환(String -> 타입, 타입 -> String)
 *  - 바인딩할 필드에 적용 : @NumberFormat, @DateTimeForamt
 */ 

@Controller
public class RegisterController {
	@InitBinder
	public void toDate(WebDataBinder binder) {	// String -> Date 타입으로 변환 : @DateTimeForamt annotation으로 밑의 2줄 코드 대체 가능
//		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");	// 형식 지정
//		binder.registerCustomEditor(Date.class, new CustomDateEditor(df, false));	// String -> Date 타입으로 변환 -> CustomDateEditor 클래스 이용(false는 빈값 허용 여부)
		ConversionService conversionseService = binder.getConversionService();
		System.out.println("conversionservice=" + conversionseService);
		binder.registerCustomEditor(String[].class, new StringArrayPropertyEditor("#"));	// String -> String 배열 타입으로 변환 -> StringArrayPropertyEditor("구분자") 클래스 이용
		binder.registerCustomEditor(String[].class, "hobby", new StringArrayPropertyEditor("#"));	// "hobby"와 같이 특정 필드를 지정 가능
	}
	
//	@PostMapping("/register/add")	
//	@GetMapping("/register/add")	
	@RequestMapping(value="/register/add", method={RequestMethod.POST, RequestMethod.GET})	 // {} 배열방식으로 POST, GET 요청방식 둘다 허용 가능
	public String register() {	// 단순히 신규회원 가입 화면을 보여주는 기능만을 담당
		return "registerForm";	// WEB-INF/views/registerForm.jsp
	}
// spring -> appServlet -> servlet-context.xml에 <view-controller path="/register/add" view-name="registerForm" /> 추가하면 register() 메소드와 동일한 기능 수행 
	
//	@RequestMapping(value="/register/save", method=RequestMethod.POST)	// 요청 GET 방식으로 회원가입 불가능. 오직 POST 방식만 가능 (= @PostMapping)
	@PostMapping("/register/save")
	public String save(User user, BindingResult result, Model model) throws Exception {		// 회원가입 진행
		System.out.println("result : " + result);
		System.out.println("user : " + user);
		
		// 1. 유효성 검사
		if (!isValid(user)) {
			String msg = URLEncoder.encode("ID를 잘못 입력하셨습니다.", "utf-8");	// 밑의 return과 같이 서버에서 URL을 직접 작성시 브라우저가 인코딩 불가. URLEncoder를 사용하여 직접 인코딩 필요
			
			model.addAttribute("msg", msg);	// 모델에 msg를 저장하여 view 파일에 전달
			return "forward:/register/add";	// 밑의 코드와 동일한 기능
			// redirect는 재요청이기 때문에 model을 사용하여 view 파일에 전달이 불가능 -> 하지만 Spring이 자동적으로 위의 2줄의 코드를 밑의 1줄의 코드로 변환해주기 때문에 사용 가능
//			return "redirect:/registerForm/add" + msg;	// URL 재작성(rewriting)
		}
		// 2. DB에 신규회원 정보를 저장
		return "registerInfo";
	}

	private boolean isValid(User user) {
		return true	;
	}
}
