package com.fastcampus.ch2;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.StringJoiner;

public class MethodInfo {
	public static void main(String[] args) throws Exception{
		// 1. YoilTeller 클래스의 객체 생성
		Class clazz = Class.forName("com.fastcampus.ch2.YoilTellerMVC");
		Object obj = clazz.newInstance();
		
		// 2. 모든 메소드 정보를 가져와서 배열로 저장(메소드가 여러개일 경우를 위해 배열로 저장)
		Method[] methodArr = clazz.getDeclaredMethods();
		
		// 3. 반복문을 사용하여 메소드 정보들을 출력
		for(Method m : methodArr) {
			String name = m.getName();	// 메소드 이름
			Parameter[] paramArr = m.getParameters();	// 메소드 매개변수 목록
//			Class[] paramTypeArr = m.getParameterTypes();
			Class returnType = m.getReturnType();	// 메소드 반환 타입
			
			StringJoiner paramList = new StringJoiner(", ", "(", ")");	// , : 구분자 / ( : 접두사(prefix) / ) : 접미사(suffix)
			// StringJoiner Class : 생성자 값인 문자를 값들 출력값들 사이에 삽입해주는 기능. 출력값 전체 앞뒤에 접두사와 접미사도 추가할 수 있음 
			
			for(Parameter param : paramArr) {
				String paramName = param.getName();
				Class  paramType = param.getType();
				
				paramList.add(paramType.getName() + " " + paramName);
			}
			
			System.out.printf("%s %s%s%n", returnType.getName(), name, paramList);
		}
	} // main
}

/*
 * [실행결과]
 * 
 * void main(javax.servlet.http.HttpServletRequest arg0, javax.servlet.http.HttpServletResponse arg1)
 * 
 * - YoilTeller의 main 메소드의 매개변수의 이름이 request, response이지만 출력결과에서는 args0, args1임
 *  => YoilTeller Class를 컴파일할 때, 컴파일러한테는 매개변수의 타입은 저장하지만 이름은 저장하지 않기 때문에 args0, args1으로 출력
 *  => 때문에 매개변수의 이름을 저장하기 위해서는 컴파일러 옵션에 -parameters(매개변수 이름 저장 옵션)을 설정해줘야함
 *  => 설정방법 : Window -> Preferences -> Java -> Compiler -> Store Information about method parameters(usable via reflection) 체크(JRE 1.8(자바 8)부터 사용 가능)
 *  => JRE 11 변경
 *   a. pom.xml 
     <properties>
		<java-version>11</java-version> -> 11로 변경 (이 파일들을 사용할 참조값들을 설정)
	 </properties>
	 
	 <plugin>
        <configuration>
            <source>${java-version}</source> -> ${java-version}으로 변경. properties의 java-version 값을 참조
            <target>${java-version}</target> -> ${java-version}으로 변경. properties의 java-version 값을 참조
        </configuration>
     </plugin>
     
     b. pom.xml 수정 후 프로젝트 우클릭 -> Maven(프로젝트를 관리) -> Update Project 클릭 후 OK
     c. 수정, 업데이트 후 결과 : void main(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) -> 매개변수 이름 정상 출력
 * 
 * 매개변수 얻어오는 방법
 * 	1. Reflection API : -parameters 옵션을 추가하여야 함(위 메모한 방법)
 *  2. ClassFile 정보를 이용
 */
