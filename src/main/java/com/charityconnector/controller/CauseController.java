package com.charityconnector.controller;

import com.charityconnector.entity.Cause;
import com.charityconnector.service.CauseService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Controller
public class CauseController {

    @Resource
    private CauseService causeService;


    @RequestMapping("/causePage/{id}")
    public String getCharityPage(Map<String, Object> model, @PathVariable("id") Long id) {
        Cause cause = causeService.findById(id);
        model.put("cause",cause);
        model.put("charities",cause.getCharities());
        return "causePage";
    }

    @RequestMapping("/causes")
    @ResponseBody
    public List<String> getCharityPage() {
        return causeService.getAllCauses();
    }

}
