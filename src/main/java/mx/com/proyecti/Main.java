package mx.com.proyecti;

import mx.com.proyecti.entity.Employee;

public class Main {

	public static void main(String[] args) {
		EmployeeManager manager = new EmployeeManager();
		manager.setup();
		Employee emp = manager.read(1);
		System.out.println(emp);
		manager.exit();
	}

}
