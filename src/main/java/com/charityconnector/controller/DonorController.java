package com.charityconnector.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.Map;

@Controller
public class DonorController {
    @RequestMapping("/donorPage/{id}")
    public String getDonorPage(Map<String, Object> model, @PathVariable("id") Long id, Principal principal) {
        if (principal == null || !principal.getName().equals((id.toString())))
            return "error";

        model.put("userId", principal.getName());

        return "userPage";
    }
}
