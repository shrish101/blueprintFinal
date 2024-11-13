# Ginky and Friends Messaging App

## Project Overview

The Real-Time Messaging and Translation Application is designed to enable seamless multilingual communication through both individual and group chats. Utilizing a language translation API, this application translates messages, allowing users to send messages in their preferred language and have them translated automatically for recipients who speak different languages. This project also includes features for message editing, message search, and chat history storage, enhancing the user experience and promoting effective communication across language barriers. Additionally, the real-time aspect will be implemented through the use of a sync-button, which will be able to retrieve the updated messages/chat history from the database.

## User Stories

### Real-Time Messaging and Translation

**User Story**: Girik wants to chat with his friend Abdallah, who speaks Arabic. He sends a message in English, and the application translates it into Arabic in real-time so Abdallah can read it.

- **Assigned to**: Team
- **Use Case**: Users can send messages in their chosen language, and the app translates these messages into the recipient’s language.
- **Interactor**: Manages the sending and receiving of messages and initiates translation requests.
- **Controller**: Coordinates message flow and translation processes.
- **Presenter**: Displays translated messages in the recipient’s chat window.

### Group Chat with Translation

**User Story**: Abdallah creates a group chat with friends who speak various languages (e.g., Tamil, Hindi, Nepali). Each friend can communicate in their own language, and the app translates the messages for all members in the group.

- **Assigned to**: Team
- **Use Case**: Users can communicate in group chats where each member’s messages are automatically translated into the other members’ preferred languages.
- **Interactor**: Handles the real-time processing and translation of each group message.
- **Controller**: Manages the flow of messages within the group chat and processes translations for each participant.
- **Presenter**: Displays messages in each participant's language in the group chat window.

### Editing Messages

**User Story**: Shrish realizes he made a typo in his message and edits it. The app shows both the original and edited messages with timestamps.

- **Assigned to**: Shrish
- **Use Case**: Users can edit previously sent messages, with both the original and edited versions displayed, along with timestamps.
- **Interactor**: Detects the request to edit messages and captures the updated text.
- **Controller**: Stores and updates message data, handling the display of both original and edited versions.
- **Presenter**: Shows both the original and edited messages with timestamps in the chat window.

### Chat History Storage

**User Story**: Thanush closes the app and returns later, accessing his chat history to see previous conversations with translations and catch up on what he missed.

- **Assigned to**: Thanush
- **Use Case**: Users can access their chat history, which includes translated messages from previous conversations, allowing them to revisit and review past interactions.
- **Interactor**: Requests the retrieval of stored chat data.
- **Controller**: Manages chat data storage and retrieves past conversations upon user request.
- **Presenter**: Displays the chat history in a scrollable format within the chat window.

### Message Search Feature

**User Story**: Abdallah wants to find a specific message from last week in the group chat. He uses the search feature to locate it by keyword or date.

- **Assigned to**: Abdallah
- **Use Case**: Users can search past messages within a chat by entering keywords or selecting a date, enabling quick and efficient message retrieval.
- **Interactor**: Handles search inputs and initiates the search function.
- **Controller**: Searches the chat history for relevant messages based on the keyword or date.
- **Presenter**: Displays the search results in the chat window, highlighting matched messages.

### Login Feature

**User Story**: Girik opens the app and logs in to access his conversations and settings.

- **Assigned to**: Girik
- **Use Case**: Users can securely log in to access personalized chat data and settings.
- **Interactor**: Handles login requests and verifies user credentials.
- **Controller**: Manages user sessions and provides access to stored user-specific data.
- **Presenter**: Displays the user’s chat history and personalized settings upon successful login.

## Software Specification

The Real-Time Messaging and Translation Application includes the following core features:

1. **Direct and Group Messaging**: Enables both one-on-one and group conversations.
2. **Real-Time Translation**: Automatically translates messages into the recipient's language using a language translation API.
3. **Editable Messages**: Allows users to edit sent messages, displaying both original and edited versions with timestamps.
4. **Chat History**: Stores and retrieves past conversations, including translated messages, for user reference.
5. **Message Search**: Allows users to search past messages by keywords or date.
6. **User Authentication**: Provides secure login for personalized chat experiences.