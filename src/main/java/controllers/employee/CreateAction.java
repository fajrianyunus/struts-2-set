package controllers.employee;

import entities.Employee;
import services.EmployeeService;

public class CreateAction extends EmployeeController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3291291430710481449L;

	private EmployeeService employeeService;
	private String fullName;
	private String nric;
	private String remarks;
	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getFullName() {
		return fullName;
	}
	public void setNric(String nric) {
		this.nric = nric;
	}
	public String getNric() {
		return nric;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getRemarks() {
		return remarks;
	}
	
	@Override
	public String execute() {
		Employee empl = new Employee();
		empl.setFullName(fullName);
		empl.setNric(nric);
		empl.setRemarks(remarks);
		employeeService.save(empl);
		return NONE;
	}
}
