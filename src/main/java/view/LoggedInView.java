package view;

import java.awt.Component;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import entity.User;
import interface_adapter.ViewManagerModel;
import interface_adapter.addFriend.*;
import interface_adapter.change_password.ChangePasswordController;
import interface_adapter.change_password.LoggedInState;
import interface_adapter.change_password.LoggedInViewModel;
import interface_adapter.logout.LogoutController;
import use_case.add_friend.*;

/**
 * The View for when the user is logged into the program.
 */
public class LoggedInView extends JPanel implements PropertyChangeListener {

    private final String viewName = "logged in";
    private final LoggedInViewModel loggedInViewModel;
    private final JLabel passwordErrorField = new JLabel();
    private ChangePasswordController changePasswordController;
    private AddFriendController addFriendController;
    private LogoutController logoutController;

    private final JLabel username;

    private final JButton logOut;

    private final JButton sync;

    private final JButton search;

    private final JButton addFriend;

    private final JTextField passwordInputField = new JTextField(15);
    private final JButton changePassword;

    private final JTextArea chatArea;
    private final JTextField chatInputField;

    private final ViewManagerModel viewManagerModel;

    public LoggedInView(LoggedInViewModel loggedInViewModel, ViewManagerModel viewManagerModel) {
        this.loggedInViewModel = loggedInViewModel;
        this.loggedInViewModel.addPropertyChangeListener(this);
        this.viewManagerModel = viewManagerModel;

        final JLabel title = new JLabel("Logged In Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final LabelTextPanel passwordInfo = new LabelTextPanel(
                new JLabel("Password"), passwordInputField);

        final JLabel usernameInfo = new JLabel("Currently logged in: ");
        username = new JLabel();

        final JPanel buttons = new JPanel();
        logOut = new JButton("Log Out");
        buttons.add(logOut);

        changePassword = new JButton("Change Password");
        buttons.add(changePassword);

        sync = new JButton("Sync");
        buttons.add(changePassword);

        search = new JButton("Search");

        addFriend = new JButton("Add Friend");

        chatArea = new JTextArea(10, 30);
        chatArea.setEditable(false);

        final JScrollPane chatScrollPane = new JScrollPane(chatArea);

        chatInputField = new JTextField(30);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        passwordInputField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final LoggedInState currentState = loggedInViewModel.getState();
                currentState.setPassword(passwordInputField.getText());
                loggedInViewModel.setState(currentState);
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

        sync.addActionListener(
                evt -> {
                    System.out.println("Retrieving Data...");
                }
        );

        search.addActionListener(evt -> {
            final SearchView searchView = new SearchView();
            searchView.setVisible(true);
        });

        addFriend.addActionListener(evt -> {
            // final AddFriendState currentState = addFriendViewModel.getState();
            // String friendUsername = currentState.getFriendUsername(); // Assuming this is the friend's username input
            // if (friendUsername != null && !friendUsername.trim().isEmpty()) {
            //    addFriendViewModel.addFriend(friendUsername); // Call to add friend through the ViewModel
            // } else {
            //    JOptionPane.showMessageDialog(null, "Please enter a valid username to add as a friend.");
            // }
            AddFriendViewModel addFriendViewModel = new AddFriendViewModel();
            AddFriendView addFriendView = new AddFriendView(addFriendViewModel);
            addFriendView.setAddFriendController(addFriendController);
            addFriendView.setVisible(true);
            viewManagerModel.setState("add friend");
            viewManagerModel.firePropertyChanged();
        });

        changePassword.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                evt -> {
                    if (evt.getSource().equals(changePassword)) {
                        final LoggedInState currentState = loggedInViewModel.getState();

                        this.changePasswordController.execute(
                                currentState.getUsername(),
                                currentState.getPassword(),
                                currentState.getLanguage(),
                                currentState.getFriends()
                        );
                    }
                }
        );

        logOut.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                evt -> {
                    if (evt.getSource().equals(logOut)) {
                        // TODO: execute the logout use case through the Controller
                        // 1. get the state out of the loggedInViewModel. It contains the username.
                        // 2. Execute the logout Controller.
                    }
                }
        );

        chatInputField.addActionListener(evt -> {
            final String message = chatInputField.getText();
            chatArea.append("You: " + message + "\n");
            chatInputField.setText("");
        });

        this.add(title);
        this.add(usernameInfo);
        this.add(username);

        this.add(passwordInfo);
        this.add(passwordErrorField);
        this.add(buttons);
        this.add(sync);
        this.add(search);
        this.add(addFriend);

        this.add(new JLabel("Chat Area:"));
        this.add(chatScrollPane);
        this.add(chatInputField);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("state")) {
            final LoggedInState state = (LoggedInState) evt.getNewValue();
            username.setText(state.getUsername());
        }
        else if (evt.getPropertyName().equals("password")) {
            final LoggedInState state = (LoggedInState) evt.getNewValue();
            JOptionPane.showMessageDialog(null, "password updated for " + state.getUsername());
        }

    }

    public String getViewName() {
        return viewName;
    }

    public void setChangePasswordController(ChangePasswordController changePasswordController) {
        this.changePasswordController = changePasswordController;
    }

    public void setLogoutController(LogoutController logoutController) {
        // TODO: save the logout controller in the instance variable.
    }
}
