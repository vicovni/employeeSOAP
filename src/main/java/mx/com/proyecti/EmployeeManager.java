package mx.com.proyecti;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import mx.com.proyecti.entity.Employee;

public class EmployeeManager {
	protected SessionFactory sessionFactory;
	
	public void setup() {
		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
				.configure()
				.build();
		
		try {
			sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
		}catch(Exception e){
			System.out.println("error:" + e.getMessage());
			StandardServiceRegistryBuilder.destroy(registry);
		}
	}
	
	public void exit() {
		sessionFactory.close();
	}
	
	public int create(String firstName, String lastName, Date birthdate, double salary) {
		
		Employee employee = new Employee();
		employee.setFirstName(firstName);
		employee.setLastName(lastName);
		employee.setBirthdate(birthdate);
		employee.setSalary(salary);
		
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		int id = (Integer)session.save(employee);
		session.getTransaction().commit();
		session.close();
		return id;
	}
	
	public Employee read(int id) {
		Session session = sessionFactory.openSession();
		Employee employee = session.get(Employee.class,  id);
		System.out.println("FirstName: " + employee.getFirstName());
		System.out.println("LastName: " + employee.getLastName());
		System.out.println("Birthday: " + employee.getBirthdate());
		System.out.println("Salary: " + employee.getSalary());
		session.close();
		return employee;
	}
	
	public List<Employee> readAll(){
		Session session = sessionFactory.openSession();
		List<Employee> list = session.createQuery("from Employee").list();
		return list;
	}
	
	public void update(int id, String firstName, String lastName, Date birthDate, double salary ){
		Employee employee = new Employee();
		employee.setId(id);
		employee.setFirstName(firstName);
		employee.setLastName(lastName);
		employee.setBirthdate(birthDate);
		employee.setSalary(salary);
		
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		session.update(employee);
		
		session.getTransaction().commit();
		session.close();
	}
	
	public void delete(int id) {
		Employee employee = new Employee();
		employee.setId(id);
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		session.delete(employee);
		
		session.getTransaction().commit();
		session.close();
	}
}
