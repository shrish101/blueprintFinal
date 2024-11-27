package use_case.fetch_friends;

import java.util.List;

/**
 * Represents the output data for the Fetch Friends use case.
 * Encapsulates the list of friends retrieved during the operation.
 */
public class FetchFriendsOutputData {
    private final List<String> friends;

    public FetchFriendsOutputData(List<String> friends) {
        this.friends = friends;
    }

    public List<String> getFriends() {
        return this.friends;
    }
}
