package use_case.add_friend;

import data_access.InMemoryUserDataAccessObject;
import entity.CommonUserFactory;
import entity.User;
import entity.UserFactory;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AddFriendInteractorTest {

    @Test
    void successAddFriendTest() {
        AddFriendInputData inputData = new AddFriendInputData("test12", "test13");
        AddFriendUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();

        // Add users to the repository.
        UserFactory factory = new CommonUserFactory();
        ArrayList<String> friendstest12 = new ArrayList<>();
        ArrayList<String> friendstest13 = new ArrayList<>();
        User test12 = factory.create("test12", "password", "en", friendstest12);
        User test13 = factory.create("test13", "password", "en", friendstest13);
        userRepository.save(test12);
        userRepository.save(test13);

        // Create a success presenter for the test.
        AddFriendOutputBoundary successPresenter = new AddFriendOutputBoundary() {
            @Override
            public void prepareSuccessView(AddFriendOutputData outputData) {
                assertFalse(outputData.isUseCaseFailed());
                assertEquals("test13 successfully added as your friend!", outputData.getFriendUsername());
            }

            @Override
            public void prepareFailView(AddFriendOutputData outputData) {
                fail("Use case failure is unexpected.");
            }
        };

        AddFriendInputBoundary interactor = new AddFriendInteractor(successPresenter, userRepository);
        interactor.execute(inputData);
        assertTrue(userRepository.getFriendsList("test12").contains("test13"));
    }

    @Test
    void failureFriendDoesNotExistTest() {
        AddFriendInputData inputData = new AddFriendInputData("test10", "NonExistentUser");
        AddFriendUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();

        // Add only test10 to the repository.
        UserFactory factory = new CommonUserFactory();
        ArrayList<String> friends = new ArrayList<>();
        User test10 = factory.create("test10", "password", "en", friends);
        userRepository.save(test10);

        // Create a failure presenter for the test.
        AddFriendOutputBoundary failurePresenter = new AddFriendOutputBoundary() {
            @Override
            public void prepareSuccessView(AddFriendOutputData outputData) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(AddFriendOutputData outputData) {
                assertTrue(outputData.isUseCaseFailed());
                assertEquals("NonExistentUser: Account does not exist.", outputData.getFriendUsername());
            }
        };

        AddFriendInputBoundary interactor = new AddFriendInteractor(failurePresenter, userRepository);
        interactor.execute(inputData);
    }

    @Test
    void failureSelfAddTest() {
        AddFriendInputData inputData = new AddFriendInputData("test10", "test10");
        AddFriendUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();

        // Add test10 to the repository.
        UserFactory factory = new CommonUserFactory();
        ArrayList<String> friends = new ArrayList<>();
        User test10 = factory.create("test10", "password", "en", friends);
        userRepository.save(test10);

        // Create a failure presenter for the test.
        AddFriendOutputBoundary failurePresenter = new AddFriendOutputBoundary() {
            @Override
            public void prepareSuccessView(AddFriendOutputData outputData) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(AddFriendOutputData outputData) {
                assertTrue(outputData.isUseCaseFailed());
                assertEquals("You cannot add yourself as a friend.", outputData.getFriendUsername());
            }
        };

        AddFriendInputBoundary interactor = new AddFriendInteractor(failurePresenter, userRepository);
        interactor.execute(inputData);
    }

    @Test
    void failureAlreadyFriendTest() {
        AddFriendInputData inputData = new AddFriendInputData("test10", "test11");
        AddFriendUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();

        // Add users and establish an existing friendship.
        UserFactory factory = new CommonUserFactory();
        ArrayList<String> friendstest10 = new ArrayList<>();
        ArrayList<String> friendstest11 = new ArrayList<>();
        User test10 = factory.create("test10", "password", "en", friendstest10);
        User test11 = factory.create("test11", "password", "en", friendstest11);
        userRepository.save(test10);
        userRepository.save(test11);
        userRepository.addFriend("test10", "test11");

        // Create a failure presenter for the test.
        AddFriendOutputBoundary failurePresenter = new AddFriendOutputBoundary() {
            @Override
            public void prepareSuccessView(AddFriendOutputData outputData) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(AddFriendOutputData outputData) {
                assertTrue(outputData.isUseCaseFailed());
                assertEquals("You have already added test11 as your friend.", outputData.getFriendUsername());
            }
        };

        AddFriendInputBoundary interactor = new AddFriendInteractor(failurePresenter, userRepository);
        interactor.execute(inputData);
    }
}