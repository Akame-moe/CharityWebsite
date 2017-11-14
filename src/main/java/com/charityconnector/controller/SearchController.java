package com.charityconnector.controller;

import com.charityconnector.entity.Charity;
import com.charityconnector.service.CharityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.*;


@Controller
public class SearchController {

    final static String NAME = "name";
    final static String CAUSE = "cause";
    final static String LOCATION = "location";

    @Resource
    private CharityService charityService;

    @Autowired
    public SearchController(CharityService charityService) {
        this.charityService = charityService;
    }

    @RequestMapping("/results")
    public String getResultsPage(Map<String, Object> model,
                                 @RequestParam(name = "searchValue", defaultValue = "") String searchValue,
                                 @RequestParam(name = "searchKey", defaultValue = "name") String searchKey,
                                 @RequestParam(defaultValue = "0") int pageNumber,
                                 @RequestParam(defaultValue = "10") int pageSize) {
        if (searchValue == null || searchValue.trim().equals(""))
            return "resultsPage";
        if (pageSize != 10 && pageSize != 25 && pageSize != 50)
            pageSize = 10;
        PageRequest pageRequest = new PageRequest(pageNumber, pageSize, Sort.Direction.DESC, searchKey);
        Page<Charity> page = null;

        if (searchKey.equals(CAUSE)) {
            Set<Charity> res = charityService.getCharitiesByCause(searchValue);
            ArrayList<Charity> arrayList = new ArrayList<>(res);
            page = new PageImpl<>(arrayList, pageRequest, arrayList.size());
        } else if (searchKey.equals(LOCATION)) {
            Set<Charity> res = charityService.getCharitiesByCountry(searchValue);
            ArrayList<Charity> arrayList = new ArrayList<>(res);
            page = new PageImpl<>(arrayList, pageRequest, arrayList.size());
        }else if (searchKey.equals(NAME)) {
            page = charityService.findByNameLike("%" + searchValue + "%", pageRequest);
        }

        if (page == null) {
            return "resultsPage";
        }
        List<Charity> charities = page.getContent();
        if (charities.size() != 0) {
            model.put("charities", charities);
            model.put("numberOfPages", page.getTotalPages());
            model.put("numberOfResults", page.getTotalElements());
            model.put("pageNumber", page.getNumber());
            model.put("searchedName", searchValue);
            model.put("pageSize", pageSize);
            model.put("thisPageSize", charities.size());
        }
        return "resultsPage";
    }
}
