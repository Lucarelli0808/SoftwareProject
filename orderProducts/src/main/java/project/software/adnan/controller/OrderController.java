package project.software.adnan.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import project.software.adnan.domain.Order;
import project.software.adnan.domain.Orderline;
import project.software.adnan.domain.Person;
import project.software.adnan.domain.Product;
import project.software.adnan.service.OrderService;
import project.software.adnan.service.PersonService;
import project.software.adnan.service.ProductService;

@Controller
@RequestMapping("/order")
public class OrderController {
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private PersonService personService;
	
	@GetMapping("/list")
	public String list(Model model) {
		List<Order> orders =orderService.findAll();
		model.addAttribute("orders", orders);
		return "orderList";
	}
	
	@GetMapping("/userList")
	public String userList(Model model) {
		String user = personService.getCurUser();
		List<Order> orders = orderService.findByUser(user);
		model.addAttribute("orders", orders);
		return "myOrderList";
	}
	
	@GetMapping("/add/{prodId}")
	public String add(@PathVariable int prodId,Model model) {
		Product product = productService.getProduct(prodId);
		Person person = personService.getCurPerson();
		String user = personService.getCurUser();
		Order order = new Order();
		order.setPerson(person);
		order.setUser(user);
		order.setOrderDate(new Date());
		Orderline orderline = new Orderline();
		orderline.setOrder(order);
		orderline.setProduct(product);
		orderline.setQuantity(1);
		order.addOrderLine(orderline);
		orderService.save(order);
		return "redirect:/order/userList";
	}
	
	
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable int id) {
		orderService.delete(id);;
		return "redirect:/order/list";
	}

	
}
