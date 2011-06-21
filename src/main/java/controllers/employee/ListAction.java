package controllers.employee;

import java.util.List;

import entities.Employee;

import services.EmployeeService;

public class ListAction extends EmployeeController {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4603707956919125324L;
	private EmployeeService employeeService;
	private List<Employee> employees;
	private Long employeeCount;
	
	@Override
	public void prepare() {
		try {
			super.prepare();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		employees = employeeService.findAll();
		employeeCount = employeeService.countAll();
	}

	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public Long getEmployeeCount() {
		return employeeCount;
	}
}
