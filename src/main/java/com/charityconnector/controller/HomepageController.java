package com.charityconnector.controller;

import com.charityconnector.entity.Charity;
import com.charityconnector.service.CharityService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Controller
public class HomepageController {
    private static int FEATURED_PAGE_SIZE = 6;

    @Resource
    private CharityService charityService;

    @RequestMapping("/")
    public String getHomePage(Map<String, Object> model) {
        Map<Long, Charity> featuredCharities = new HashMap<>();
        while (featuredCharities.size() < FEATURED_PAGE_SIZE) {
            Charity c = charityService.findRandom();
            featuredCharities.put(c.getId(), c);
        }
        model.put("featuredCharities", featuredCharities.values());
        return "index";
    }
}
