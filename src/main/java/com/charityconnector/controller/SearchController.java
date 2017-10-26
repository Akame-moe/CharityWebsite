package com.charityconnector.controller;

import com.charityconnector.entity.Charity;
import com.charityconnector.service.ArticleService;
import com.charityconnector.service.CharityService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.Map;


@Controller
public class SearchController {

    @Resource
    private CharityService charityService;

    @Resource
    private ArticleService articleService;

    @RequestMapping("/results")
    public String getResultsPagee(Map<String, Object> model, @RequestParam(defaultValue = "0") int pageNumber, @RequestParam(defaultValue = "5") int pageSize) {
        PageRequest pageRequest = new PageRequest(pageNumber, pageSize, Sort.Direction.DESC, "name");
        Charity[] charities = charityService.getPaged(pageRequest);
        model.put("charities", charities);
        return "resultsPage";
    }
}
