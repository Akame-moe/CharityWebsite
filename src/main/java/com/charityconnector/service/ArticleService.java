package com.charityconnector.service;

import com.charityconnector.bean.Article;

public interface ArticleService {

    Article findById(Long id);

    Article[] findArticlesByCharityId(Long id);

    void deleteById(Long id);

    Article addArticle(Article article);

    void update(Article article);
}
