package use_case.search_messages;

import entity.Message;
import entity.CommonMessage;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SearchMessagesInteractorTest {

    @Test
    void testSearchMessagesWithResults() {
        // Create a fake data access layer with sample messages
        SearchMessagesUserDataAccessInterface fakeDataAccess = new FakeDataAccess();
        FakeOutputBoundary fakePresenter = new FakeOutputBoundary();

        // Instantiate the interactor with the fake data access and presenter
        SearchMessagesInteractor interactor = new SearchMessagesInteractor(fakeDataAccess, fakePresenter);

        // Input data for searching messages
        SearchMessagesInputData inputData = new SearchMessagesInputData("hello");

        // Execute the interactor
        interactor.execute(inputData);

        // Verify the output
        assertTrue(fakePresenter.outputData.hasResults());
        assertEquals(1, fakePresenter.outputData.getResults().size());
        assertEquals("Message: Hello world", fakePresenter.outputData.getResults().get(0));
    }

    @Test
    void testSearchMessagesWithNoResults() {
        // Create a fake data access layer with sample messages
        SearchMessagesUserDataAccessInterface fakeDataAccess = new FakeDataAccess();
        FakeOutputBoundary fakePresenter = new FakeOutputBoundary();

        // Instantiate the interactor with the fake data access and presenter
        SearchMessagesInteractor interactor = new SearchMessagesInteractor(fakeDataAccess, fakePresenter);

        // Input data for searching messages
        SearchMessagesInputData inputData = new SearchMessagesInputData("nonexistent");

        // Execute the interactor
        interactor.execute(inputData);

        // Verify the output
        assertFalse(fakePresenter.outputData.hasResults());
        assertTrue(fakePresenter.outputData.getResults().isEmpty());
    }

    @Test
    void testSearchMessagesWithError() {
        // Create a fake data access layer that throws an exception
        SearchMessagesUserDataAccessInterface fakeDataAccess = new FakeErrorDataAccess();
        FakeOutputBoundary fakePresenter = new FakeOutputBoundary();

        // Instantiate the interactor with the fake data access and presenter
        SearchMessagesInteractor interactor = new SearchMessagesInteractor(fakeDataAccess, fakePresenter);

        // Input data for searching messages
        SearchMessagesInputData inputData = new SearchMessagesInputData("any query");

        // Execute the interactor
        interactor.execute(inputData);

        // Verify the output
        assertEquals("Error connecting to database: Simulated database error", fakePresenter.error);
    }

    // Fake data access class for testing
    static class FakeDataAccess implements SearchMessagesUserDataAccessInterface {
        @Override
        public List<Message> getAllMessages() {
            List<Message> messages = new ArrayList<>();
            messages.add(new CommonMessage("user1", "user2", "Hello world", "Bonjour le monde", "en"));
            messages.add(new CommonMessage("user2", "user1", "Goodbye world", "Au revoir le monde", "en"));
            return messages;
        }
    }

    // Fake data access class that simulates an error
    static class FakeErrorDataAccess implements SearchMessagesUserDataAccessInterface {
        @Override
        public List<Message> getAllMessages() throws Exception {
            throw new Exception("Simulated database error");
        }
    }

    // Fake presenter class for testing
    static class FakeOutputBoundary implements SearchMessagesOutputBoundary {
        SearchMessagesOutputData outputData;
        String error;

        @Override
        public void presentSearchResults(SearchMessagesOutputData outputData) {
            this.outputData = outputData;
        }

        @Override
        public void presentError(String error) {
            this.error = error;
        }
    }
}
