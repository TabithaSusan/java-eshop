package presentation;

import business.Article;
import business.IArticleBusiness;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class SearchView extends JPanel {

    private JTable tableForContent;
    private JPanel mainPanel;

    private IArticleBusiness b;

    private ArticleDetailView articleDetailView;
    List<Article> searchedList;

    public SearchView(IArticleBusiness b, List<Article> searchedList) {
        this.searchedList = searchedList;
        this.b = b;
        add(mainPanel);
    }

    /**
     * Method to create Article Table to display every Article that is saved in the database, through ArticleBusiness all the needed Parameters are provided.
     */
    private void createUIComponents() {

        String[] columnNames = {"Artikelname", "Preis"};
        String[][] data = new String[searchedList.size()][columnNames.length];

        for (int row = 0; row < searchedList.size(); row++) {
            for (int column = 0; column < columnNames.length; column++) {
                if (column % 2 == 0) {
                    data[row][column] = searchedList.get(row).getArticleName();
                } else {
                    data[row][column] = String.valueOf(searchedList.get(row).getPrice());
                }

            }
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

    private void onClick (MouseEvent mouseEvent) {
        int row = tableForContent.rowAtPoint(mouseEvent.getPoint());
        String articleName = (String) tableForContent.getModel().getValueAt(tableForContent.convertRowIndexToModel(row),0);
        Article selectedArticle = b.getArticleByName(articleName);
        if(mouseEvent.getClickCount() == 2 && tableForContent.getSelectedRow() != -1) {
            Overview frame = ((Overview) SwingUtilities.getAncestorOfClass(Overview.class, this));
            if (frame != null) {
                frame.switchMainPanel(new ArticleDetailView(row, selectedArticle, b));
            }

        }
    }
}
