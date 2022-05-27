package com.fastcampus.ch2;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/* 
 * JSP = Servlet
 * 
 * JSP : HTML 코드 내 Java 코드가 작성됨
 * JSP로 작성하면 JSP가 자동으로 Servlet으로 변환
 * 
 * .JSP 파일 문법
 * <%! %> : 클래스 영역
 * <% %> : 메소드 영역 (service()의 내부)
 * 
 * ${} : EL(Express Language). 모델 객체가 가지고 있는 값이 들어갈 자리
 * 
 * JSP와 Spring 둘다 싱글톤
 *  - 싱글톤 : 하나의 객체로 생성 후 똑같은 작업에 대해서는 기존 생성된 객체를 재활용. 다만 객체 내용 변경이 있을 시 다시 객체 생성
 *  
 * JSP 호출 과정
 *  - .JSP 요청 -> 서블릿 인스턴스가 존재 X -> (.jsp -> .java -> .class) 변환 과정 진행 -> 인스턴스 생성(_jspInit()) -> 서블릿 인스턴스 처리(_jspService()) -> 응답
 *  - .JSP 요청 -> 서블릿 인스턴스가 존재 O -> 기존 서블릿 인스턴스 재활용하여 처리 -> 응답
 *  - 첫번째 호출시 (.jsp -> .java -> .class) 변환 과정 때문에 시간이 지연됨(Lazy-Init : 늦은 초기화)
 *  - loadOnStartUp을 사용하면 Early-Init 가능
 *  
 * Spring 호출 과정
 *  - Spring은 JSP와 달리 요청이 왔을 때 인스턴스를 생성하는 것이 아니라 요청 이전 미리 인스턴스를 생성
 *  - 때문에 JSP의 (.jsp -> .java -> .class) 변환 과정과 같은 과정없이 바로 미리 생성된 인스턴스로 요청 처리하여 응답(Early-Init)
 *  
 * View 파일 내 EL 구문 작성시 주의점!
 *  - ex.${msg} : 일반적인 EL(Server에서 작동) 구문으로 보이지만 EL이 아닌 Templet Literal(JS6, Browser에서 작동)임. 문법이 서로 동일함
 *  - 실행순서가 Server -> Browser
 *  - Server에서 ${msg}을 EL 구문으로 인식하지 않고 msg로 인식하여 데이터가 출력되지 않음
 *  - 때문에 ${msg}로 인식하게 하기 위해 ${'${msg}'} 식으로 ${msg} 구문을 ${}으로 한번 더 감싸줘야 함 
 */

@WebServlet("/hello")
public class HelloServlet extends HttpServlet {
	@Override
	public void init() throws ServletException {
		// 서블릿이 초기화될 때 자동으로 호출되는 메소드
		// 1. 서블릿의 초기화 작업 담당(인스턴스 생성)
		System.out.println("[HelloServlet] init() is called.");
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1. 입력
		// 2. 처리
		// 3. 출력
		System.out.println("[HelloServlet] service() is called.");
	}
	
	
	@Override
	public void destroy() {
		// 3. 뒷정리 - 서블릿이 메모리에서 제거될 때 서블릿 컨테이너에 의해서 자동 호출
		System.out.println("[HelloServlet] destroy() is called.");
	}
}
 