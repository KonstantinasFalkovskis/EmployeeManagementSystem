package com.example.demo;

import com.example.demo.entities.Departament;
import com.example.demo.entities.Employee;
import com.example.demo.services.DepartamentService;
import com.example.demo.services.EmployeeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

	@Autowired
	private DepartamentService departamentService;

	@Autowired
    private EmployeeService employeeService;

	@Test
	public void addDepartment() {
		Departament departament = new Departament(10L, "Accounting");
		departamentService.saveDepo(departament);
        assertNotNull(departament);
		assertEquals(departament.getDepartament(), "Accounting");
	}

	@Test
	public void editDepo() {
		Departament depo = departamentService.findOne(5L);
		assertNotNull(depo);
		Departament updatedDepo = new Departament(5L, "Management");
		assertNotNull(updatedDepo);
		departamentService.saveDepo(updatedDepo);
		assertEquals(updatedDepo.getDepartament(), "Management");
	}

	@Test
	public void viewDepo() {
		Departament depo = departamentService.findOne(5L);
		assertNotNull(depo);
		assertEquals(depo.getDepartament(), "Management");
	}

	@Test
	public void deleteDepo() {
		Departament departament = departamentService.findOne(9L);
		if (departament.getId() != null) {
			departamentService.deleteDepo(9L);
			employeeService.deleteEmployee(departament.getId());
		}
	}

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

    @Test
    public void showEmployee() {
        Employee employee = employeeService.getEmployeeById(3L);
        assertNotNull(employee);
        assertEquals(employee.getName(), "Alex Fox");
    }

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