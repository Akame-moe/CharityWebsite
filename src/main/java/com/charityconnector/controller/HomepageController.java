package com.charityconnector.controller;

import com.charityconnector.service.CharityService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.Map;

@Controller
public class HomepageController {

	@Resource
	private CharityService charityService;

	@RequestMapping("/")
	public String getHomePage(Map<String, Object> model) {
		return "index";
	}
}
