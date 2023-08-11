package presentation;

import business.Article;
import business.IArticleBusiness;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EmployeeAdminArticleDetailView extends JPanel implements ActionListener {
    private JPanel employeeAdminArticleDetailView;
    private JTextField articleNameEntry;
    private JTextField articlePriceEntry;
    private JCheckBox specialVAT;
    private JButton updateBtn;
    private JLabel priceWithVATLabel;
    private JLabel stockLabel;
    private JButton cancelBtn;
    private JLabel currentArticleName;
    private JLabel currentPriceWithoutVAT;
    private JLabel currentSpecialVAT;
    private Overview overview;
    private ArticleOverview articleOverview;
    private IArticleBusiness articleBusiness;
    private String articleName;
    private float articlePrice;

    private final Article article;

    public EmployeeAdminArticleDetailView (int index, Article article, IArticleBusiness articleBusiness) {

        add(employeeAdminArticleDetailView);

        this.articleBusiness = articleBusiness;
        this.article = article;
        this.overview = overview;

        currentArticleName.setText(article.getArticleName());
        currentPriceWithoutVAT.setText(String.valueOf(article.getPrice()));

        updateBtn.addActionListener(this);
        cancelBtn.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==updateBtn) {
            articleName = articleNameEntry.getText();
            articlePrice = Float.parseFloat(articlePriceEntry.getText());


        } else if(e.getSource()==cancelBtn) {
            articleOverview = new ArticleOverview(articleBusiness);
            overview.switchMainPanel(articleOverview);
        }
    }

}
