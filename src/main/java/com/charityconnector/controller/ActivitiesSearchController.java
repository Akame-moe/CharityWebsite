package com.charityconnector.controller;

import com.charityconnector.service.CharityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.security.Principal;
import java.util.Map;


@Controller
public class ActivitiesSearchController {

    @Resource
    private CharityService charityService;

    @Autowired
    public ActivitiesSearchController(CharityService charityService) {
        this.charityService = charityService;
    }

    @RequestMapping("/results/activities")
    public String getResultsPage(Map<String, Object> model,
                                 @RequestParam(defaultValue = "0") int pageNumber,
                                 @RequestParam(defaultValue = "10") int pageSize,
                                 Principal principal) {

        return "activitiesResultsPage";
    }
}
