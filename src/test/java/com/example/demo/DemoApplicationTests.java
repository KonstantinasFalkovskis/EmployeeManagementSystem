/**
 * @Author - Falco Constantine
 * @date - 2018.03.30
 * @version - v.1.0
 */
package com.example.demo;

import com.example.demo.entities.Departament;
import com.example.demo.entities.Employee;
import com.example.demo.services.DepartamentService;
import com.example.demo.services.EmployeeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Tests for departments + employees
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    //calling depo services
	@Autowired
	private DepartamentService departamentService;

	//calling empl services
	@Autowired
    private EmployeeService employeeService;

    /**
     * Test for new department adding
     */
	@Test
	public void addDepartment() {
		Departament departament = new Departament(10L, "Accounting");
		departamentService.saveDepo(departament);
        assertNotNull(departament);
		assertEquals(departament.getDepartament(), "Accounting");
	}

    /**
     * Test for exist department editing
     */
	@Test
	public void editDepo() {
		Departament depo = departamentService.findOne(5L);
		assertNotNull(depo);
		Departament updatedDepo = new Departament(5L, "Management");
		assertNotNull(updatedDepo);
		departamentService.saveDepo(updatedDepo);
		assertEquals(updatedDepo.getDepartament(), "Management");
	}

    /**
     * Test for existing department viewing
     */
	@Test
	public void viewDepo() {
		Departament depo = departamentService.findOne(5L);
		assertNotNull(depo);
		assertEquals(depo.getDepartament(), "Management");
	}

    /**
     * Test for exist department and if exist all employees inside removing
     */
	@Test
	public void deleteDepo() {
		Departament departament = departamentService.findOne(12L);
		if (departament.getId() != null) {
			departamentService.deleteDepo(12L);
			employeeService.deleteEmployee(departament.getId());
            assertNull(departament);
            assertEquals(departament.getDepartament(), null);
		}
	}

    /**
     * Test for new employee adding
     */
	@Test
    public void addNewEmployee() {
        Employee employee = new Employee("John McLain", "3500", "john@yahoo.com", null);
        Employee employee2 = new Employee("Alex Fox", "4500", "alex@yahoo.com", null);
        employeeService.saveEmployee(employee);
        employeeService.saveEmployee(employee2);
        assertNotNull(employee);
        assertEquals(employee.getName(), "John McLain");
        assertNotNull(employee2);
        assertEquals(employee2.getName(), "Alex Fox");
    }

    /**
     * Test for exist employee view
     */
    @Test
    public void showEmployee() {
        Employee employee = employeeService.getEmployeeById(3L);
        assertNotNull(employee);
        assertEquals(employee.getName(), "Alex Fox");
    }

    /**
     * Test for exist employee updating
     */
    @Test
    public void editEmploye() {
        Employee employee = employeeService.getEmployeeById(3L);
        assertNotNull(employee);
        assertEquals(employee.getName(), "Alex Fox");
        employee = new Employee(3L, "Test Test", "5000", "test@gmail.com");
	    employeeService.saveEmployee(employee);
        assertNotNull(employee);
        assertEquals(employee.getName(), "Test Test");
    }

    /**
     * Test for exist employee removing
     */
    @Test
    public void delEmployeeByName() {
	    Employee employee = employeeService.getEmployeeById(3L);
        assertNotNull(employee);
        assertEquals(employee.getName(), "Test Test");
        employeeService.deleteEmployee(3L);
        assertNull(employee);
        assertEquals(employee.getName(), "");
    }

}