package use_case.add_friend;

import entity.User;

public interface AddFriendUserDataAccessInterface {

    boolean isUserExist(String username);

    boolean addFriend(String username, String friendUsername);
}