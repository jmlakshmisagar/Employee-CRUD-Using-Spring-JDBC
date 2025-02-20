package jdbc.crud.using.spring.jdbc;

import java.util.List;
import java.util.Scanner;

public class EmployeeMenu {
	private final EmployeeDAO employeeDAO;

	public EmployeeMenu(EmployeeDAO employeeDAO) {
		this.employeeDAO = employeeDAO;
	}

	public void displayMenu() {
		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.println("Menu:");
			System.out.println("1. Add Employee");
			System.out.println("2. Update Employee");
			System.out.println("3. Delete Employee");
			System.out.println("4. View All Employees");
			System.out.println("5. Find Employee by ID");
			System.out.println("6. Exit");
			System.out.print("Enter your choice: ");
			int choice = scanner.nextInt();
			scanner.nextLine(); // Consume newline

			switch (choice) {
			case 1:
				addEmployee(scanner);
				break;
			case 2:
				updateEmployee(scanner);
				break;
			case 3:
				deleteEmployee(scanner);
				break;
			case 4:
				viewAllEmployees();
				break;
			case 5:
				findEmployeeById(scanner);
				break;
			case 6:
				System.out.println("Exiting...");
				scanner.close();
				System.exit(0);
				break;
			default:
				System.out.println("Invalid choice. Please try again.");
			}
		}
	}

	private void addEmployee(Scanner scanner) {
		System.out.print("Enter employee name: ");
		String name = scanner.nextLine();
		System.out.print("Enter employee salary: ");
		String salary = scanner.nextLine();
		Employee newEmployee = new Employee(0, name, salary);
		employeeDAO.createEmployee(newEmployee);
		System.out.println("Employee added successfully!");
	}

	private void updateEmployee(Scanner scanner) {
		System.out.print("Enter employee ID to update: ");
		int updateId = scanner.nextInt();
		scanner.nextLine(); // Consume newline
		System.out.print("Enter new salary: ");
		String newSalary = scanner.nextLine();
		Employee updatedEmployee = new Employee(updateId, null, newSalary);
		employeeDAO.updateEmployee(updatedEmployee);
		System.out.println("Employee updated successfully!");
	}

	private void deleteEmployee(Scanner scanner) {
		System.out.print("Enter employee ID to delete: ");
		int deleteId = scanner.nextInt();
		Employee deleteEmployee = new Employee(deleteId, null, null);
		employeeDAO.removeEmployee(deleteEmployee);
		System.out.println("Employee deleted successfully!");
	}

	private void viewAllEmployees() {
		List<Employee> employees = employeeDAO.findAllEmployees();
		System.out.println("Employee List:");
		for (Employee employee : employees) {
			System.out.println(employee);
		}
	}

	private void findEmployeeById(Scanner scanner) {
		System.out.print("Enter employee ID to find: ");
		int employeeId = scanner.nextInt();
		Employee employee = employeeDAO.findEmployeeById(employeeId);
		if (employee != null) {
			System.out.println("Employee Details:");
			System.out.println(employee);
		} else {
			System.out.println("Employee with ID " + employeeId + " not found.");
		}
	}
}
