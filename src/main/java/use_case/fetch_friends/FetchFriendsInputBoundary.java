package use_case.fetch_friends;

public interface FetchFriendsInputBoundary {
    FetchFriendsOutputData execute(FetchFriendsInputData inputData);
}