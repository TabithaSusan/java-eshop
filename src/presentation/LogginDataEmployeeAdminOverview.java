package presentation;

import business.ILoginBusiness;
import business.IRegistBusiness;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogginDataEmployeeAdminOverview extends JPanel implements ActionListener {
    private JPanel logginDataEmployeeAdminFrame;
    private JTable logginDataTable;
    private JButton goBackBtn;
    private AdminOverview adminOverview;
    private Overview overview;
    private IRegistBusiness registBusiness;
    private ILoginBusiness loginBusiness;

    public LogginDataEmployeeAdminOverview(Overview overview, IRegistBusiness registBusiness, ILoginBusiness loginBusiness) {
        add(logginDataEmployeeAdminFrame);

        this.overview = overview;
        this.registBusiness = registBusiness;
        this.loginBusiness = loginBusiness;

        goBackBtn.addActionListener(this);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        adminOverview = new AdminOverview(overview, registBusiness, loginBusiness);
        overview.switchMainPanel(adminOverview);
    }
}
