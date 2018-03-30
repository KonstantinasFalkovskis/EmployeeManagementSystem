package com.example.demo;

import com.example.demo.entities.Departament;
import com.example.demo.services.DepartamentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

	@Autowired
	private DepartamentService departamentService;

	@Test
	public void addDepartment() {
		Departament departament = new Departament(1L, "Accounting");
		assertNotNull(departament);
		departamentService.saveDepo(departament);
		assertEquals(departament.getDepartament(), "Accounting");
	}

	@Test
	public void editDepo() {
		Departament depo = departamentService.findOne(1L);
		assertNotNull(depo);
		Departament updatedDepo = new Departament(1L, "Management");
	}

}
