package project.software.adnan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import project.software.adnan.domain.Product;
import project.software.adnan.service.ProductService;

@Controller
@RequestMapping("/product")
public class ProductController {
	@Autowired
	private ProductService productService;
	
	@GetMapping("/list")
	public String list(Model model) {
		List<Product> products =productService.getAllProduct();
		model.addAttribute("products", products);
		return "productList";
	}
	
	@PostMapping("/add")
	public String add(Product product) {
		productService.save(product);
		return "redirect:/product/list";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable int id) {
		productService.delete(id);;
		return "redirect:/product/list";
	}

	
}
