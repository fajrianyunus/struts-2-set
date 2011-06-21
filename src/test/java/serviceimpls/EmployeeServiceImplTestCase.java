package serviceimpls;

import static org.junit.Assert.*;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import entities.Employee;

import services.EmployeeService;

@ContextConfiguration(locations={"/applicationContext_test.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class EmployeeServiceImplTestCase {
	private final Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
	private EmployeeService employeeService;
	
	public EmployeeService getEmployeeService() {
		return employeeService;
	}

	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	@Before
	public void beforeEachTest() {
		employeeService.deleteAll();
	}
	
	@Test
	public void testTheTruth() {
		log.debug("sanity test");
		assertTrue("is always true", true);
	}
	
	@Test
	public void testEmployeeServiceIsNotNull() {
		log.debug("testing the spring autowiring");
		assertNotNull(employeeService);
	}
	
	@Test
	public void testBeforeEachTest() {
		log.debug("testing the method which is executed before each test is working");
		Long count = employeeService.countAll();
		assertEquals(new Long(0), count);
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
		assertTrue("there is employee", count > 0);
		
		employeeService.deleteAll();
		
		Long countAfterDeletion = employeeService.countAll();
		assertEquals(new Long(0), countAfterDeletion);
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
			
			assertTrue("found in db", matchIsFound);
		}
	}
	
	@Test
	public void testCanInsert() {
		log.debug("testing the insertion capability");
		
		Employee empl = new Employee();
		empl.setFullName("phua chu kang");
		empl.setNric("s1234567a");
		empl.setRemarks("<h1 style='color : blue;'>comedian</h1>");
		
		assertNull(empl.getId());
		employeeService.save(empl);
		assertNotNull(empl.getId());
		assertNotNull(employeeService.find(empl.getId()));
	}	
	
	@Test
	public void testCanUpdate() {
		log.debug("testing updating capability");
		
		Employee empl = new Employee();
		empl.setFullName("phua chu kang");
		empl.setNric("s1234567a");
		empl.setRemarks("<h1 style='color : blue;'>comedian</h1>");
		employeeService.save(empl);
		assertNotNull(empl);
		Long id = empl.getId();
		assertNotNull(id);
		
		empl.setFullName("gurmit singh");
		empl.setNric("s7654321b");
		empl.setRemarks("<h1 style='color : red;'>actor</h1>");
		employeeService.save(empl);
		
		Employee retrievedEmployee = employeeService.find(id);
		assertNotNull(retrievedEmployee);
		
		boolean doesMatch = false;
		
		if (empl.getFullName().equals(retrievedEmployee.getFullName())) {
			if (empl.getNric().equals(retrievedEmployee.getNric())) {
				if (empl.getRemarks().equals(retrievedEmployee.getRemarks())) {
					doesMatch = true;
				}
			}
		}
		
		assertTrue("updated record is correct", doesMatch);
	}
	
	@Test
	public void testCanRemoveById() {
		log.debug("testing removal by id capability");
		
		Employee empl = new Employee();
		empl.setFullName("phua chu kang");
		empl.setNric("s1234567a");
		empl.setRemarks("<h1 style='color : blue;'>comedian</h1>");
		employeeService.save(empl);
		assertNotNull(empl);
		Long id = empl.getId();
		assertNotNull(id);
		
		employeeService.removeById(id);
		
		assertNull("must be already deleted", employeeService.find(id));
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
		assertNotNull(empl);
		Long id = empl.getId();
		assertNotNull(id);
		
		Employee retrievedEmployee = employeeService.find(id);
		assertNotNull(retrievedEmployee);
		
		boolean doesMatch = false;
		
		if (empl.getFullName().equals(retrievedEmployee.getFullName())) {
			if (empl.getNric().equals(retrievedEmployee.getNric())) {
				if (empl.getRemarks().equals(retrievedEmployee.getRemarks())) {
					doesMatch = true;
				}
			}
		}
		
		assertTrue("the record is found", doesMatch);
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
		
		assertEquals(1L, count[1] - count[0]);
	}
}
