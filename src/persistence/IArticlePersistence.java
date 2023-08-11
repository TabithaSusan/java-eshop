package persistence;

import business.Article;

import java.util.HashMap;
import java.util.List;

public interface IArticlePersistence {
    List<Article> getArticleList();

    HashMap<Integer, Double> getVATmap();
}
