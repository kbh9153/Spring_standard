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
 * JSP : HTML �ڵ� �� Java �ڵ尡 �ۼ���
 * JSP�� �ۼ��ϸ� JSP�� �ڵ����� Servlet���� ��ȯ
 * 
 * .JSP ���� ����
 * <%! %> : Ŭ���� ����
 * <% %> : �޼ҵ� ���� (service()�� ����)
 * 
 * ${} : EL(Express Language). �� ��ü�� ������ �ִ� ���� �� �ڸ�
 * 
 * JSP�� Spring �Ѵ� �̱���
 *  - �̱��� : �ϳ��� ��ü�� ���� �� �Ȱ��� �۾��� ���ؼ��� ���� ������ ��ü�� ��Ȱ��. �ٸ� ��ü ���� ������ ���� �� �ٽ� ��ü ����
 *  
 * JSP ȣ�� ����
 *  - .JSP ��û -> ���� �ν��Ͻ��� ���� X -> (.jsp -> .java -> .class) ��ȯ ���� ���� -> �ν��Ͻ� ����(_jspInit()) -> ���� �ν��Ͻ� ó��(_jspService()) -> ����
 *  - .JSP ��û -> ���� �ν��Ͻ��� ���� O -> ���� ���� �ν��Ͻ� ��Ȱ���Ͽ� ó�� -> ����
 *  - ù��° ȣ��� (.jsp -> .java -> .class) ��ȯ ���� ������ �ð��� ������(Lazy-Init : ���� �ʱ�ȭ)
 *  - loadOnStartUp�� ����ϸ� Early-Init ����
 *  
 * Spring ȣ�� ����
 *  - Spring�� JSP�� �޸� ��û�� ���� �� �ν��Ͻ��� �����ϴ� ���� �ƴ϶� ��û ���� �̸� �ν��Ͻ��� ����
 *  - ������ JSP�� (.jsp -> .java -> .class) ��ȯ ������ ���� �������� �ٷ� �̸� ������ �ν��Ͻ��� ��û ó���Ͽ� ����(Early-Init)
 *  
 * View ���� �� EL ���� �ۼ��� ������!
 *  - ex.${msg} : �Ϲ����� EL(Server���� �۵�) �������� �������� EL�� �ƴ� Templet Literal(JS6, Browser���� �۵�)��. ������ ���� ������
 *  - ��������� Server -> Browser
 *  - Server���� ${msg}�� EL �������� �ν����� �ʰ� msg�� �ν��Ͽ� �����Ͱ� ��µ��� ����
 *  - ������ ${msg}�� �ν��ϰ� �ϱ� ���� ${'${msg}'} ������ ${msg} ������ ${}���� �ѹ� �� ������� �� 
 */

@WebServlet("/hello")
public class HelloServlet extends HttpServlet {
	@Override
	public void init() throws ServletException {
		// ������ �ʱ�ȭ�� �� �ڵ����� ȣ��Ǵ� �޼ҵ�
		// 1. ������ �ʱ�ȭ �۾� ���(�ν��Ͻ� ����)
		System.out.println("[HelloServlet] init() is called.");
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1. �Է�
		// 2. ó��
		// 3. ���
		System.out.println("[HelloServlet] service() is called.");
	}
	
	
	@Override
	public void destroy() {
		// 3. ������ - ������ �޸𸮿��� ���ŵ� �� ���� �����̳ʿ� ���ؼ� �ڵ� ȣ��
		System.out.println("[HelloServlet] destroy() is called.");
	}
}
 