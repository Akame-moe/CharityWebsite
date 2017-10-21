package com.charityconnector.controller;

import com.charityconnector.bean.Charity;
import com.charityconnector.service.ArticleService;
import com.charityconnector.service.CharityService;
import org.springframework.stereotype.Controller;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Encoder;

import javax.annotation.Resource;
import java.io.*;
import java.util.Map;




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
        charityService.updateSelective(charity);
    }
    
    @RequestMapping("/charityPage/{id}")
    public String getCharityPage(Map<String, Object> model, @PathVariable("id") Long id) {
	    model.put("charity", charityService.findById(id));
        model.put("articles", articleService.findArticlesByCharityId(id));
        return "charityPage";
    }



    // This method is used for test the upload functionality, should be modify later!!!
    @RequestMapping(path = "/charity/{id}/logo", method = RequestMethod.POST)
    public String updateCharityLogo(@RequestParam("file") MultipartFile file, @PathVariable("id") Long id) {
        Charity charity = charityService.findById(id);
        if (!file.isEmpty()) {
            try {
                charity.setLogoFile("data:"+file.getContentType()+";base64," + new BASE64Encoder().encode(file.getBytes()).replaceAll("\r|\n", ""));
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


//    // This method is used for test the upload functionality, should be modify later!!!
//    @RequestMapping(path = "/charity/{id}/logo", method = RequestMethod.POST)
//    public String updateCharityLogo(@RequestParam("file") MultipartFile file, @PathVariable("id") Long id) {
//
//
//        String classPath = ClassUtils.getDefaultClassLoader().getResource("").getPath();
//        String logoPath =classPath.replace("out/production/classes/","")+"src/main/logo-images/";
//
//        Charity charity = charityService.findById(id);
//        String oldFileName = charity.getLogoPath().replace("../../logo-images/","");
//        String oldFilePath = logoPath+oldFileName;
//        File oldImageFile = new File(oldFilePath);
//        if (oldImageFile.exists() && oldImageFile.isFile()) {
//            oldImageFile.delete();
//        }
//
//        if (!file.isEmpty()) {
//            try {
//                BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(new File(logoPath + id + "_" + file.getOriginalFilename())));
//                out.write(file.getBytes());
//                out.flush();
//                out.close();
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//                return "fail," + e.getMessage();
//            } catch (IOException e) {
//                e.printStackTrace();
//                return "faile," + e.getMessage();
//            }
//
//        } else {
//            return "Update File, the File is empty.";
//        }
//        charity.setLogoPath("../../logo-images/" + id + "_" + file.getOriginalFilename());
//        charityService.updateSelective(charity);
//        return ("redirect:/charityPage/" + id);
//    }



}
