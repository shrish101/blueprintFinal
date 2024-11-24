package view;

import data_access.MessageDataAccessObject;
import entity.Message;
import io.github.cdimascio.dotenv.Dotenv;
import org.bson.Document;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SearchView extends JFrame {

    private final MessageDataAccessObject messageDataAccessObject;

    private final JTextField searchField;
    private final JButton searchButton;

    public SearchView() {
        setTitle("Search View");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Initialize the MessageDataAccessObject to handle MongoDB operations
        messageDataAccessObject = new MessageDataAccessObject();

        // Create a panel for the input and button
        final JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(10, 10));

        // Create and add the text field
        searchField = new JTextField(20);
        panel.add(searchField, BorderLayout.CENTER);

        // Create and add the search button
        searchButton = new JButton("Search");
        panel.add(searchButton, BorderLayout.EAST);

        // Add the panel to the frame
        add(panel, BorderLayout.NORTH);

        // Add a label or area for results (optional)
        final JLabel resultLabel = new JLabel("Enter your search query above and click Search.");
        add(resultLabel, BorderLayout.CENTER);

        // Add ActionListener to handle search
        searchButton.addActionListener(e -> {
            final String query = searchField.getText();
            if (query.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Search query cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                List<String> results = searchMessages(query);
                if (results.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "No messages found for: " + query, "No Results", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    StringBuilder resultString = new StringBuilder("Results:\n");
                    for (String result : results) {
                        resultString.append(result).append("\n");
                    }
                    JOptionPane.showMessageDialog(this, resultString.toString(), "Search Results", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        setLocationRelativeTo(null);
    }

    // Method to search messages in MongoDB using MessageDataAccessObject
    private List<String> searchMessages(String keyword) {
        List<String> results = new ArrayList<>();

        try {
            // Get all messages containing the keyword in the translated message field
            List<Message> allMessages = messageDataAccessObject.getAllMessages();
            for (Message message : allMessages) {
                if (message.getTranslatedContent().toLowerCase().contains(keyword.toLowerCase())) {
                    String sender = message.getSender();
                    String recipient = message.getRecipient();
                    String originalMessage = message.getOriginalLanguage();
                    String translatedMessage = message.getTranslatedContent();

                    results.add(String.format("From: %s, To: %s, Message: %s, Translation: %s", sender, recipient, originalMessage, translatedMessage));
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error connecting to database: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
        return results;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SearchView().setVisible(true));
    }
}


