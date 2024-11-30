package use_case.fetch_friends;

/**
 * Defines the input boundary for the Fetch Friends use case.
 * Acts as an interface for initiating the fetch friends operation with the required input data.
 */
public interface FetchFriendsInputBoundary {

    /**
     * Executes the Fetch Friends use case.
     *
     * @param inputData the input data required to fetch friends, containing the necessary details (e.g., user ID).
     *                  Must not be null.
     */
    void execute(FetchFriendsInputData inputData);
}
