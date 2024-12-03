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
        SearchMessagesUserDataAccessInterface fakeDataAccess = new FakeDataAccess();
        FakeOutputBoundary fakePresenter = new FakeOutputBoundary();

        SearchMessagesInteractor interactor = new SearchMessagesInteractor(fakeDataAccess, fakePresenter);

        SearchMessagesInputData inputData = new SearchMessagesInputData("hello");

        interactor.execute(inputData);

        assertTrue(fakePresenter.outputData.hasResults());
        assertEquals(1, fakePresenter.outputData.getResults().size());
        assertEquals("Message: Hello world", fakePresenter.outputData.getResults().get(0));
    }

    @Test
    void testSearchMessagesWithNoResults() {
        SearchMessagesUserDataAccessInterface fakeDataAccess = new FakeDataAccess();
        FakeOutputBoundary fakePresenter = new FakeOutputBoundary();

        SearchMessagesInteractor interactor = new SearchMessagesInteractor(fakeDataAccess, fakePresenter);

        SearchMessagesInputData inputData = new SearchMessagesInputData("nonexistent");

        interactor.execute(inputData);

        assertFalse(fakePresenter.outputData.hasResults());
        assertTrue(fakePresenter.outputData.getResults().isEmpty());
    }

    @Test
    void testSearchMessagesWithError() {
        SearchMessagesUserDataAccessInterface fakeDataAccess = new FakeErrorDataAccess();
        FakeOutputBoundary fakePresenter = new FakeOutputBoundary();

        SearchMessagesInteractor interactor = new SearchMessagesInteractor(fakeDataAccess, fakePresenter);

        SearchMessagesInputData inputData = new SearchMessagesInputData("any query");

        interactor.execute(inputData);

        assertEquals("Error connecting to database: Simulated database error", fakePresenter.error);
    }

    static class FakeDataAccess implements SearchMessagesUserDataAccessInterface {
        @Override
        public List<Message> getAllMessages() {
            List<Message> messages = new ArrayList<>();
            messages.add(new CommonMessage("user1", "user2", "Hello world", "Bonjour le monde", "en"));
            messages.add(new CommonMessage("user2", "user1", "Goodbye world", "Au revoir le monde", "en"));
            return messages;
        }
    }

    static class FakeErrorDataAccess implements SearchMessagesUserDataAccessInterface {
        @Override
        public List<Message> getAllMessages() throws Exception {
            throw new Exception("Simulated database error");
        }
    }

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
