package com.charityconnector.controller;

import com.charityconnector.entity.Charity;
import com.charityconnector.service.CharityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


@Controller
public class SearchController {
    @Resource
    private CharityService charityService;

    @Autowired
    public SearchController(CharityService charityService) {
        this.charityService = charityService;
    }

    @RequestMapping("/results")
    public String getResultsPage(Map<String, Object> model, @RequestParam(defaultValue = "") String name, @RequestParam(defaultValue = "0") int pageNumber, @RequestParam(defaultValue = "10") int pageSize) {
        if (name == null || name.trim().equals(""))
            return "resultsPage";
        if (pageSize != 10 && pageSize != 25 && pageSize != 50)
            pageSize = 10;
        PageRequest pageRequest = new PageRequest(pageNumber, pageSize, Sort.Direction.DESC, "name");
        Page<Charity> page = charityService.findByNameLike("%" + name + "%", pageRequest);
        List<Charity> charities = page.getContent();
        if (charities.size() != 0) {
            model.put("charities", charities);
            model.put("numberOfPages", page.getTotalPages());
            model.put("numberOfResults", page.getTotalElements());
            model.put("pageNumber", page.getNumber());
            model.put("searchedName", name);
            model.put("pageSize", pageSize);
            model.put("thisPageSize", charities.size());
        }

        return "resultsPage";
    }
}
