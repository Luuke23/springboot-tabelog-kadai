package com.example.nagoyameshi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminHomeController {
	@GetMapping("/admin/home")
	public String index(Model model) {
		return "admin/index";
	}
}
