package com.charityconnector.controller;

import com.charityconnector.entity.Charity;
import com.charityconnector.service.ArticleService;
import com.charityconnector.service.CharityService;
import com.charityconnector.util.CodeUtil;
import com.charityconnector.util.MailUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import sun.misc.BASE64Encoder;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Map;
import java.util.Set;


@Controller
public class CharityController {

    @Resource
    private CharityService charityService;

    @Resource
    private ArticleService articleService;

    @RequestMapping(path = "/charities/{name}", method = RequestMethod.GET)
    @ResponseBody
    Charity[] getCharitiesByName(@PathVariable("name") String name) {
        return charityService.findByName(name);
    }

    @RequestMapping(path = "/charity/{id}", method = RequestMethod.GET)
    @ResponseBody
    Charity getCharityById(@PathVariable("id") Long id) {
        return charityService.findById(id);
    }

    @RequestMapping(path = "/charity", method = RequestMethod.POST)
    @ResponseBody
    public Charity addCharity(@RequestBody Charity charity) {
        Charity res = charityService.addCharity(charity);
        return res;
    }

    @RequestMapping(path = "/charity/random", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView getRandomCharity() {
        Charity res = null;
        while (res == null)
            res = charityService.findRandom();
        return new ModelAndView("redirect:/charityPage/" + res.getId());
    }


    @RequestMapping(path = "/charity/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void deleteCharityById(@PathVariable("id") Long id) {
        charityService.deleteById(id);
    }

    @RequestMapping(path = "/charity", method = RequestMethod.PATCH)
    @ResponseBody
    public void updateCharity(@RequestBody Charity charity) {
        charityService.updateSelective(charity);
    }

    @RequestMapping("/charityPage/{id}")
    public String getCharityPage(Map<String, Object> model, @PathVariable("id") Long id) {
        model.put("charity", charityService.findById(id));
        model.put("articles", articleService.findArticlesByCharityId(id));

        return "charityPage";
    }

    // This method is used for upload the logo of the charity.
    // The image is encoded with base64 and then store directly to the databse.
    @RequestMapping(path = "/charity/{id}/logo", method = RequestMethod.POST)
    public String updateCharityLogo(@RequestParam("file") MultipartFile file, @PathVariable("id") Long id) {
        Charity charity = charityService.findById(id);
        if (!file.isEmpty()) {
            try {
                charity.setLogoFile("data:" + file.getContentType() + ";base64," + new BASE64Encoder().encode(file.getBytes()).replaceAll("\r|\n", ""));
            } catch (IOException e) {
                e.printStackTrace();
                return "File Error";
            }
            charityService.updateDirect(charity);
            return ("redirect:/charityPage/" + id);
        } else {
            return "Update File, the File is empty.";
        }
    }

//    @RequestMapping(path = "/charities", method = RequestMethod.GET)
//    @ResponseBody
//    Charity[] getCharitiesByCauses(@RequestParam("cause") long causes) {
//        return charityService.findByCauses(causes);
//    }

    @RequestMapping(path = "/charity/{id}/verify", method = RequestMethod.POST)
    public ResponseEntity<String> verifyCharity(@PathVariable("id") Long id) {
        Charity charity = charityService.findById(id);
        String email = charity.getEmail();
        if (email == null) {
            return new ResponseEntity<String>(HttpStatus.METHOD_NOT_ALLOWED);
        } else {
            //Generate the verify code and save it to the database;
            String code = CodeUtil.generateUniqueCode();
            Long charityID = charity.getId();
            charity.setVerifyCode(code);
            charityService.updateDirect(charity);
            new Thread(new MailUtil(email, code, charityID)).start();
            return new ResponseEntity<String>(HttpStatus.OK);
        }
    }

    @RequestMapping(path = "/charities/cause/{cause}", method = RequestMethod.GET)
    @ResponseBody
    public Set<Charity> getCharityByCause(@PathVariable("cause") String cause) {
        return charityService.getCharitiesByCause(cause);
    }

    @RequestMapping(path = "/charities/country/{country}", method = RequestMethod.GET)
    @ResponseBody
    public Set<Charity> getCharityByCountry(@PathVariable("country") String country) {
        return charityService.getCharitiesByCountry(country);
    }

}

