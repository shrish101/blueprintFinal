package use_case.fetch_friends;

import entity.User;
import java.util.List;

public class FetchFriendsOutputData {
    private final List<User> friends;

    public FetchFriendsOutputData(List<User> friends) {
        this.friends = friends;
    }

    public List<String> getFriends() {
        return friends;
    }
}