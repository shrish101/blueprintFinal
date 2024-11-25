package view;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import interface_adapter.edit_message.EditMessageController;

/**
 * The EditView class represents a graphical user interface (GUI) for editing a message.
 * It provides a text area for the user to edit their message and a button to save the changes.
 * This view interacts with the EditMessageController to save the edited message when the user confirms the changes.
 */
public class EditView extends JFrame {

    private JTextArea messageTextArea;
    private JButton saveButton;
    private String currentUser;
    private EditMessageController editMessageController;
    private final int magic400 = 400;
    private final int magic300 = 300;
    private final int magic10 = 10;
    private final int magic30 = 30;

    public EditView(String currentUser) {
        this.currentUser = currentUser;

        setTitle("Edit Message");
        setSize(magic400, magic300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        messageTextArea = new JTextArea("", magic10, magic30);
        final JScrollPane scrollPane = new JScrollPane(messageTextArea);

        saveButton = new JButton("Save Changes");
        saveButton.addActionListener(evt -> saveChanges());

        final JPanel panel = new JPanel();
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
        final String updatedMessage = messageTextArea.getText();

        editMessageController.execute(currentUser, updatedMessage);

        dispose();
    }
}
