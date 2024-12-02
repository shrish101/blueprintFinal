# Talk Tuah-Ginky

## Authors and Contributors
- **Girik Setya**
- **Shrish Luitel**
- **Thanush Lingeswaran**
- **Abdallah Abdelaal**

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

### User/Team Stories
### Real-Time Messaging and Translation
Girik wants to chat with his friend Abdallah, who speaks Arabic. He sends a message in English, and the application translates it into Arabic in real-time so Abdallah can read it.. - Team
### Editing Messages
Shrish realizes he made a typo in his message and edits it. - Shrish
### Add Friend
Thanush wants to add Girik as a friend so that they can communicate with each other. - Thanush
### Message Search Feature
Abdallah wants to find a specific message. He uses the search feature to locate it by keyword. - Abdallah
### Creating and Logging in to Account
Girik
opens the app and creates an account which he can log in in to later with the information he used to create account. - Girik
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
2. **Adding a friend**:
   - Press the add friend button to add another user as a friend using their username.
3. **Chatting**:
    - Select a friend from the dropdown menu.
    - Type your message, and it will be translated automatically for each person's chosen language.
4. **Editing Messages**:
    - Press edit message button to edit latest message sent by you in the conversation.
6. **Searching Messages**:
    - Use the search button to find message by keyword.

## License
This project is licensed under the MIT License. See the [LICENSE](LICENSE.md) file for details.

---

## Feedback
We value your feedback! Submit your suggestions or bug reports through our [Google Form](https://forms.google.com/example-feedback) or raise an issue on the [GitHub Issues](https://github.com/shrish101/blueprintFinal/issues) page.

---

## Contributions
We welcome contributions! To contribute:
1. Fork the repository.
2. Create a new branch for your feature or bug fix.
3. Commit your changes and push to your forked repository.
4. Create a pull request with a detailed explanation of your changes.

For detailed contribution guidelines, see our [Contributing](CONTRIBUTING.md) document.