package use_case.add_friend;

import entity.User;

public interface AddFriendUserDataAccessInterface {

    boolean existsByName(String friendUsername);

    boolean addFriend(String username, String friendUsername);
}