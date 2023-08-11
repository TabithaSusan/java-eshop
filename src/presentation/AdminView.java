package presentation;


import business.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminView extends JPanel implements ActionListener {
    private JPanel adminFrame;
    private JButton adminBtn;
    private JButton logoutBtn;
    private JLabel adminName;
    private Overview overview;
    private AdminOverview adminOverview;
    private IRegistBusiness registBusiness;
    private ILoginBusiness loginBusiness;
    private IArticleBusiness articleBusiness;
    private ArticleOverview articleOverview;

    public AdminView(Overview overview, IRegistBusiness registBusiness, ILoginBusiness loginBusiness, IArticleBusiness articleBusiness) {

        add(adminFrame);

        this.overview = overview;
        this.registBusiness = registBusiness;
        this.loginBusiness = loginBusiness;
        this.articleBusiness = articleBusiness;

        adminName.setText(LoginBusiness.getCurrentAdmin().getFirstName());

        adminBtn.addActionListener(this);
        logoutBtn.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==adminBtn) {
            adminOverview = new AdminOverview(overview, registBusiness, loginBusiness);
            overview.switchMainPanel(adminOverview);
        } else if(e.getSource()==logoutBtn){
            LoginBusiness.setCurrentAdminNull();
            DefaultUserView defaultUserView = new DefaultUserView(loginBusiness,overview,registBusiness,articleBusiness);
            overview.switchUserPanel(defaultUserView);
            articleOverview = new ArticleOverview(articleBusiness);
            overview.switchMainPanel(articleOverview);
        }
    }
}
