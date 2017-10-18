package com.charityconnector.controller;

import com.charityconnector.bean.Charity;
import com.charityconnector.service.CharityService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import java.util.Map;




@Controller
public class CharityController {

    @Resource
    private CharityService charityService;
    
    @RequestMapping(path = "/charities/{name}", method = RequestMethod.GET)
    @ResponseBody
    Charity[] getCharitiesByName(@PathVariable("name") String name) {
        return charityService.findByName(name);
    }

    @RequestMapping(path = "/charity/{id}", method = RequestMethod.GET)
    @ResponseBody
    Charity getCharityById(@PathVariable("id") Long id) {
        return  charityService.findById(id);}

    @RequestMapping(path = "/charity", method = RequestMethod.POST)
    @ResponseBody
    public Charity addCharity(@RequestBody Charity charity) {
        Charity res = charityService.addCharity(charity);
        return res;
    }

    @RequestMapping(path = "/charity/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void deleteCharityById(@PathVariable("id") Long id) {
        charityService.deleteById(id);
    }

    @RequestMapping(path = "/charity", method = RequestMethod.PATCH)
    @ResponseBody
    public void updateCharity(@RequestBody Charity charity) {
        charityService.update(charity);
    }


    @RequestMapping("/charityPage/{id}")
    public String getCharityPage(Map<String, Object> model, @PathVariable("id") Long id) {
	    model.put("charity", charityService.findById(id));
	     return "charityPage";
    }



}
