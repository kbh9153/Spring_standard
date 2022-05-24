package com.fastcampus.ch2;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.StringJoiner;

public class MethodInfo {
	public static void main(String[] args) throws Exception{
		// 1. YoilTeller Ŭ������ ��ü ����
		Class clazz = Class.forName("com.fastcampus.ch2.YoilTellerMVC");
		Object obj = clazz.newInstance();
		
		// 2. ��� �޼ҵ� ������ �����ͼ� �迭�� ����(�޼ҵ尡 �������� ��츦 ���� �迭�� ����)
		Method[] methodArr = clazz.getDeclaredMethods();
		
		// 3. �ݺ����� ����Ͽ� �޼ҵ� �������� ���
		for(Method m : methodArr) {
			String name = m.getName();	// �޼ҵ� �̸�
			Parameter[] paramArr = m.getParameters();	// �޼ҵ� �Ű����� ���
//			Class[] paramTypeArr = m.getParameterTypes();
			Class returnType = m.getReturnType();	// �޼ҵ� ��ȯ Ÿ��
			
			StringJoiner paramList = new StringJoiner(", ", "(", ")");	// , : ������ / ( : ���λ�(prefix) / ) : ���̻�(suffix)
			// StringJoiner Class : ������ ���� ���ڸ� ���� ��°��� ���̿� �������ִ� ���. ��°� ��ü �յڿ� ���λ�� ���̻絵 �߰��� �� ���� 
			
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
 * [������]
 * 
 * void main(javax.servlet.http.HttpServletRequest arg0, javax.servlet.http.HttpServletResponse arg1)
 * 
 * - YoilTeller�� main �޼ҵ��� �Ű������� �̸��� request, response������ ��°�������� args0, args1��
 *  => YoilTeller Class�� �������� ��, �����Ϸ����״� �Ű������� Ÿ���� ���������� �̸��� �������� �ʱ� ������ args0, args1���� ���
 *  => ������ �Ű������� �̸��� �����ϱ� ���ؼ��� �����Ϸ� �ɼǿ� -parameters(�Ű����� �̸� ���� �ɼ�)�� �����������
 *  => ������� : Window -> Preferences -> Java -> Compiler -> Store Information about method parameters(usable via reflection) üũ(JRE 1.8(�ڹ� 8)���� ��� ����)
 *  => JRE 11 ����
 *   a. pom.xml 
     <properties>
		<java-version>11</java-version> -> 11�� ���� (�� ���ϵ��� ����� ���������� ����)
	 </properties>
	 
	 <plugin>
        <configuration>
            <source>${java-version}</source> -> ${java-version}���� ����. properties�� java-version ���� ����
            <target>${java-version}</target> -> ${java-version}���� ����. properties�� java-version ���� ����
        </configuration>
     </plugin>
     
     b. pom.xml ���� �� ������Ʈ ��Ŭ�� -> Maven(������Ʈ�� ����) -> Update Project Ŭ�� �� OK
     c. ����, ������Ʈ �� ��� : void main(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) -> �Ű����� �̸� ���� ���
 * 
 * �Ű����� ������ ���
 * 	1. Reflection API : -parameters �ɼ��� �߰��Ͽ��� ��(�� �޸��� ���)
 *  2. ClassFile ������ �̿�
 */
