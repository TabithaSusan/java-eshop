package presentation;

import business.Article;
import business.Cart;
import business.IArticleBusiness;
import business.ILoginBusiness;
import presentation.DefaultUserView;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ArticleOverview extends JPanel implements ActionListener, ChangeListener {

    private JTable tableForContent;
    private JPanel mainPanel;
    private JSpinner amountOfArticle;
    private JButton addToCartBtn;
    private IArticleBusiness b;
    private ArticleDetailView articleDetailView;
    private DefaultUserView defaultUserView;
    private Overview overview;
    private Article selectedArticle;
    int selectedAmountOfArticle;

    public ArticleOverview(IArticleBusiness b) {
        this.b = b;
        this.overview = overview;
        this.defaultUserView = defaultUserView;

        addToCartBtn.addActionListener(this);

        amountOfArticle.addChangeListener(this);
        add(mainPanel);
    }

    /**
     * Method to create Article Table to display every Article that is saved in the database, through ArticleBusiness all the needed Parameters are provided.
     */
    private void createUIComponents() {

        String[] columnNames = {"Artikelname", "Preis"};
        String[][] data = new String[b.getAllArticles().size()][columnNames.length];

        for (int row = 0; row < b.getAllArticles().size(); row++) {
            for (int column = 0; column < columnNames.length; column++) {
                if (column % 2 == 0) {
                    data[row][column] = b.getArticleName(row);
                } else {
                    data[row][column] = String.valueOf(b.getPrice(row));
                }

            }

            amountOfArticle =  new JSpinner(new SpinnerNumberModel(1, 1, 50, 1));
        }

        tableForContent = new JTable(data, columnNames) {
            public boolean editCellAt(int row, int column, java.util.EventObject e) {
                return false;
            }
        };

        tableForContent.setAutoCreateRowSorter(true);

        tableForContent.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent mouseEvent) {
                onClick(mouseEvent);
            }
        });
    }

    /**
     * Method to listen for the double click of the User to open up the Article Detail View of the chosen Article
     * @param mouseEvent
     */
    private void onClick (MouseEvent mouseEvent) {
        int row = tableForContent.rowAtPoint(mouseEvent.getPoint());
        String articleName = (String) tableForContent.getModel().getValueAt(tableForContent.convertRowIndexToModel(row),0);
        selectedArticle = b.getArticleByName(articleName);
        if(mouseEvent.getClickCount() == 2 && tableForContent.getSelectedRow() != -1) {
            Overview frame = ((Overview) SwingUtilities.getAncestorOfClass(Overview.class, this));
            if (frame != null) {

                frame.switchMainPanel(new ArticleDetailView(row, selectedArticle, b));
            }

        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==addToCartBtn) {
            selectedAmountOfArticle = (int) amountOfArticle.getValue();
            Cart.addToCart(selectedArticle, selectedAmountOfArticle);
            JOptionPane.showMessageDialog(this, selectedAmountOfArticle+ " " + selectedArticle.getArticleName() + " wurde/n dem Warenkorb hinzugefügt", "Erfolg", JOptionPane.PLAIN_MESSAGE);

        }
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        if(e.getSource()==amountOfArticle) {
            int selectedAmountOfArticle = (int) amountOfArticle.getValue();

        }
    }
}
