package presentation;

import business.ILoginBusiness;
import business.IRegistBusiness;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.EventObject;

public class LogginDataLoginFailureOverview extends JPanel implements ActionListener {
    private JPanel logginDataLoginFailureFrame;
    private JTable failedLoginsTable;
    private JButton goBackBtn;
    private Overview overview;
    private AdminOverview adminOverview;
    private IRegistBusiness registBusiness;
    private ILoginBusiness loginBusiness;

    public  LogginDataLoginFailureOverview(Overview overview, IRegistBusiness registBusiness, ILoginBusiness loginBusiness) {

        this.overview = overview;
        this.registBusiness = registBusiness;
        this.loginBusiness = loginBusiness;
        add(logginDataLoginFailureFrame);

        goBackBtn.addActionListener(this);
    }

    private void createUIComponents() {
        String[] columnNames = {"Datum/Uhrzeit", "E-Mail Adresse"};
        String[][] data = new String[loginBusiness.getFailedLogins().size()][columnNames.length];

        for (int row = 0; row < loginBusiness.getFailedLogins().size(); row++) {
            for (int column = 0; column < columnNames.length; column++) {
                if (column % 2 == 0) {
                    data[row][column] = loginBusiness.getFailedLogins().get(row).getDatetime();
                } else {
                    data[row][column] = loginBusiness.getFailedLogins().get(row).getEmail();
                }

            }
        }

        failedLoginsTable = new JTable(data, columnNames) {
            public boolean editCellAt(int row, int column, EventObject e) {
                return false;
            }
        };

        failedLoginsTable.setAutoCreateRowSorter(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        adminOverview = new AdminOverview(overview, registBusiness, loginBusiness);
        overview.switchMainPanel(adminOverview);
    }
}
