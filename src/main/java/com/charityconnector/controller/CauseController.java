package com.charityconnector.controller;

import com.charityconnector.service.CauseService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.Map;

@Controller
public class CauseController {

    @Resource
    private CauseService causeService;


    @RequestMapping("/causePage/{id}")
    public String getCharityPage(Map<String, Object> model, @PathVariable("id") Long id) {
        model.put("cause", causeService.findById(id));

        return "causePage";
    }
}
