package net.java_school.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@GetMapping("/")
	public String index() {
		return "index";
	}

	@RequestMapping("/error-403")
	public String error403() {
		return "error-403";
	}

	@GetMapping("/check-thread")
	public String checkThread() {
		boolean isVirtual = Thread.currentThread().isVirtual();
		String threadName = Thread.currentThread().toString();
    
		System.out.printf("Is Virtual Thread? %b | Thread Info: %s", isVirtual, threadName);
		return "index";
	}
}
