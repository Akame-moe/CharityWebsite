package service;

import com.charityconnector.WebsiteApplication;
import com.charityconnector.bean.Article;
import com.charityconnector.service.ArticleService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = WebsiteApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ArticleServiceTest {

    @Autowired
    ArticleService articleService;

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
        // update
        article.setBody("changed");
        articleService.update(article);
        Assert.assertEquals("changed", articleService.findById(added.getId()).getBody());
        // delete
        articleService.deleteById(added.getId());
        Assert.assertNull(articleService.findById(added.getId()));
    }
}
