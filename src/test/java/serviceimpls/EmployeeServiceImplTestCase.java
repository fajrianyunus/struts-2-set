package serviceimpls;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import entities.Employee;

import services.EmployeeService;

@ContextConfiguration(locations={"classpath:/applicationContext_test.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class EmployeeServiceImplTestCase extends AbstractTestNGSpringContextTests {
	private final Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
	private EmployeeService employeeService;
	
	public EmployeeService getEmployeeService() {
		return employeeService;
	}

	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
		System.out.println("hehehe");
	}
	
	@BeforeMethod
	public void beforeEachTest() {
		employeeService.deleteAll();
		Long count = employeeService.countAll();
		Assert.assertEquals(new Long(0), count);
	}
	
	@Test
	public void testTheTruth() {
		log.debug("sanity test");
		Assert.assertTrue(true);
	}
	
	@Test
	public void testEmployeeServiceIsNotNull() {
		log.debug("testing the spring autowiring");
		Assert.assertNotNull(employeeService);
	}
	
	@Test
	public void testCanDeleteAll() {
		log.debug("testing the wholesale data clearing capability");
		
		Employee empl = new Employee();
		empl.setFullName("phua chu kang");
		empl.setNric("s1234567a");
		empl.setRemarks("<h1 style='color : blue;'>comedian</h1>");
		employeeService.save(empl);
		
		Employee empl2 = new Employee();
		empl2.setFullName("gurmit singh");
		empl2.setNric("s1234567b");
		empl2.setRemarks("<h1 style='color : red;'>comedian</h1>");
		employeeService.save(empl2);
		
		Long count = employeeService.countAll();
		Assert.assertTrue(count > 0);
		
		employeeService.deleteAll();
		
		Long countAfterDeletion = employeeService.countAll();
		Assert.assertEquals(new Long(0), countAfterDeletion);
	}
	
	@Test
	public void testCanFindAll() {
		log.debug("testing the finding all capability");
		
		Employee empl[] = new Employee[2];
		
		empl[0] = new Employee();
		empl[0].setFullName("phua chu kang");
		empl[0].setNric("s1234567a");
		empl[0].setRemarks("<h1 style='color : blue;'>comedian</h1>");
		employeeService.save(empl[0]);
		
		empl[1] = new Employee();
		empl[1].setFullName("gurmit singh");
		empl[1].setNric("s1234567b");
		empl[1].setRemarks("<h1 style='color : red;'>comedian</h1>");
		employeeService.save(empl[1]);
		
		List<Employee> retrievedEmployees = employeeService.findAll();
		
		for (Employee e : empl) {
			boolean matchIsFound = false;
			
			for (Employee retEmp : retrievedEmployees) {
				if (retEmp.getFullName().equals(e.getFullName())) {
					if (retEmp.getNric().equals(e.getNric())) {
						if (retEmp.getRemarks().equals(e.getRemarks())) {
							matchIsFound = true;
							break;
						}
					}
				}
			}
			
			Assert.assertTrue(matchIsFound);
		}
	}
	
	@Test
	public void testCanInsert() {
		log.debug("testing the insertion capability");
		
		Employee empl = new Employee();
		empl.setFullName("phua chu kang");
		empl.setNric("s1234567a");
		empl.setRemarks("<h1 style='color : blue;'>comedian</h1>");
		
		Assert.assertNull(empl.getId());
		employeeService.save(empl);
		Assert.assertNotNull(empl.getId());
		Assert.assertNotNull(employeeService.find(empl.getId()));
	}	
	
	@Test
	public void testCanUpdate() {
		log.debug("testing updating capability");
		
		Employee empl = new Employee();
		empl.setFullName("phua chu kang");
		empl.setNric("s1234567a");
		empl.setRemarks("<h1 style='color : blue;'>comedian</h1>");
		employeeService.save(empl);
		Assert.assertNotNull(empl);
		Long id = empl.getId();
		Assert.assertNotNull(id);
		
		empl.setFullName("gurmit singh");
		empl.setNric("s7654321b");
		empl.setRemarks("<h1 style='color : red;'>actor</h1>");
		employeeService.save(empl);
		
		Employee retrievedEmployee = employeeService.find(id);
		Assert.assertNotNull(retrievedEmployee);
		
		boolean doesMatch = false;
		
		if (empl.getFullName().equals(retrievedEmployee.getFullName())) {
			if (empl.getNric().equals(retrievedEmployee.getNric())) {
				if (empl.getRemarks().equals(retrievedEmployee.getRemarks())) {
					doesMatch = true;
				}
			}
		}
		
		Assert.assertTrue(doesMatch);
	}
	
	@Test
	public void testCanRemoveById() {
		log.debug("testing removal by id capability");
		
		Employee empl = new Employee();
		empl.setFullName("phua chu kang");
		empl.setNric("s1234567a");
		empl.setRemarks("<h1 style='color : blue;'>comedian</h1>");
		employeeService.save(empl);
		Assert.assertNotNull(empl);
		Long id = empl.getId();
		Assert.assertNotNull(id);
		
		employeeService.removeById(id);
		
		Assert.assertNull(employeeService.find(id));
	}
	
	@Test
	public void testCanFind() {
		log.debug("testing find by id capability");
		
		log.debug("testing removal by id capability");
		
		Employee empl = new Employee();
		empl.setFullName("phua chu kang");
		empl.setNric("s1234567a");
		empl.setRemarks("<h1 style='color : blue;'>comedian</h1>");
		employeeService.save(empl);
		Assert.assertNotNull(empl);
		Long id = empl.getId();
		Assert.assertNotNull(id);
		
		Employee retrievedEmployee = employeeService.find(id);
		Assert.assertNotNull(retrievedEmployee);
		
		boolean doesMatch = false;
		
		if (empl.getFullName().equals(retrievedEmployee.getFullName())) {
			if (empl.getNric().equals(retrievedEmployee.getNric())) {
				if (empl.getRemarks().equals(retrievedEmployee.getRemarks())) {
					doesMatch = true;
				}
			}
		}
		
		Assert.assertTrue(doesMatch);
	}
	
	@Test
	public void testCanCountAll() {
		log.debug("testing counting all capability");
		
		Employee empl[] = new Employee[2];
		Long count[] = new Long[2];
		
		empl[0] = new Employee();
		empl[0].setFullName("phua chu kang");
		empl[0].setNric("s1234567a");
		empl[0].setRemarks("<h1 style='color : blue;'>comedian</h1>");
		employeeService.save(empl[0]);
		count[0] = employeeService.countAll();
		
		empl[1] = new Employee();
		empl[1].setFullName("gurmit singh");
		empl[1].setNric("s1234567b");
		empl[1].setRemarks("<h1 style='color : red;'>comedian</h1>");
		employeeService.save(empl[1]);
		count[1] = employeeService.countAll();
		
		Assert.assertEquals(1L, count[1] - count[0]);
	}
}
