package view;

import data_access.MessageDataAccessObject;
import entity.Message;
import interface_adapter.search_messages.SearchMessagesController;
import interface_adapter.search_messages.SearchMessagesPresenter;
import io.github.cdimascio.dotenv.Dotenv;
import org.bson.Document;
import use_case.search_messages.SearchMessagesInteractor;
import use_case.search_messages.SearchMessagesUserDataAccessInterface;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SearchView extends JFrame {

    private final JTextField searchField;
    private final JButton searchButton;
    private final SearchMessagesController controller;

    public SearchView() {
        setTitle("Search View");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Initialize components
        searchField = new JTextField(20);
        searchButton = new JButton("Search");

        // Setup layout
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.add(searchField, BorderLayout.CENTER);
        panel.add(searchButton, BorderLayout.EAST);
        add(panel, BorderLayout.NORTH);

        JLabel resultLabel = new JLabel("Enter your search query above and click Search.");
        add(resultLabel, BorderLayout.CENTER);

        // Create presenter and interactor
        SearchMessagesPresenter presenter = new SearchMessagesPresenter(this);
        SearchMessagesUserDataAccessInterface dataAccess = new MessageDataAccessObject();
        SearchMessagesInteractor interactor = new SearchMessagesInteractor(dataAccess, presenter);
        controller = new SearchMessagesController(interactor);

        // Add action listener to search button
        searchButton.addActionListener(e -> {
            String query = searchField.getText();
            controller.handleSearch(query);
        });

        setLocationRelativeTo(null);
    }

    public void showResults(String results) {
        JOptionPane.showMessageDialog(this, results, "Search Results", JOptionPane.INFORMATION_MESSAGE);
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Info", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SearchView().setVisible(true));
    }
}



