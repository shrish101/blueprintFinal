package interface_adapter.fetchFriend;

import use_case.fetch_friends.FetchFriendsOutputBoundary;
import use_case.fetch_friends.FetchFriendsOutputData;
import java.util.List;

public class FetchFriendPresenter implements FetchFriendsOutputBoundary {
    private List<String> friendsList;

    @Override
    public void presentFriends(FetchFriendsOutputData outputData) {
        this.friendsList = outputData.getFriends();
    }

    public List<String> getFriendsList() {
        return friendsList;
    }
}