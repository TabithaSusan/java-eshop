package presentation;

import business.Customer;
import business.ILoginBusiness;
import business.IRegistBusiness;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.EventObject;

public class AdminOverview extends JPanel implements ActionListener {
    private JPanel adminOverviewFrame;
    private JTable tableForUnlockedCustomers;
    private JButton unlockCustomerBtn;
    private JTable tableForArticlesWithLowStock;
    private JButton orderLowArticlesBtn;
    private JButton logginDataEmployeeAdminBtn;
    private JButton logginDataFailedLoginsBtn;
    private JButton createNewEmployeeBtn;
    Customer selectedCust;
    private Overview overview;
    private LogginDataEmployeeAdminOverview logginDataEmployeeAdminOverview;
    private LogginDataLoginFailureOverview logginDataLoginFailureOverview;
    private CreateNewEmployeeView createNewEmployeeView;
    private IRegistBusiness registBusiness;
    private ILoginBusiness loginBusiness;

    public AdminOverview(Overview overview, IRegistBusiness registBusiness, ILoginBusiness loginBusiness) {
        this.overview = overview;
        this.registBusiness= registBusiness;
        this.loginBusiness = loginBusiness;

        unlockCustomerBtn.addActionListener(this);
        logginDataEmployeeAdminBtn.addActionListener(this);
        logginDataFailedLoginsBtn.addActionListener(this);
        createNewEmployeeBtn.addActionListener(this);
        add(adminOverviewFrame);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==unlockCustomerBtn) {
            if(selectedCust != null){
                registBusiness.unlockCustomer(selectedCust);
                JOptionPane.showMessageDialog(this, selectedCust.getFirstName() + " wurde freigeschaltet.", "Freigeschaltet", JOptionPane.PLAIN_MESSAGE);
                overview.switchMainPanel(new AdminOverview(overview, registBusiness,loginBusiness));
            } else {
                JOptionPane.showMessageDialog(this, "Bitte wähle einen Kunden aus indem du auf dessen Namen doppelklickst.", "Kein Kunde ausgewählt", JOptionPane.ERROR_MESSAGE);
            }
        } else if(e.getSource()==logginDataEmployeeAdminBtn) {
            logginDataEmployeeAdminOverview = new LogginDataEmployeeAdminOverview(overview, registBusiness, loginBusiness);
            overview.switchMainPanel(logginDataEmployeeAdminOverview);

        } else if (e.getSource()==logginDataFailedLoginsBtn) {
            logginDataLoginFailureOverview = new LogginDataLoginFailureOverview(overview, registBusiness, loginBusiness);
            overview.switchMainPanel(logginDataLoginFailureOverview);
        } else if (e.getSource()== createNewEmployeeBtn) {
            createNewEmployeeView = new CreateNewEmployeeView(overview, registBusiness, loginBusiness);
            overview.switchMainPanel(createNewEmployeeView);
        }
    }

    private void createUIComponents() {
        String[] columnNames = {"Vorname", "Nachname"};
        String[][] data = new String[registBusiness.getLockedCustomerList().size()][columnNames.length];

        for (int row = 0; row < registBusiness.getLockedCustomerList().size(); row++) {
            for (int column = 0; column < columnNames.length; column++) {
                if (column % 2 == 0) {
                    data[row][column] = registBusiness.getLockedCustomerList().get(row).getFirstName();
                } else {
                    data[row][column] = registBusiness.getLockedCustomerList().get(row).getSurname();
                }
            }
        }

        tableForUnlockedCustomers = new JTable(data, columnNames) {
            public boolean editCellAt(int row, int column, EventObject e) {
                return false;
            }
        };

        tableForUnlockedCustomers.setAutoCreateRowSorter(true);

        tableForUnlockedCustomers.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent mouseEvent) {
                onTableClick(mouseEvent);
            }
        });
    }
    private void onTableClick(MouseEvent mouseEvent) {
        if(mouseEvent.getClickCount() == 2 && tableForUnlockedCustomers.getSelectedRow() != -1) {
            int row = tableForUnlockedCustomers.rowAtPoint(mouseEvent.getPoint());
            String surname = (String) tableForUnlockedCustomers.getModel().getValueAt(tableForUnlockedCustomers.convertRowIndexToModel(row),1);
            selectedCust = registBusiness.findCustInLockedCustomerList(surname);
        }
    }
}
