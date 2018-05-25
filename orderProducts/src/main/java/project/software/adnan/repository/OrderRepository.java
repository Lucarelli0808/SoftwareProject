package project.software.adnan.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import project.software.adnan.domain.Order;
import project.software.adnan.domain.Person;
import project.software.adnan.domain.Product;

public interface OrderRepository extends JpaRepository<Order, Integer>{
	
	List<Order> findDistinctOrderByOrderLines_Product(Product product);
	List<Order> findOrderByPerson(Person person);
	List<Order> findOrderByUser(String user);
	List<Order> findOrderByOrderDateBetween(Date minDate, Date maxDate);
	

}
