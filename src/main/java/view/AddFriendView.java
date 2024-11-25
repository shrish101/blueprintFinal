package view;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import interface_adapter.ViewManagerModel;
import interface_adapter.addFriend.AddFriendController;
import interface_adapter.addFriend.AddFriendState;
import interface_adapter.addFriend.AddFriendViewModel;
import interface_adapter.change_password.LoggedInState;
import interface_adapter.change_password.LoggedInViewModel;
import interface_adapter.logout.LogoutController;

/**
 * The AddFriendView class is a JPanel that allows users to add a friend by entering their username.
 * It provides a user interface with fields for inputting the friend's username, displaying error
 * and success messages, and navigating to the main screen.
 * It interacts with the AddFriendViewModel to handle the state and business logic.
 */
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

    public AddFriendView(AddFriendViewModel addFriendViewModel, ViewManagerModel viewManagerModel,
                         LoggedInViewModel loggedInViewModel) {
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
            final LoggedInView loggedInView = new LoggedInView(loggedInViewModel, viewManagerModel);
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

    /**
     * Handles the action performed event when a button is clicked.
     *
     * @param evt The ActionEvent triggered by a button click.
     */
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        friendUsernameErrorField.setText("");
        friendUsernameSuccessLabel.setText("");

        final AddFriendState state = (AddFriendState) evt.getNewValue();
        setFields(state);

        if (!state.getErrorMessage().isEmpty()) {
            friendUsernameErrorField.setText(state.getErrorMessage());
        }
        else {
            friendUsernameSuccessLabel.setText(state.getSuccessMessage());
        }
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
