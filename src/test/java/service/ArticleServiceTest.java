package service;

import com.charityconnector.WebsiteApplication;
import com.charityconnector.entity.Article;
import com.charityconnector.entity.Charity;
import com.charityconnector.service.ArticleService;
import com.charityconnector.service.CharityService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = WebsiteApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ArticleServiceTest {

    //this should go
    @Autowired
    CharityService charityService;

    @Autowired
    ArticleService articleService;

    @Before
    public void makeDB() {
        //make fake in memory db
        //load it with data
        //connect article service with this db
    }

    @Test
    public void crudArticle() {
        Article article = new Article();
        article.setTitle("title");
        article.setBody("body");
        article.setCharityId(1);
        // add
        Article added = articleService.addArticle(article);
        // get
        Article got = articleService.findById(added.getId());
        Assert.assertEquals(got.getBody(), added.getBody());
        Assert.assertEquals(got.getCharityId(), added.getCharityId());
        Assert.assertEquals(got.getTitle(), added.getTitle());
        // updateSelective
        article.setBody("changed");
        articleService.updateSelective(article);
        Assert.assertEquals("changed", articleService.findById(added.getId()).getBody());
        // delete
        articleService.deleteById(added.getId());
        Assert.assertNull(articleService.findById(added.getId()));
    }

    @Test
    public void testFindArticlesByCharityId() {
        // add one charity
        Charity charity = new Charity();
        charity.setName("charityTest");
        Charity added = charityService.addCharity(charity);
        Article article1 = new Article();
        Article article2 = new Article();

        try {
            // get charity id
            Long charityId = added.getId();
            // add multiple articles for this charity

            article1.setCharityId(charityId);
            article1.setTitle("article1");

            article2.setCharityId(charityId);
            article2.setTitle("article2");

            articleService.addArticle(article1);
            articleService.addArticle(article2);

            // compare the result findArticlesByCharityId and the article objects we created
            Article[] articles = articleService.findArticlesByCharityId(charityId);
            Assert.assertTrue(articles.length == 2);
            for (Article a : articles) {
                if (a.getId() == article1.getId()) {
                    Assert.assertEquals(article1.getTitle(), a.getTitle());
                } else {
                    Assert.assertEquals(article2.getTitle(), a.getTitle());
                }
            }
        } finally {
            articleService.deleteById(article1.getId());
            articleService.deleteById(article2.getId());
            charityService.deleteById(added.getId());
        }
    }
}
