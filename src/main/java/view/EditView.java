package view;

import javax.swing.*;
import interface_adapter.edit_message.EditMessageController;

import java.awt.*;

public class EditView extends JFrame {

    private JTextArea messageTextArea;
    private JButton saveButton;
    private String currentUser;
    private EditMessageController editMessageController;

    public EditView(String currentUser) {
        this.currentUser = currentUser;

        setTitle("Edit Message");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        messageTextArea = new JTextArea("", 10, 30);
        JScrollPane scrollPane = new JScrollPane(messageTextArea);

        saveButton = new JButton("Save Changes");
        saveButton.addActionListener(evt -> saveChanges());

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(new JLabel("Edit your message:"), BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(saveButton, BorderLayout.SOUTH);

        add(panel);
    }

    public void setEditMessageController(EditMessageController controller) {
        this.editMessageController = controller;
    }

    private void saveChanges() {
        String updatedMessage = messageTextArea.getText();

        editMessageController.execute(currentUser, updatedMessage);

        dispose();
    }
}
