package mx.com.proyecti.service;

import java.util.Date;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import mx.com.proyecti.EmployeeManager;
import mx.com.proyecti.entity.Employee;

@WebService
public class EmployeeService {

	
	EmployeeManager manager = new EmployeeManager();
	public EmployeeService(){
		
	}
	
	@WebMethod
	public Employee getEmployee(@WebParam(name="id")int id) {
		manager.setup();
		Employee emp = manager.read(id);
		manager.exit();
		return emp;
		
	}
	
	@WebMethod
	public List<Employee> getAllEmployees(){
		manager.setup();
		return manager.readAll();
	}
	
	@WebMethod
	public Employee setEmployee(@WebParam(name="firstname")String firstName, 
			@WebParam(name="lastname")String lastName, 
			@WebParam(name="date")Date birthdate, 
			@WebParam(name="salary")double salary) {
		manager.setup();
		int id = manager.create(firstName, lastName, birthdate, salary);
		Employee emp = getEmployee(id);
		return emp;
	}
	
	@WebMethod
	public boolean deleteEmployee(@WebParam(name="id") int id) {
		manager.setup();
		manager.delete(id);
		return true;
	}
	
	@WebMethod
	public Employee updateEmployee(@WebParam(name="id") int id,
			@WebParam(name="firstname")String firstName, 
			@WebParam(name="lastname")String lastName, 
			@WebParam(name="date")Date birthdate, 
			@WebParam(name="salary")double salary) {
		manager.setup();
		manager.update(id, firstName, lastName, birthdate, salary);
		Employee emp = getEmployee(id);
		return emp;		
	}
}
