# Talk Tuah-Ginky

## Authors and Contributors
- **Girik Setya**
- **Shrish**
- **Thanush**
- **Abdallah**

## Project Summary
Talk Tuah-Ginky is a real-time messaging and translation application designed to bridge communication gaps across languages. The app enables seamless one-on-one and group conversations by translating messages into the recipient's preferred language in real-time.

This project was created to facilitate effective communication in multilingual settings, addressing a growing need for global connectivity. With features like message editing, chat history, and message search, Talk Tuah-Ginky enhances user experience and accessibility.

---

## Table of Contents
- [Features](#features)
- [Installation](#installation)
- [Usage](#usage)
---

## Features
### Core Features
- **Real-Time Messaging and Translation**: Messages are translated into the recipient's preferred language automatically.
- **Group Chat with Translation**: Facilitates group communication with automatic translations for all participants.
- **Editable Messages**: Allows users to edit previously sent messages with original and updated versions displayed alongside timestamps.
- **Chat History**: Stores and retrieves past conversations, including translated messages.
- **Message Search**: Enables users to locate specific messages using keywords or dates.
- **User Authentication**: Secure login system for personalized chat experiences.

### User Stories
1. **Messaging Across Languages**: Girik wants to chat with his friend Abdallah, who speaks Arabic. He sends a message in English, and the application translates it into Arabic in real-time so Abdallah can read it.. - Team
3. **Message Editing**: Shrish realizes he made a typo in his message and edits it. - Shrish
4. **Chat History Access**: hanush wants to add Girik as a friend so that they can communicate with each other. - Thanush
5. **Search Functionality**: Abdallah wants to find a specific message. He uses the search feature to locate it by keyword. - Abdallah
6. **User Account creation and Log in**: Girik opens the app and creates an account which he can login in to later with the information he used to create account. - Girik
---

## Installation
1. **System Requirements**:
    - OS: Cross-platform (Windows, macOS, Linux)
2. **Setup**:
    - Clone the repository:
      ```bash
      git clone https://github.com/shrish101/blueprintFinal.git
      cd blueprintFinal
      ```
    - Configure the database:
        - Install and run MongoDB.
        - Set up environment variables for the database connection.
    - Setting up .env file:
      
    - Install LibreTranslate Api:
   ```bash
    pip3 install libretranslate
   ```
   - Run api server:
   ```bash
   libretranslate
   ```

## Usage
1. **Starting the Application**:
    - Log in or sign up to access the chat features.
2. **Chatting**:
    - Select a user or group to start a chat.
    - Type your message, and it will be translated automatically.
3. **Editing Messages**:
    - Hover over a message and select "Edit" to update it.
4. **Accessing Chat History**:
    - Scroll through previous conversations in the chat window.
5. **Searching Messages**:
    - Use the search bar to locate messages by keyword or date.
