package view;

import java.awt.Component;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import data_access.MessageDataAccessObject;
import entity.CommonMessage;
import entity.Message;
import interface_adapter.ViewManagerModel;
import interface_adapter.addFriend.AddFriendController;
import interface_adapter.addFriend.AddFriendViewModel;
import interface_adapter.change_password.ChangePasswordController;
import interface_adapter.change_password.LoggedInState;
import interface_adapter.change_password.LoggedInViewModel;
import interface_adapter.edit_message.EditMessageController;
import interface_adapter.logout.LogoutController;

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
    private final int magic10 = 10;
    private final int magic30 = 30;

    private final JLabel username;

    private final JButton logOut;
    private final JButton sync;
    private final JButton search;
    private final JButton editMessageButton;

    private final JButton addFriend;

    private final JTextField passwordInputField = new JTextField(15);
    private final JButton changePassword;

    private final JTextArea chatArea;
    private final JTextField chatInputField;

    private final MessageDataAccessObject messageDataAccessObject;
    private EditMessageController editMessageController;
    private final ViewManagerModel viewManagerModel;

    public LoggedInView(LoggedInViewModel loggedInViewModel, ViewManagerModel viewManagerModel) {
        this.loggedInViewModel = loggedInViewModel;
        this.loggedInViewModel.addPropertyChangeListener(this);
        this.viewManagerModel = viewManagerModel;
        this.messageDataAccessObject = new MessageDataAccessObject();

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
        buttons.add(sync);

        search = new JButton("Search");

        editMessageButton = new JButton("Edit Message");
        buttons.add(editMessageButton);
        addFriend = new JButton("Add Friend");

        chatArea = new JTextArea(magic10, magic30);
        chatArea.setEditable(false);

        final JScrollPane chatScrollPane = new JScrollPane(chatArea);

        chatInputField = new JTextField(magic30);

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
            final AddFriendViewModel addFriendViewModel = new AddFriendViewModel();
            final AddFriendView addFriendView = new AddFriendView(addFriendViewModel, viewManagerModel,
                    loggedInViewModel);
            addFriendView.setAddFriendController(addFriendController);
            addFriendView.setVisible(true);
            viewManagerModel.setState("add friend");
            viewManagerModel.firePropertyChanged();
        });

        changePassword.addActionListener(
                evt -> {
                    if (evt.getSource().equals(changePassword)) {
                        final LoggedInState currentState = loggedInViewModel.getState();
                        this.changePasswordController.execute(
                                currentState.getUsername(), currentState.getPassword(),
                                currentState.getLanguage(),
                                currentState.getFriends()
                        );
                    }
                }
        );

        logOut.addActionListener(
                evt -> {
                    if (evt.getSource().equals(logOut)) {
                        // TODO: execute the logout use case through the Controller
                        // 1. get the state out of the loggedInViewModel. It contains the username.
                        // 2. Execute the logout Controller.
                    }
                }
        );

        chatInputField.addActionListener(evt -> {
            final String messageText = chatInputField.getText();
            final String usern = loggedInViewModel.getState().getUsername();

            final Message message = new CommonMessage(usern, "recipient", messageText, messageText);
            messageDataAccessObject.saveMessage(message);

            chatArea.append("You: " + messageText + "\n");
            chatInputField.setText("");
        });

        // EDITTING MESSAGE
        editMessageButton.addActionListener(
                evt -> {
                    if (evt.getSource().equals(editMessageButton)) {
                        final LoggedInState currentState = loggedInViewModel.getState();

                        final EditView editView = new EditView(currentState.getUsername());
                        editView.setEditMessageController(editMessageController);
                        editView.setVisible(true);
                    }
                }
        );

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

    public void setEditMessageController(EditMessageController controller) {
        this.editMessageController = controller;
    }

    /**
     * Sets the controller responsible for handling the logout action.
     *
     * @param logoutController The controller that manages the logout functionality.
     */
    public void setLogoutController(LogoutController logoutController) {
        // TODO: save the logout controller in the instance variable.
    }
}
