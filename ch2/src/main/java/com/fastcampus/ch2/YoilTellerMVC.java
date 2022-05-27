package com.fastcampus.ch2;

import java.io.IOException;
import java.util.Calendar;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

// YoilTellerMVC : YoilTeller 코드를 MVC 패턴을 사용하여 관심사 분리 처리(입력, 출력 부분을 분리)
@Controller
public class YoilTellerMVC {
	
	@ExceptionHandler(Exception.class)	// 예외처리
	public String catcher(Exception ex) {
		ex.printStackTrace();
		return "yoilError";
	}
	
	@RequestMapping("/getYoilMVC")	// Mapping은 중복된 값이 있으면 안됨
//	public static void main(String[] args) {
//	public void main(HttpServletRequest request, HttpServletResponse response) throws IOException {
//	public void main(String year, String month, String day, HttpServletResponse response) throws IOException {		// HttpServletRequest 대신 직접 year, month, day를 매개변수로 받음
//	public void main(int year, int month, int day, Model model) throws IOException {	
		// 반환 타입을 void로 변경, return "yoil";이 없어도 가능. Mapping으로 설정된 urㅣ와 동일한 view(jsp)파일이 있으면 가능
//	public ModelAndView main(int year, int month, int day) throws IOException {
		// 매개변수로 model을 받지않고 반환 타입을 ModelAndView로 설정
	public String main(@RequestParam(required=true) int year, 
					   @RequestParam(required=true)int month,
					   @RequestParam(required=true)int day,
					   @RequestParam(required=true)Model model) throws IOException {	// String 타입인 URL을 int 타입으로 변환 과정없이 year, month, day를 int 타입의 매개변수로 받음 -> 주로 사용하는 방식
		// 반환 타입이 String인 이유는 return "yoil"; 코드와 같이 반환하는 view 파일의 이름이 문자열이기 때문
		
		// 1. 입력 -> 매개변수 변경으로 분리하여 필요 X
//		String year = request.getParameter("year");
//		String month = request.getParameter("month");
//		String day = request.getParameter("day");
		
		// 2. 작업 -> 매개변수 변경으로 분리하여 필요 X
		// URL은 String. request를 통해 전달받은 값을 정수로 변환
//		int yyyy = Integer.parseInt(year);
//		int mm = Integer.parseInt(month);
//		int dd = Integer.parseInt(day);
		
		// ModelAndView 반환 타입을 사용할 경우 메소드는 하나의 값만 반환할 수 있기 때문에 Model과 View를 같이 반환할 수 있는 ModelAndView 객체를 생성
//		ModelAndView mv = new ModelAndView();
		
		// 1. 유효성 검사
		if (!isValid(year, month, day)) {
			return "yoilError";
		}
		
		// 2. 요일 계산
		char yoil = getYoil(year, month, day);
		
		// 3. 계산한 결과를 model에 저장
		model.addAttribute("year", year);	// model에 key, value 형식으로 저장
		model.addAttribute("month", month);
		model.addAttribute("day", day);
		model.addAttribute("yoil", yoil);
		
//		mv.addObject("year", year);
//		mv.addObject("month", month);
//		mv.addObject("day", day);
//		mv.addObject("yoil", yoil);
		
		// 4. 결과를 보여줄 view를 지정(메소드 반환타입이 ModelAndView일 경우)
//		mv.setViewName("yoil");
//		return mv;
		
		return "yoil";	// WEB-INF/views/yoil.jsp를 이용하여 응답 처리 후 출력하도록 전달. 중복된 경로를 제외하고 view 파일의 이름만 작성하면 됨
		/*
		 * spring -> appServlet -> servlet-context.xml의
		 * 
		 * <beans:bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
		 *	<beans:property name="prefix" value="/WEB-INF/views/" />
		 *	<beans:property name="suffix" value=".jsp" />
		 * </beans:bean>
		 * 
		 * 설정 덕분에 중복된 경로를 제외하여 작성 가능
		 */
	}
	
	private boolean isValid(int year, int month, int day) {
		return true;
	}
	
	private char getYoil(int year, int month, int day) {	// 코드 범위 지정 -> Refactor -> Extract Method를 통해 메소드 자동 생성 
		Calendar cal = Calendar.getInstance();
		cal.set(year, month - 1, day);
		
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);		// 1. 일요일, 2. 월요일, 3. 화요일 ...
		return " 일월화수목금토".charAt(dayOfWeek);	// dayOfWeek이 1이면 일, 2이면 월, 3이면 화. 0은 공백
	}
		
		// 3. 출력 -> view 파일로 분리
		// 브라우저는 전송받는 내용이 텍스트인지 바이너리인지 구분하지 못하기 때문에 데이터 타입, 인코딩 타입이 무엇인지 브라우저에게 알려줘야함
//		response.setContentType("text/html");	// 출력하기 전 텍스트타입을 지정해야함. 
//		response.setCharacterEncoding("utf-8");		// 출력하기 전 인코딩타입을 지정해야함
		
//		PrintWriter out = response.getWriter();	// response 객체에서 브라우저로의 출력 스트림을 얻음
		
//		out.println("<html>");
//		out.println("<head>");
//		out.println("</head>");
//		out.println("<body>"); 		
//		out.println(year + "년 " + month + "월 " + day + "일은 ");
//		out.println(yoil + "요일입니다.");
//		out.println("</body>");
//		out.println("</html>");
}
