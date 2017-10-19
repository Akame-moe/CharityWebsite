package com.charityconnector.controller;

import com.charityconnector.bean.Article;
import com.charityconnector.service.ArticleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Controller
public class ArticleController {

    @Resource
    private ArticleService articleService;

    @RequestMapping(path = "/articles/{charityId}", method = RequestMethod.GET)
    @ResponseBody
    Article[] getArticlesByCharityId(@PathVariable("charityId") Long charityId) {
        return articleService.findArticlesByCharityId(charityId);
    }

    @RequestMapping(path = "/article/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Article getArticleById(@PathVariable("id") Long id) {
        return articleService.findById(id);
    }

    @RequestMapping(path = "/article/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void deleteArticleById(@PathVariable("id") Long id) {
        articleService.deleteById(id);
    }

    @RequestMapping(path = "/article", method = RequestMethod.POST)
    @ResponseBody
    public Article addArticle(@RequestBody Article article) {
        Article res = articleService.addArticle(article);
        return res;
    }

    @RequestMapping(path = "/article", method = RequestMethod.PATCH)
    @ResponseBody
    public void updateCharity(@RequestBody Article article) {
        articleService.update(article);
    }
}
