package presentation;

import business.Article;
import business.IArticleBusiness;
import business.ILoginBusiness;
import business.IRegistBusiness;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Overview extends JFrame implements ActionListener {

    private JTable tableForContent;
    private JPanel panel;
    private JTextField searchTextField;
    private JButton searchBtn;
    private JButton cartBtn;
    //Warenkorb icon by Icons8
    private JPanel mainPanel;
    private JButton homeBtn;
    private JPanel userPanel;
    private JPanel thisIsInMainPanel;
    private JPanel thisIsInUserPanel;
    private ILoginBusiness b1;
    private IArticleBusiness b2;
    private final IRegistBusiness registBusiness;
    private CartView cartView;
    private SearchView searchView;
    private ArticleOverview articleOverview;
    int userClassID; // 1=User, 2=Employee, 3=Admin
    private String searchWord;
    private List<Article> searchedList;

    /**
     * Constructor to build panel
     */
    public Overview(ILoginBusiness b1, IArticleBusiness b2, IRegistBusiness b3) {
        super("eShop Programmier Praktikum SoSe 2022 - Gruppe 3");
        this.b1 = b1;
        this.b2 = b2;
        this.registBusiness = b3;

        this.userClassID = userClassID;

        setContentPane(panel);
        pack();

        IArticleBusiness items;
        //List<Article> listArticles = items.getAllArticles();

        searchBtn.addActionListener(this);
        cartBtn.addActionListener(this);
        homeBtn.addActionListener(this);

        switchMainPanel(new ArticleOverview(b2));
        switchUserPanel(new DefaultUserView(b1, this, registBusiness,b2));

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    /**
     * Method to set upper Panel to current state of User and wether the user is logged in or not
     * @param newUserPanel is the Panel that will be placed into the panel
     */
    public void switchUserPanel(JPanel newUserPanel) {
        if(thisIsInUserPanel != null) {
            userPanel.remove(thisIsInUserPanel);
        }
        userPanel.add(newUserPanel);
        thisIsInUserPanel = newUserPanel;
        setVisible(true);
    }

    /**
     * Method to set main Panel to current state of App to display the cart, article overview etc.
     * @param newMainPanel is the Panel that will be placed into the panel
     */
    public void switchMainPanel(JPanel newMainPanel) {
        if (thisIsInMainPanel != null) {
            mainPanel.remove(thisIsInMainPanel);
        }
        mainPanel.add(newMainPanel);
        thisIsInMainPanel = newMainPanel;
        setVisible(true);
    }

    public void addRow(String str1, String str2){
        DefaultTableModel model = (DefaultTableModel) tableForContent.getModel();
        model.addRow(new Object[]{str1, str2});
    }

    /**
     * Method to log into Platform with Data, if login for Costumer will not work, it will go on to alternativeActionPerformedEmployee to try for login Data for Employee
     *
     * @param e the event to be processed
     */
    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==searchBtn) { //Listening to Search Button
            searchedList = b2.searchArticle(getSearchWord());
            searchView = new SearchView(b2, searchedList);
            switchMainPanel(searchView);
        } else if(e.getSource()==cartBtn) { //Listening to Cart Button
            cartView = new CartView(b2, this, b1);
            switchMainPanel(cartView);
        }else if(e.getSource()==homeBtn) {
            articleOverview = new ArticleOverview(b2);
            switchMainPanel(articleOverview);
        }
    }

    public String getSearchWord(){
        searchWord = searchTextField.getText();
        return searchWord;
    }


}

