package com.charityconnector.controller;

import com.charityconnector.entity.Activity;
import com.charityconnector.entity.Country;
import com.charityconnector.service.ActivityService;
import com.charityconnector.service.CharityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.security.Principal;
import java.util.Date;
import java.util.Map;


@Controller
public class ActivitiesSearchController {

    @Resource
    private CharityService charityService;

    @Resource
    private ActivityService activityService;

    @Autowired
    public ActivitiesSearchController(CharityService charityService) {
        this.activityService = activityService;
        this.charityService = charityService;
    }

    @RequestMapping("/results/activities")
    public String getResultsPage(Map<String, Object> model,
                                 @RequestParam(required = false) Date holdDateFrom,
                                 @RequestParam(required = false) Date holdDateTo,
                                 @RequestParam(required = false) Country country,
                                 @RequestParam(defaultValue = "0") int pageNumber,
                                 @RequestParam(defaultValue = "10") int pageSize,
                                 Principal principal) {
        Page<Activity> page;
        Pageable pageRequest;

        if (principal != null)
            model.put("userId", principal.getName());

        pageRequest = new PageRequest(pageNumber, pageSize, Sort.Direction.DESC, "holdDate");

        page = activityService.findByHoldDateAndCountry(holdDateFrom, holdDateTo, country, pageRequest);

        if (page.getContent().size() != 0) {
            model.put("activities", page.getContent());
            model.put("numberOfPages", page.getTotalPages());
            model.put("numberOfResults", page.getTotalElements());
            model.put("pageNumber", page.getNumber());
            model.put("pageSize", pageSize);
            model.put("thisPageSize", page.getContent().size());
        }

        return "activitiesResultsPage";
    }
}
