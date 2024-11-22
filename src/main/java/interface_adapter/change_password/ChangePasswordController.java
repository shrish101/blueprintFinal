package interface_adapter.change_password;

import use_case.change_password.ChangePasswordInputBoundary;
import use_case.change_password.ChangePasswordInputData;
import java.util.List;

/**
 * Controller for the Change Password Use Case.
 */
public class ChangePasswordController {
    private final ChangePasswordInputBoundary userChangePasswordUseCaseInteractor;

    public ChangePasswordController(ChangePasswordInputBoundary userChangePasswordUseCaseInteractor) {
        this.userChangePasswordUseCaseInteractor = userChangePasswordUseCaseInteractor;
    }

    /**
     * Executes the Change Password Use Case.
     * @param password the new password
     * @param username the user whose password to change
     * @param language the user's language
     * @param friends the list of the user's friends
     */
    public void execute(String password, String username, String language, List<String> friends) {
        // Pass all the required data, including the friends list
        final ChangePasswordInputData changePasswordInputData = new ChangePasswordInputData(password, username, language, friends);

        // Execute the use case
        userChangePasswordUseCaseInteractor.execute(changePasswordInputData);
    }
}