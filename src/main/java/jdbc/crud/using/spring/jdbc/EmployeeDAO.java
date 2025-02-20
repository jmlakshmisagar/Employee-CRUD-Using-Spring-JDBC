package jdbc.crud.using.spring.jdbc;

import java.util.List;

public interface EmployeeDAO {
	void createEmployee(Employee employee);

	void updateEmployee(Employee employee);

	void removeEmployee(Employee employee);

	List<Employee> findAllEmployees();
}
