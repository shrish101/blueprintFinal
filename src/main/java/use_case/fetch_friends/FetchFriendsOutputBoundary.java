package use_case.fetch_friends;

/**
 * The Output Boundary for the Fetch Friend Use Case.
 */
public interface FetchFriendsOutputBoundary {

    /**
     * Prepares the view for a successful fetch friends operation.
     *
     * @param outputDat the data containing the list of friends and other relevant information to be displayed.
     *                   Must not be null.
     */
    void prepareSuccessView(FetchFriendsOutputData outputDat);

    /**
     * Prepares the view for a failed fetch friends operation.
     *
     * @param error the error message describing the reason for the failure. Must not be null or empty.
     */
    void prepareFailView(String error);
}
