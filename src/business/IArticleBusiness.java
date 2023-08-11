package business;

import java.util.List;

public interface IArticleBusiness {
    List<Article> searchArticle(String articleName);
    List<Article> getAllArticles();
    String getArticleName(int index);
    double getPrice(int index);
    String getPicture(int index);
    int getStock(int index);
    int getCategoryID(int index);
    int getAgeRestrictionID(int index);
    int getVATID(int index);
    Article getArticleByName(String name);

}
