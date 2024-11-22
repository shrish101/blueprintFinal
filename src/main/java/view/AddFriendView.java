package view;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import interface_adapter.addFriend.AddFriendController;
import interface_adapter.addFriend.AddFriendState;
import interface_adapter.addFriend.AddFriendViewModel;

/**
 * The View for the Add Friend Use Case.
 */
public class AddFriendView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName = "add friend";

    private final AddFriendViewModel addFriendViewModel;
    private final JTextField usernameInputField = new JTextField(15);
    private AddFriendController addFriendController;

    private final JButton addFriendButton;

    public AddFriendView(AddFriendViewModel addFriendViewModel) {
        this.addFriendViewModel = addFriendViewModel;
        this.addFriendViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel(AddFriendViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Input fields and labels
        final LabelTextPanel usernameInfo = new LabelTextPanel(new JLabel(AddFriendViewModel.USERNAME_LABEL), usernameInputField);

        // Add Friend Button
        final JPanel buttons = new JPanel();
        addFriendButton = new JButton(AddFriendViewModel.ADD_FRIEND_BUTTON_LABEL);
        buttons.add(addFriendButton);

        addFriendButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (evt.getSource().equals(addFriendButton)) {
                    final AddFriendState currentState = addFriendViewModel.getAddFriendState();
                    final String username = currentState.getCurrentUsername();
                    final String friend = currentState.getFriendUsername();
                    if (username != null && !username.isEmpty()) {
                        addFriendController.addFriend(username, friend);
                    } else {
                        displayErrorMessage("Please enter a valid username.");
                    }
                }
            }
        });

        addUsernameListener();

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(usernameInfo);
        this.add(buttons);
    }

    private void addUsernameListener() {
        usernameInputField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                documentListenerHelper();
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        JOptionPane.showMessageDialog(this, "Action not implemented yet.");
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final AddFriendState state = (AddFriendState) evt.getNewValue();
        if (state.getUsernameError() != null) {
            JOptionPane.showMessageDialog(this, state.getUsernameError());
        }
        if (state.getMessage() != null) {
            JOptionPane.showMessageDialog(this, state.getMessage());
        }
    }

    public String getViewName() {
        return viewName;
    }

    public void setAddFriendController(AddFriendController controller) {
        this.addFriendController = controller;
    }

    public void displaySuccessMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public void displayErrorMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}
