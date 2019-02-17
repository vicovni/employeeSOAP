package mx.com.proyecti;

import javax.xml.ws.Endpoint;

import mx.com.proyecti.service.EmployeeService;


public class Publish {

	public static void main(String[] args) {
		EmployeeService ws = new EmployeeService();
		Endpoint ep = Endpoint.create(ws);
		String address = "http://localhost:8082/TestWSClient";
		Endpoint.publish(address, ws);
		System.out.println("Server started");
	}

}
