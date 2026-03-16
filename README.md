# NewsNow 📰

**NewsNow** is a modern Android news application built using best
development practices, including **Jetpack Compose**, **Clean
Architecture**, **MVVM**, **Hilt**, and **Room Database**. The app
consumes real-time data from a news API (https://newsapi.org/) and
allows users to save their favorite articles for offline reading.

## ✨ Features
-   **Onboarding:** An intuitive introduction for new users.
-   **News Feed:** A continuously updated list of the latest news using
    Paging 3.
-   **Search:** Find specific news articles using keywords.
-   **Bookmarks:** Save articles locally using Room to access them
    later.
-   **Details View:** Full article view with options to share or open
    the article in a web browser.
-   **Dark/Light Mode:** Native support for dynamic themes.

## 🛠️ Technologies Used

-   **Kotlin:** Primary programming language.
-   **Jetpack Compose:** Modern declarative UI toolkit.
-   **Dagger Hilt:** Dependency injection.
-   **Retrofit & OkHttp:** REST API consumption.
-   **Room DB:** Local data persistence.
-   **Paging 3:** Efficient data loading with pagination.
-   **Navigation Compose:** Navigation between screens and nested
    graphs.
-   **Coil:** Asynchronous image loading.
-   **DataStore:** Storage for user preferences (onboarding status).

## 🏗️ Architecture

The project follows **Clean Architecture** principles:

-   **Domain:** Data models, Use Cases, and repository interfaces.
-   **Data:** Repository implementations, APIs (Retrofit), and database
    (Room).
-   **Presentation:** ViewModels, UI states, and UI components built
    with Compose.

## 🚀 How to Run the Project

1.  Clone this repository.
2.  Get an API key from [NewsAPI.org](https://newsapi.org/).
3.  Add your API key to the project.
4.  Sync the project with Gradle and run it in Android Studio.
