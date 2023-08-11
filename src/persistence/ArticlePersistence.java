package persistence;

import business.Article;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ArticlePersistence implements IArticlePersistence{
    private final List<Article> articleList;
    private final Connection conn;
    public ArticlePersistence(Connection conn) {
        this.conn = conn;

        articleList = new ArrayList<>();

        int articleID;
        String articleName;
        double price;
        String picture;
        int stock;
        int categoryID;
        int ageRestrictionID;
        int vatID;
        double vat;

        String sql = "SELECT * FROM Articles";
        String sqlCount = "SELECT COUNT(*) AS count FROM Articles;";

        if (conn!=null) {
            try {
                Statement stmtCount = conn.createStatement();
                ResultSet rsCount = stmtCount.executeQuery(sqlCount);
                int count = rsCount.getInt("count");
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);

                for(int i = 1; i <= count; i++){
                    while (rs.next()) {
                        articleID = rs.getInt("ArticleID");
                        articleName = rs.getString("ArticleName");
                        price = rs.getDouble("Price");
                        picture = rs.getString("Picture");
                        stock = rs.getInt("Stock");
                        categoryID = rs.getInt("CategorieID");
                        ageRestrictionID = rs.getInt("AgeRestrictionID");
                        vatID = rs.getInt("VATID");
                        vat = getVatById(vatID);
                        Article article = new Article(articleID, articleName, price, picture, stock, categoryID,
                                ageRestrictionID, vatID, vat);
                        articleList.add(article);
                    }

                }

            } catch (SQLException e) {
                System.out.println("catch ArticlePersistence" );
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Method to fetch the list of all articles in the database
     * @return java list of all articles
     */
    public List<Article> getArticleList(){
        return articleList;
    }

    /**
     * Method to fetch VATs in the database
     * @return hashmap of VATs
     */
    public HashMap<Integer, Double> getVATmap(){
        HashMap<Integer, Double> VATmap = new HashMap<>();
        int VATID;
        double VAT;

        String sqlVAT = "SELECT * FROM VAT";
        String sqlCountVAT = "SELECT COUNT(*) AS count FROM VAT;";

        if (conn!=null) {
            try {
                Statement stmtCount = conn.createStatement();
                ResultSet rsCount = stmtCount.executeQuery(sqlCountVAT);
                int count = rsCount.getInt("count");
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sqlVAT);

                for(int i = 1; i <= count; i++){
                    while (rs.next()) {
                        VATID = rs.getInt("VATID");
                        VAT = rs.getDouble("VAT");

                        VATmap.put(VATID,VAT);
                    }

                }

            } catch (SQLException e) {
                System.out.println("catch ArticlePersistence" );
                System.out.println(e.getMessage());
            }
        }
        return VATmap;
    }

    /**
     * Method to fetch VAT by their ID from database
     * @param id of the VAT
     * @return double of VAT
     */
    public double getVatById(int id){
        return getVATmap().get(id);
    }
}
