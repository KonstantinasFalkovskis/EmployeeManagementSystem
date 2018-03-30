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
		Departament departament = new Departament(8L, "Accounting");
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
		Iterable<Departament> departament = departamentService.findListDepartment();
		if (departament != null) {
			departamentService.deleteDepo(7L);
		}
	}

	@Test
    public void addNewEmployee() {
        Employee employee = new Employee("John McLain", "3500", "john@yahoo.com", null);
        employeeService.saveEmployee(employee);
        assertNotNull(employee);
        assertEquals(employee.getName(), "John McLain");
    }
}