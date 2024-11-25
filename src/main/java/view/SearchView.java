package view;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import data_access.MessageDataAccessObject;
import interface_adapter.search_messages.SearchMessagesController;
import interface_adapter.search_messages.SearchMessagesPresenter;
import use_case.search_messages.SearchMessagesInteractor;
import use_case.search_messages.SearchMessagesUserDataAccessInterface;

/**
 * Represents the graphical user interface for searching messages.
 *
 * <p>This view allows the user to input a search query and displays the results
 * or informational messages using pop-up dialogs. The view integrates the MVC
 * architecture by interacting with a controller, presenter, and interactor.</p>
 */
public class SearchView extends JFrame {

    private final JTextField searchField;
    private final JButton searchButton;
    private final SearchMessagesController controller;
    private final int magic400 = 400;
    private final int magic300 = 300;
    private final int magic20 = 20;

    public SearchView() {
        setTitle("Search View");
        setSize(magic400, magic300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Initialize components
        searchField = new JTextField(magic20);
        searchButton = new JButton("Search");

        // Setup layout
        final JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.add(searchField, BorderLayout.CENTER);
        panel.add(searchButton, BorderLayout.EAST);
        add(panel, BorderLayout.NORTH);

        final JLabel resultLabel = new JLabel("Enter your search query above and click Search.");
        add(resultLabel, BorderLayout.CENTER);

        // Create presenter and interactor
        final SearchMessagesPresenter presenter = new SearchMessagesPresenter(this);
        final SearchMessagesUserDataAccessInterface dataAccess = new MessageDataAccessObject();
        final SearchMessagesInteractor interactor = new SearchMessagesInteractor(dataAccess, presenter);
        controller = new SearchMessagesController(interactor);

        // Add action listener to search button
        searchButton.addActionListener(e -> {
            final String query = searchField.getText();
            controller.handleSearch(query);
        });

        setLocationRelativeTo(null);
    }

    /**
     * Displays the search results in a pop-up dialog.
     *
     * @param results The search results to display.
     */
    public void showResults(String results) {
        JOptionPane.showMessageDialog(this, results, "Search Results", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Displays an informational message in a pop-up dialog.
     *
     * @param message The message to display.
     */
    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Info", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Entry point for launching the {@code SearchView}.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SearchView().setVisible(true));
    }
}
