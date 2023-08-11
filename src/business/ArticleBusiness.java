package business;

import persistence.IArticlePersistence;

import java.util.ArrayList;
import java.util.List;

public class ArticleBusiness implements IArticleBusiness{
    private IArticlePersistence articlePersistence;

    public ArticleBusiness(IArticlePersistence articlePersistence) {
        this.articlePersistence = articlePersistence;
    }

    public List<Article> searchArticle(String searchword) {
        List<Article> foundArticles = new ArrayList<>();
        for (Article article : getAllArticles()) {
            if (article.getArticleName().contains(searchword)){
                foundArticles.add(article);
            }
        }
        return foundArticles;
    }

    /**
     *Method to return all Articles that are inserted into the Database
     * @return Article objects
     */
    public List<Article> getAllArticles() {
        return articlePersistence.getArticleList();
    }

    /**
     *Method to return the article name at a given index position
     * @param index of the article name that will be needed
     * @return article name
     */
    public String getArticleName(int index){
        return getAllArticles().get(index).getArticleName();
    }

    /**
     *Method to return the price of the article at a given index position
     * @param index of the article price that will be needed
     * @return article price
     */
    public double getPrice(int index) {
        return getAllArticles().get(index).getPrice();
    }

    /**
     *Method to return the picture of the article at a given index position
     * @param index of the article picture that will be needed
     * @return article picture
     */
    public String getPicture(int index) {
        return getAllArticles().get(index).getPicture();
    }

    /**
     *Method to return the stock of the article at a given index position
     * @param index of the article stock that will be needed
     * @return article stock
     */
    public int getStock(int index) {
        return getAllArticles().get(index).getStock();
    }

    /**
     *Method to return the categoryID of the article at a given index position
     * @param index of the article category ID that will be needed
     * @return article categoryID
     */
    public int getCategoryID(int index) {
        return getAllArticles().get(index).getCategoryID();
    }

    /**
     *Method to return the Age Restriction ID of the article at a given index position
     * @param index of the article age restriction ID that will be needed
     * @return article age restriction ID
     */
    public int getAgeRestrictionID(int index) {
        return getAllArticles().get(index).getAgeRestrictionID();
    }

    /**
     *Method to return the VAT ID of the article at a given index position
     * @param index of the article VAT ID that will be nedded
     * @return article VAT ID
     */
    public int getVATID(int index) {
        return getAllArticles().get(index).getVATID();
    }

    /**
     *Method to return the article if searched by name
     * @param name of the article that is searched
     * @return the article that has been searched
     */
    public Article getArticleByName(String name){
        Article article = null;
        for (Article a: getAllArticles()) {
            if(a.getArticleName().equals(name)){
                article = a;
            }
        }
        return article;
    }

}
