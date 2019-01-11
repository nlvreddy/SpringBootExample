package com.example.demo.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
 
import com.example.demo.dao.EmployeeDAO;
import com.example.demo.model.Employee;
import com.example.demo.model.Employees;

@RestController
@RequestMapping(path = "/employees")
public class EmployeeController {

	 @Autowired
	    private EmployeeDAO employeeDao;
	     
	    @GetMapping(path="/", produces = "application/json")
	    public Employees getEmployees()
	    {
	        return employeeDao.getAllEmployees();
	    }
	     
	    @PostMapping(path= "/add", consumes = "application/json", produces = "application/json")
	    public ResponseEntity<Object> addEmployee(@RequestBody Employee employee)
	    {
	        Integer id = employeeDao.getAllEmployees().getEmployeeList().size() + 1;
	        
	        System.out.println("id in addEmployee method from Employee controller" +id);
	        employee.setId(id);
	         
	        employeeDao.addEmployee(employee);
	         
	        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
	                                    .path("/{id}")
	                                    .buildAndExpand(employee.getId())
	                                    .toUri();
	        
	        System.out.println("after constructing Loaction.." +location);
	         
	        return ResponseEntity.created(location).build();
	    }
	    
	    @GetMapping(path="/hello", produces = "application/json")
	    public String hello() {
	      return "hello.....!";
	    }
	}