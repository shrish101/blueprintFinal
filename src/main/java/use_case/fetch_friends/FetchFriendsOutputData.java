package use_case.fetch_friends;

import java.util.List;

public class FetchFriendsOutputData {
    private final List<String> friends;

    public FetchFriendsOutputData(List<String> friends) {
        this.friends = friends;
    }

    public List<String> getFriends() {
        return friends;
    }
}