package use_case.fetch_friends;

/**
 * The Output Boundary for the Fetch Friend Use Case.
 */
public interface FetchFriendsOutputBoundary {
    void prepareSuccessView(FetchFriendsOutputData outputDat);
    void prepareFailView(String error);
}
