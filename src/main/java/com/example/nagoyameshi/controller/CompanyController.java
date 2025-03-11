package com.example.nagoyameshi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.nagoyameshi.entity.Company;
import com.example.nagoyameshi.service.CompanyService;

@Controller
@RequestMapping("/company")
public class CompanyController {
	private final CompanyService companyService;
	
	public CompanyController (CompanyService companyService) {
		this.companyService = companyService;
	}
	
	@GetMapping
	public String index (Model model) {
		Company company = companyService.findFirstCompanyByOrderByIdDesc();
		
		model.addAttribute("company", company);
		
		 return "company/index";
	}
	
	@ControllerAdvice
	public class GlobalExceptionHandler {

	    @ExceptionHandler(Exception.class)
	    public String handleException(Exception e, Model model) {
	        // エラーメッセージをモデルに設定
	        model.addAttribute("errorMessage", e.getMessage());
	        return "error"; // error.html ページを返すように設定
	    }
	}

}
