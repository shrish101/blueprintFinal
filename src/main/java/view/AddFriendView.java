package view;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.*;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import interface_adapter.ViewManagerModel;
import interface_adapter.addFriend.AddFriendController;
import interface_adapter.addFriend.AddFriendState;
import interface_adapter.addFriend.AddFriendViewModel;
import interface_adapter.change_password.LoggedInState;
import interface_adapter.change_password.LoggedInViewModel;
import interface_adapter.logout.LogoutController;
import interface_adapter.change_password.LoggedInState;

public class AddFriendView extends JPanel implements ActionListener, PropertyChangeListener {

    private final String viewName = "add friend";
    private final AddFriendViewModel addFriendViewModel;
    private final LoggedInViewModel loggedInViewModel;

    private final JTextField friendUsernameInputField = new JTextField(15);
    private final JLabel friendUsernameErrorField = new JLabel();
    private final JLabel friendUsernameSuccessLabel = new JLabel();

    private final JButton addFriendButton;
    private final JButton homeScreenButton;
    private AddFriendController addFriendController;
    private LogoutController logoutController;

    private final ViewManagerModel viewManagerModel;

    public AddFriendView(AddFriendViewModel addFriendViewModel, ViewManagerModel viewManagerModel, LoggedInViewModel loggedInViewModel) {
        this.addFriendViewModel = addFriendViewModel;
        this.addFriendViewModel.addPropertyChangeListener(this);
        this.viewManagerModel = viewManagerModel;
        this.loggedInViewModel = loggedInViewModel;
        System.out.println("Hello");

        final JLabel title = new JLabel("Add Friend");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final LabelTextPanel friendUsernameInfo = new LabelTextPanel(
                new JLabel("Friend's Username"), friendUsernameInputField);

        final JPanel buttons = new JPanel();
        addFriendButton = new JButton("Add Friend");
        buttons.add(addFriendButton);
        homeScreenButton = new JButton("Main Screen");
        buttons.add(homeScreenButton);

        addFriendButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (evt.getSource().equals(addFriendButton)) {
                    final AddFriendState friendState = addFriendViewModel.getState();
                    final LoggedInState currentState = loggedInViewModel.getState();
                    addFriendController.execute(currentState.getUsername(), friendState.getFriendUsername());
                }
            }
        });

        homeScreenButton.addActionListener(evt -> {
            LoggedInView loggedInView = new LoggedInView(loggedInViewModel, viewManagerModel);
            loggedInView.setLogoutController(logoutController);
            loggedInView.setVisible(true);
            viewManagerModel.setState("logged in");
            viewManagerModel.firePropertyChanged();
        });
        friendUsernameInputField.getDocument().addDocumentListener(new DocumentListener() {
            private void documentListenerHelper() {
                final AddFriendState currentState = addFriendViewModel.getState();
                currentState.setFriendUsername(friendUsernameInputField.getText());
                addFriendViewModel.setState(currentState);
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

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(friendUsernameInfo);
        this.add(friendUsernameErrorField);
        this.add(friendUsernameSuccessLabel);
        this.add(buttons);
        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final AddFriendState state = (AddFriendState) evt.getNewValue();
        setFields(state);
        friendUsernameErrorField.setText(state.getErrorMessage());
        friendUsernameSuccessLabel.setText(state.getSuccessMessage());
    }

    private void setFields(AddFriendState state) {
        friendUsernameInputField.setText(state.getFriendUsername());
    }

    public String getViewName() {
        return viewName;
    }

    public void setAddFriendController(AddFriendController addFriendController) {
        this.addFriendController = addFriendController;
    }
}