package presentation;


import business.IArticleBusiness;
import business.ILoginBusiness;
import business.IRegistBusiness;
import business.LoginBusiness;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomerView extends JPanel implements ActionListener {
    private JPanel customerFrame;
    private JButton logoutBtn;
    private JLabel customerName;
    private Overview overview;
    IArticleBusiness articleBusiness;
    ILoginBusiness loginBusiness;
    IRegistBusiness registBusiness;
    private ArticleOverview articleOverview;

    public CustomerView(Overview overview, IArticleBusiness articleBusiness, ILoginBusiness loginBusiness, IRegistBusiness registBusiness) {

        this.overview = overview;
        this.articleBusiness = articleBusiness;
        this.loginBusiness = loginBusiness;
        this.registBusiness = registBusiness;

        add(customerFrame);

        customerName.setText(LoginBusiness.getCurrentCust().getFirstName());

        logoutBtn.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==logoutBtn){
            LoginBusiness.setCurrentEmployeeNull();
            DefaultUserView defaultUserView = new DefaultUserView(loginBusiness,overview,registBusiness,articleBusiness);
            overview.switchUserPanel(defaultUserView);
            articleOverview = new ArticleOverview(articleBusiness);
            overview.switchMainPanel(articleOverview);
        }
    }
}
