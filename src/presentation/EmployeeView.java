package presentation;

import business.IArticleBusiness;
import business.ILoginBusiness;
import business.IRegistBusiness;
import business.LoginBusiness;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EmployeeView extends JPanel implements ActionListener {
    private JPanel employeeFrame;
    private JButton employeeBtn;
    private JButton logoutBtn;
    private JLabel employeeName;
    private Overview overview;
    private EmployeeOverview employeeOverview;
    private IRegistBusiness registBusiness;
    private ILoginBusiness loginBusiness;
    private IArticleBusiness articleBusiness;
    private ArticleOverview articleOverview;

    public EmployeeView(Overview overview, IRegistBusiness registBusiness, ILoginBusiness loginBusiness, IArticleBusiness articleBusiness) {

        add(employeeFrame);
        this.registBusiness = registBusiness;
        this.overview = overview;
        this.loginBusiness = loginBusiness;
        this.articleBusiness = articleBusiness;

        employeeName.setText(LoginBusiness.getCurrentEmployee().getFirstName());

        employeeBtn.addActionListener(this);
        logoutBtn.addActionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource()== employeeBtn) {
            employeeOverview = new EmployeeOverview(overview, registBusiness);
            overview.switchMainPanel(employeeOverview);
        }else if(e.getSource()==logoutBtn){
            LoginBusiness.setCurrentEmployeeNull();
            DefaultUserView defaultUserView = new DefaultUserView(loginBusiness,overview,registBusiness,articleBusiness);
            overview.switchUserPanel(defaultUserView);
            articleOverview = new ArticleOverview(articleBusiness);
            overview.switchMainPanel(articleOverview);
        }
    }
}
