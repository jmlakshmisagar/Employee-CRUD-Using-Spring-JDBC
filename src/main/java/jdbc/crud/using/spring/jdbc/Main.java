package jdbc.crud.using.spring.jdbc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
	public static void main(String[] args) {
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext("connectionConfiguration.xml");
		EmployeeDAO employeeDAO = context.getBean("employeeDAO", EmployeeDAO.class);
		EmployeeMenu employeeMenu = new EmployeeMenu(employeeDAO);
		employeeMenu.displayMenu();
	}
}
