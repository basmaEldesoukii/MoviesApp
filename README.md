MovieApp: Android Movie Listing and Details Application
Overview
MovieApp is an Android application that displays lists of now playing, popular, and upcoming movies fetched from The Movie Database (TMDb) API. Users can view detailed information about each movie by clicking on it. The app uses the MVI (Model-View-Intent) architecture, Jetpack Compose for UI, Coroutines for asynchronous operations, Room DB for local storage, Retrofit for network requests, Hilt for dependency injection, and Coil for image loading. It follows Clean Architecture principles and is implemented as a multi-module project. Comprehensive unit testing ensures reliability.

Features
List Screen
Tabs: Three tabs for now playing, popular, and upcoming movies.
Horizontal Lists: Each tab displays a horizontal list of movies from the TMDb API.
Movie Info: Shows title, release date, and poster image.
Detail Screen
Detailed Info: Displays comprehensive information about the selected movie, including overview, genres, and runtime.
Navigation: Button to return to the list screen.
Architecture
MVI Pattern: Manages UI-related logic.
Jetpack Compose: Modern, declarative UI development.
Jetpack Navigation: Navigation for seamless screen transitions.
Jetpack ViewModel: Manages UI-related data.
Coroutines: Handles asynchronous operations.
Room DB: Local data storage for offline access.
Retrofit: Simplifies network requests.
Hilt: Dependency injection for clean, maintainable code.
Coil: Efficient image loading.
Clean Architecture: Ensures separation of concerns and a scalable codebase.
Multi-Module: Organizes the codebase into separate modules for better maintainability and scalability.
Unit Testing
Coverage: Critical components and functionalities.
Focus Areas: Domain logic, data retrieval, and UI interactions.
Error Handling
Graceful Handling: Manages network errors, API failures, and exceptions.
User Feedback: Displays informative error messages when issues arise.
Additional Considerations
UI/UX Design: Ensures a smooth, intuitive user experience.
Caching: Uses Room DB to minimize network requests.
Code Quality: Follows best practices for readability and maintainability.
100% Kotlin: The entire project is written in Kotlin for modern, concise, and safe code.
