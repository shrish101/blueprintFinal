package view;

import javax.swing.*;
import java.awt.*;

public class SearchView extends JFrame {

    private JTextField searchField;
    private JButton searchButton;

    public SearchView() {
        setTitle("Search View");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

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
            JOptionPane.showMessageDialog(this, "Searching for: " + query);
        });

        setLocationRelativeTo(null);
    }
}
