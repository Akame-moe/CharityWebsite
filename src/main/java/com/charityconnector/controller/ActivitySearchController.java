package com.charityconnector.controller;

import com.charityconnector.entity.Cause;
import com.charityconnector.entity.Charity;
import com.charityconnector.entity.Country;
import com.charityconnector.service.CauseService;
import com.charityconnector.service.CharityService;
import com.charityconnector.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


@Controller
public class ActivitySearchController {

    @Resource
    private CharityService charityService;

    @Resource
    private CountryService countryService;

    @Resource
    private CauseService causeService;


    @Autowired
    public ActivitySearchController(CharityService charityService) {
        this.charityService = charityService;
    }

    @RequestMapping("/activityResults")
    public String getResultsPage(Map<String, Object> model,
                                 @RequestParam(name = "searchString", defaultValue = "") String searchString,
                                 @RequestParam(name = "causeString", defaultValue = "Causes") String causeString,
                                 @RequestParam(name = "countryString", defaultValue = "Countries") String countryString,
                                 @RequestParam(defaultValue = "0") int pageNumber,
                                 @RequestParam(defaultValue = "10") int pageSize,
                                 Principal principal) {

        model.put("causeOptions", causeService.getAllCausesName());
        model.put("countryOptions", countryService.getAllCountries());

        if (principal != null)
            model.put("userId", principal.getName());
        if (searchString == null /*|| searchString.trim().equals("")*/)
            return "resultsPage";
        if (pageSize != 10 && pageSize != 25 && pageSize != 50)
            pageSize = 10;
        PageRequest pageRequest = new PageRequest(pageNumber, pageSize, Sort.Direction.DESC, "name");
        Page<Charity> page = null;

        if (causeString.equals("Causes") && countryString.equals("Countries")) {
            Charity[] res = charityService.findByNameOrDescriptionLike(searchString);
            ArrayList<Charity> arrayList = new ArrayList<>(Arrays.asList(res));
            page = new PageImpl<>(arrayList, pageRequest, arrayList.size());
        } else if (!causeString.equals("Causes") && !countryString.equals("Countries")) {
            Cause cause = causeService.findByName(causeString);
            Country country = countryService.findCountryByName(countryString);
            Charity[] res = charityService.findByCauseAndCountry(cause, country, searchString);
            ArrayList<Charity> arrayList = new ArrayList<>(Arrays.asList(res));
            page = new PageImpl<>(arrayList, pageRequest, arrayList.size());
        } else if (!causeString.equals("Causes")) {
            Cause cause = causeService.findByName(causeString);
            Charity[] res = charityService.findByCause(cause, searchString);
            ArrayList<Charity> arrayList = new ArrayList<>(Arrays.asList(res));
            page = new PageImpl<>(arrayList, pageRequest, arrayList.size());
        } else {
            Country country = countryService.findCountryByName(countryString);
            Charity[] res = charityService.findByCountry(country, searchString);
            ArrayList<Charity> arrayList = new ArrayList<>(Arrays.asList(res));
            page = new PageImpl<>(arrayList, pageRequest, arrayList.size());
        }


        if (page == null) {
            model.put("searchedName", searchString);
            return "resultsPage";
        }
        List<Charity> charities = page.getContent();
        if (charities.size() != 0) {
            model.put("charities", charities);
            model.put("numberOfPages", page.getTotalPages());
            model.put("numberOfResults", page.getTotalElements());
            model.put("pageNumber", page.getNumber());
            model.put("searchedName", searchString);
            model.put("pageSize", pageSize);
            model.put("thisPageSize", charities.size());
        }
        return "resultsPage";
    }

//    @RequestMapping("/searchKeys")
//    @ResponseBody
//    public List<String> getAllSearchKeys() {
//        return Arrays.asList(NAME, CAUSE, COUNTRY);
//    }
}
