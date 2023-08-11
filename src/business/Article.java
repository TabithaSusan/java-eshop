package business;

public class Article {
    int articleID;
    String articleName;
    double price;
    String picture;
    int stock;
    int categoryID;
    int ageRestrictionID;
    int vatID;
    double vat;


    public Article(int articleID, String articleName, double price, String picture, int stock, int categoryID,
                   int ageRestrictionID, int vatID, double vat) {
        this.articleID = articleID;
        this.articleName = articleName;
        this.price = price;
        this.picture = picture;
        this.stock = stock;
        this.categoryID = categoryID;
        this.ageRestrictionID = ageRestrictionID;
        this.vatID = vatID;
        this.vat = vat;
    }

    /**
     * Method to fetch the Article ID of Article that has been saved in the database table article
     * @return will return the ID of article
     */
    public int getArticleID() {
        return articleID;
    }

    /**
     * Method to fetch the Article Name of Article that has been saved in the database table article
     * @return will return the name of article
     */
    public String getArticleName() {
        return articleName;
    }

    /**
     * Method to fetch the price of Article that has been saved in the database table Article
     * @return will return the price of article
     */
    public double getPrice() {
        return price;
    }

    /**
     * Method to fetch the photo of Article that has been saved in the database table Article
     * @return will return the photo of article
     */
    public String getPicture() { return picture; }

    /**
     * Method to fetch the current Stock of Article that has been saved in the database table Article
     * @return will return the int of the current stock of article
     */
    public int getStock() { return stock; }

    /**
     * Method to fetch the Category of article that has been saved in the database table Article
     * @return will return the category ID of article
     */
    public int getCategoryID() { return categoryID; }

    /**
     * Method to fetch the Age Restriction of article that has been saved in the database table Article
     * @return will return the int of the Age restriction ID of article
     */
    public int getAgeRestrictionID() { return ageRestrictionID; }

    /**
     * Method to fetch the VAT ID of article that has been saved in the database table Aricle
     * @return will return the int of the VAT ID of article
     */
    public int getVATID() { return vatID; }

    /**
     * Method to fetch VAT of article that has been saved in the database table Article
     * @return will return the int of the VAT of article
     */
    public  double getVat() { return vat; }
}
