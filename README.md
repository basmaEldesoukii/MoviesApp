# MovieApp: Android Movie Listing and Details Application

## Overview

MovieApp is an Android application designed to showcase now playing, popular, and upcoming movies fetched from The Movie Database (TMDb) API. Users can explore detailed information about each movie by selecting them. The app implements the MVI (Model-View-Intent) architecture and utilizes Jetpack Compose for UI, Coroutines for asynchronous operations, Room DB for local storage, Retrofit for network requests, Hilt for dependency injection, and Coil for efficient image loading. It adheres to Clean Architecture principles and is structured as a multi-module project. Rigorous unit testing ensures reliability.

## Features

### List Screen
- **Tabs:** Three tabs for now playing, popular, and upcoming movies.
- **Horizontal Lists:** Each tab displays a horizontal list of movies from the TMDb API.
- **Movie Info:** Shows title, release date, and poster image.

### Detail Screen
- **Detailed Info:** Comprehensive information about the selected movie, including overview, genres, and runtime.
- **Navigation:** Button to return to the list screen.

## Architecture

- **MVI Pattern:** Manages UI-related logic.
- **Jetpack Compose:** Modern, declarative UI development.
- **Jetpack Navigation:** Facilitates seamless screen transitions.
- **Jetpack ViewModel:** Manages UI-related data.
- **Coroutines:** Facilitates asynchronous operations.
- **Room DB:** Local data storage for offline access.
- **Retrofit:** Simplifies network requests.
- **Hilt:** Dependency injection for clean, maintainable code.
- **Coil:** Efficient image loading.
- **Clean Architecture:** Ensures separation of concerns and a scalable codebase.
- **Multi-Module:** Organizes the codebase into separate modules for better maintainability and scalability.

## Unit Testing

- **Coverage:** Critical components and functionalities.
- **Focus Areas:** Domain logic, data retrieval, and UI interactions.

## Error Handling

- **Graceful Handling:** Manages network errors, API failures, and exceptions.
- **User Feedback:** Displays informative error messages when issues arise.

## Additional Considerations

- **UI/UX Design:** Ensures a smooth, intuitive user experience.
- **Caching:** Utilizes Room DB to minimize network requests.
- **Code Quality:** Follows best practices for readability and maintainability.
- **100% Kotlin:** The entire project is written in Kotlin for modern, concise, and safe code.

## Screenshots
![Screenshot_movie_list1](https://github.com/basmaEldesouky/MoviesApp/assets/41978905/9b9179cf-8355-4ade-aa14-77b820981b0a)
![Screenshot_movie_details](https://github.com/basmaEldesouky/MoviesApp/assets/41978905/02f06d04-a8a9-4d7f-933a-348719e1f139)

