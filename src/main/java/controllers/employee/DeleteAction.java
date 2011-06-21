package controllers.employee;

import services.EmployeeService;

public class DeleteAction extends EmployeeController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8703795669839830799L;

	private EmployeeService employeeService;
	private Long id;

	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}
	
	@Override
	public String execute() {
		employeeService.removeById(id);
		return NONE;
	}
	
	
	
	
}
