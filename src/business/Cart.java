package business;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Cart{
    private static final List<Article> articlesInCart = new ArrayList<>();

    /**
     * Method to return the full cart
     * @return Java List of articles in cart
     */
    public static  List<Article> getArticlesInCart() {
        return articlesInCart;
    }

    /**
     * Method to add a new article to the cart
     * @param article the article that will be added
     * @param amountOfArticle the amount of articles that will be added
     */
    public static void addToCart(Article article, int amountOfArticle){
        while (amountOfArticle != 0){
            articlesInCart.add(article);
            amountOfArticle--;
        }
    }

    /**
     * Method to remove an article from the cart
     * @param article the article that will be removed
     */
    public static void removeFromCart(Article article){
        articlesInCart.remove(article);
    }

    /**
     * Method to empty out the cart completely
     */
    public static void emptyCart(){
        articlesInCart.clear();
    }

    /**
     * Method to fetch the total of the cart without the VAT
     * @return int total of cart without VAT
     */
    public static String getTotalWithoutVAT(){
        double totalWithoutVAT = 0;
        for (Article a: getArticlesInCart()) {
            totalWithoutVAT += a.getPrice();
        }
        DecimalFormat df = new DecimalFormat("#.##");
        return df.format(totalWithoutVAT);
    }

    /**
     * Method to fetch the total of the cart with the VAT
     * @return int total of cart with VAT
     */
    public static String getTotalWithVAT(){
        double totalWithVAT = 0;
        for (Article a:getArticlesInCart()) {
            double VAT = a.getVat();
            double vatprice;

            vatprice = a.getPrice()/100 * VAT;
            totalWithVAT = totalWithVAT + vatprice + a.getPrice();
        }
        DecimalFormat df = new DecimalFormat("#.##");

        return df.format(totalWithVAT);
    }
}
