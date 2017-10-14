package com.charityconnector.controller;

import com.charityconnector.bean.Charity;
import com.charityconnector.service.CharityService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * because we combine two different types of apis, so here only use Controller annotation
 */
@Controller
public class CharityController {

    @Resource
    private CharityService charityService;

    /**
     * Get charities by name.
     * Charities may have same name.
     *
     * @param name
     * @return
     */
    @RequestMapping(path = "/charities/{name}", method = RequestMethod.GET)
    @ResponseBody
    Charity[] getCharitiesByName(@PathVariable("name") String name) {
        return charityService.findByName(name);
    }

    /**
     * Charity has unique Id so we find one charity by it's id.
     *
     * @param id
     * @return
     */
    @RequestMapping(path = "/charity/{id}", method = RequestMethod.GET)
    @ResponseBody
    Charity getCharityById(@PathVariable("id") Long id) {
        return charityService.findById(id);
    }

    /**
     * Add a new charity.
     *
     * @param charity
     * @return
     */
    @RequestMapping(path = "/charity", method = RequestMethod.POST)
    @ResponseBody
    public Charity add(@RequestBody Charity charity) {
        Charity res = charityService.addCharity(charity);
        return res;
    }

    /**
     * Delete a charity by description.
     *
     * @param id
     * @return
     */
    @RequestMapping(path = "/charity/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void removeCharityByDescription(@PathVariable("id") Long id ) {
        charityService.deleteById(id);
    }

    /**
     * Update a particular Charity.
     *
     * @param charity
     * @return
     */
    @RequestMapping(path = "/charity", method = RequestMethod.PATCH)
    @ResponseBody
    public void update(@RequestBody Charity charity) {
        charityService.update(charity);
    }

    /**
     * Provide a api to retrieve charity page.
     *
     * @return
     */
    @RequestMapping("/charityPage")
    public String getCharityPage(Model model) {
        return "charityPage";
    }


}
