package interface_adapter.addFriend;

import use_case.add_friend.AddFriendInputBoundary;
import use_case.add_friend.AddFriendInputData;
import use_case.add_friend.AddFriendOutputData;
import use_case.add_friend.AddFriendOutputBoundary;

public class AddFriendController {

    private final AddFriendInputBoundary addFriendInputBoundary;

    public AddFriendController(AddFriendInputBoundary addFriendInputBoundary) {
        this.addFriendInputBoundary = addFriendInputBoundary;
    }

    public void execute(String username, String friendUsername) {
        AddFriendInputData inputData = new AddFriendInputData(username, friendUsername);
        System.out.println("funny");
        addFriendInputBoundary.execute(inputData);
    }
}