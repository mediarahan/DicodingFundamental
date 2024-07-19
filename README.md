# Golden Sun Djinn List App

## Description
This project was submitted as the requirement for passing the course [Belajar Fundamental Aplikasi Android](https://www.dicoding.com/academies/14-belajar-fundamental-aplikasi-android) on Dicoding.

## Features
This app allows users to brows a list of real Github users using the Github API and few additional features, such as:
- Browsing user profiles along with their followers and following counts.
- A search bar for quick access to specific user profiles.
- Toggleable Dark and Light themes for personalized viewing preferences.
- The ability to favorite users for easy access to preferred profiles later.

## Technical Aspect
In addition to the techniques shown in the prerequisite course's [submission](https://github.com/mediarahan/DicodingBeginner), this project is a showcase of my understanding of fundamental concepts in Android Development, specifically about:
- Understanding Fragments and how to use them to show multiple ViewGroups in one activity
- How to implement a SearchView and SearchBar with option menu
- How to consume API and perform calls using Retrofit to the Github API
- The MVVM app architecture
- Android Jetpack components, such as:
  - LiveData, which is used to ensure reactive UI updates when change is detected in the observed object
  - Room and its components for persistent local storage. These include DAO (Data Access Object), Entity, and Database
  - ViewModel for separation between UI and data layer, which is often used to handle logic before exposing the value to the UI layer
  - DataStore for storing simple key-value pairs, such as App Theme toggle or other app settings / preferences
- Design patterns such as the Repository pattern that is used to centralize API call and database query logic and Factory pattern to create ViewModel instances

## Built With
- **[Kotlin](https://kotlinlang.org/)** - The primary programming language for modern Android development.
- **[Android Studio](https://developer.android.com/studio)** - Official IDE for Android.
- **[XML](https://developer.android.com/guide/topics/ui/declaring-layout)** - Used for defining user interfaces.

Click [here](https://drive.google.com/file/d/1P4MSWzerwf2tT-DIMgVkUTOhEP9KNeCg/view?usp=sharing) to download the app (APK).
