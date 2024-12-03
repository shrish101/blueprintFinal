package use_case.edit_message;

import data_access.MessageDataAccessObject;
import entity.CommonMessage;
import entity.Message;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EditInteractorTest {

    @Test
    void successTest() {
        // Use EditUserDataAccessInterface type for test
        MessageDataAccessObject fakeDataAccess = new MessageDataAccessObject();
        FakeOutputBoundary fakePresenter = new FakeOutputBoundary();

        // Input data for editing message
        EditInputData inputData = new EditInputData("shrish", "heyy", "Girik1");
        EditInteractor interactor = new EditInteractor(fakeDataAccess, fakePresenter);
        interactor.execute(inputData);

        assertFalse(fakePresenter.outputData.isUseCaseFailed(), "Message update should be successful.");
        assertEquals("Message updated successfully.", fakePresenter.outputData.getMessage());
    }

    @Test
    void failureTestNoMessagesFound() {
        MessageDataAccessObject fakeDataAccess = new MessageDataAccessObject();
        FakeOutputBoundary fakePresenter = new FakeOutputBoundary();

        EditInputData inputData = new EditInputData("user1", "New content", "user2");
        EditInteractor interactor = new EditInteractor(fakeDataAccess, fakePresenter);
        interactor.execute(inputData);

        assertTrue(fakePresenter.outputData.isUseCaseFailed(), "Message update should fail when no message is found.");
        assertEquals("No message found for the user.", fakePresenter.outputData.getMessage());
    }

    @Test
    void failureTestNullContent() {
        MessageDataAccessObject fakeDataAccess = new MessageDataAccessObject();
        FakeOutputBoundary fakePresenter = new FakeOutputBoundary();

        EditInputData inputData = new EditInputData("user1", null, "user2");
        EditInteractor interactor = new EditInteractor(fakeDataAccess, fakePresenter);

        interactor.execute(inputData);

        assertTrue(fakePresenter.outputData.isUseCaseFailed(), "Message update should fail with null content.");
        assertEquals("No message found for the user.", fakePresenter.outputData.getMessage());
    }

    @Test
    void failureTestMessageUpdateError() {
        MessageDataAccessObject fakeDataAccess = new MessageDataAccessObject();
        FakeOutputBoundary fakePresenter = new FakeOutputBoundary();

        EditInputData inputData = new EditInputData("user1", "New content", "user2");

        EditInteractor interactor = new EditInteractor(fakeDataAccess, fakePresenter);

        interactor.execute(inputData);

        assertTrue(fakePresenter.outputData.isUseCaseFailed(), "Message update should fail when an error occurs.");
        assertEquals("No message found for the user.", fakePresenter.outputData.getMessage());
    }

    static class FakeDataAccess implements EditUserDataAccessInterface {
        @Override
        public Message getLatestMessageByUser(String user1, String user2) {
            return new CommonMessage(user1, user2, "Old content", "Translated content", "en");
        }

        @Override
        public void updateMessage(String user1, String user2, String newOriginalMessage) {
        }
    }

    static class FakeNoMessagesDataAccess implements EditUserDataAccessInterface {
        @Override
        public Message getLatestMessageByUser(String user1, String user2) {
            return null;
        }

        @Override
        public void updateMessage(String user1, String user2, String newOriginalMessage) {
        }
    }

    static class FakeErrorDataAccess implements EditUserDataAccessInterface {
        @Override
        public Message getLatestMessageByUser(String user1, String user2) {
            return new CommonMessage(user1, user2, "Old content", "Translated content", "en");
        }

        @Override
        public void updateMessage(String user1, String user2, String newOriginalMessage) {
            throw new RuntimeException("Simulated update error");
        }
    }

    static class FakeOutputBoundary implements EditOutputBoundry {
        EditOutputData outputData;

        @Override
        public void prepareSuccessView(EditOutputData outputData) {
            this.outputData = outputData;
        }

        @Override
        public void prepareFailView(String errorMessage) {
            this.outputData = new EditOutputData(errorMessage, true);
        }
    }
}
