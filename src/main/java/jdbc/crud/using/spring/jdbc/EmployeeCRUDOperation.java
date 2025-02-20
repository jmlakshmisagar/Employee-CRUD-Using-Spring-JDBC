package jdbc.crud.using.spring.jdbc;

import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeCRUDOperation implements EmployeeDAO {

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void createEmployee(Employee employee) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.update("INSERT INTO employee (name, salary) VALUES (?,?)", employee.getEmployeeName(),
				employee.getEmployeeSalary());
	}

	@Override
	public void updateEmployee(Employee employee) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.update("UPDATE employee SET salary = ? WHERE employee_id = ?", employee.getEmployeeSalary(),
				employee.getEmployeeId());
	}

	@Override
	public void removeEmployee(Employee employee) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.update("DELETE FROM employee WHERE employee_id = ?", employee.getEmployeeId());
	}

	@Override
	public List<Employee> findAllEmployees() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String sql = "SELECT * FROM employee";
		return jdbcTemplate.query(sql, (rs, rowNum) -> {
			Employee employee = new Employee();
			employee.setEmployeeId(rs.getInt("employee_id"));
			employee.setEmployeeName(rs.getString("name"));
			employee.setEmployeeSalary(rs.getString("salary"));
			return employee;
		});
	}

	public Employee findEmployeeById(int employeeId) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String sql = "SELECT * FROM employee WHERE employee_id = ?";
		return jdbcTemplate.queryForObject(sql, new Object[] { employeeId }, new RowMapper<Employee>() {
			@Override
			public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
				Employee employee = new Employee();
				employee.setEmployeeId(rs.getInt("employee_id"));
				employee.setEmployeeName(rs.getString("name"));
				employee.setEmployeeSalary(rs.getString("salary"));
				return employee;
			}
		});
	}
}
