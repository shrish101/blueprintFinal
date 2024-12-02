package view;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import data_access.InMemoryUserDataAccessObject;
import data_access.MessageDataAccessObject;
import entity.CommonMessage;
import entity.Message;
import interface_adapter.ViewManagerModel;
import interface_adapter.add_friend.AddFriendController;
import interface_adapter.add_friend.AddFriendViewModel;
import interface_adapter.change_password.ChangePasswordController;
import interface_adapter.change_password.LoggedInState;
import interface_adapter.change_password.LoggedInViewModel;
import interface_adapter.edit_message.EditMessageController;
import interface_adapter.logout.LogoutController;
import interface_adapter.signup.SignupViewModel;

/**
 * The View for when the user is logged into the program.
 */
public class LoggedInView extends JPanel implements PropertyChangeListener {
    private final String viewName = "logged in";
    private final String newline = "\\n";
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
    private final InMemoryUserDataAccessObject inMemoryUserDataAccessObject;
    private final MessageDataAccessObject messageDataAccessObject;
    private EditMessageController editMessageController;
    private final ViewManagerModel viewManagerModel;
    private final JComboBox<String> friends;
    private String selectedFriend;

    public LoggedInView(LoggedInViewModel loggedInViewModel, ViewManagerModel viewManagerModel) {
        this.loggedInViewModel = loggedInViewModel;
        this.loggedInViewModel.addPropertyChangeListener(this);
        this.viewManagerModel = viewManagerModel;
        this.messageDataAccessObject = new MessageDataAccessObject();
        this.inMemoryUserDataAccessObject = new InMemoryUserDataAccessObject();

        final JLabel title = new JLabel("Logged In Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        final LabelTextPanel passwordInfo = new LabelTextPanel(
                new JLabel("Password"), passwordInputField);
        final JLabel usernameInfo = new JLabel("Currently logged in: ");
        username = new JLabel();
        final JPanel buttons = new JPanel();
        logOut = new JButton("Log Out");
        buttons.add(logOut);

        friends = new JComboBox<>();

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

        final LabelTextPanel friends_info = new LabelTextPanel(
                new JLabel(SignupViewModel.LANGUAGE_LABEL), friends);

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
                    loadChat((String) friends.getSelectedItem());
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
        friends.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedFriend = (String) friends.getSelectedItem();
                final LoggedInState currentState = loggedInViewModel.getState();
                // currentState.setLanguage(currentState.getLanguage());
                loggedInViewModel.setState(currentState);
                // we need to update the reciever and send message to them...
                loadChat(selectedFriend);
            }
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
                        // 1. get the state out of the loggedInViewModel. It contains the username.
                        // 2. Execute the logout Controller.
                        final LoggedInState currentState = loggedInViewModel.getState();
                        this.logoutController.execute(currentState.getUsername());
                    }
                }
        );

        chatInputField.addActionListener(evt -> {
            final String messageText = chatInputField.getText();
            final String usern = loggedInViewModel.getState().getUsername();
            final String recipientLanguage = loggedInViewModel.getState().getLanguage();
            final Message message = new CommonMessage(usern, selectedFriend,
                    messageText, messageText, recipientLanguage);
            messageDataAccessObject.saveMessage(message, recipientLanguage);
            chatArea.append("You: " + messageText + "\n");
            chatInputField.setText("");
        });

        editMessageButton.addActionListener(
                evt -> {
                    if (evt.getSource().equals(editMessageButton)) {
                        final LoggedInState currentState = loggedInViewModel.getState();
                        final EditView editView = new EditView(currentState.getUsername(), selectedFriend);
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
        this.add(new JLabel("Choose Friend"));
        this.add(friends);
    }

    private void updateFriendsList(List<String> friendsList) {
        friends.removeAllItems();
        for (String friend : friendsList) {
            friends.addItem(friend);
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("state")) {
            final LoggedInState state = (LoggedInState) evt.getNewValue();
            username.setText(state.getUsername());
            updateFriendsList(state.getFriends());
        }
        else if (evt.getPropertyName().equals("password")) {
            final LoggedInState state = (LoggedInState) evt.getNewValue();
            JOptionPane.showMessageDialog(null, "password updated for " + state.getUsername());
        }
    }

    private void loadChat(String friend) {
        chatArea.setText("");
        final List<Message> messageList = messageDataAccessObject.getMessageConversation(username.getText(), friend);
        for (Message message : messageList) {
            if (message.getSender().equals(username.getText())) {
                chatArea.append("You: " + message.getOriginalLanguage() + "\n");
            }
            else {
                final String lang = loggedInViewModel.getState().getLanguage();
                chatArea.append(friend + ": " + message.getTranslatedContent(lang) + "\n");
            }
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
        this.logoutController = logoutController;
    }
}
