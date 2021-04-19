# Bookapp

BookApp is an Android app built for users to read their favorite books. Functional features of BookApp are browsing books, viewing curated book list, viewing book details, and reading books. Other additional feature is reading quotes while scrolling down on the app.

## Screenshots

Here are some screenshots taken from Bookapp.

<img src="https://user-images.githubusercontent.com/50491841/113965520-bb7c6300-9857-11eb-9185-45c15e3e818a.jpg" width="270" height="480"> <img src="https://user-images.githubusercontent.com/50491841/113966215-d9969300-9858-11eb-919d-af93f3ac19f7.jpg" width="270" height="480"> <img src="https://user-images.githubusercontent.com/50491841/113966304-0ea2e580-9859-11eb-8085-c50861a94eed.jpg" width="270" height="480"> <img src="https://user-images.githubusercontent.com/50491841/113966410-3e51ed80-9859-11eb-9172-375cee5149d3.jpg" width="270" height="480">

## Project Status

This project is currently at an early stage of development.

## Project Architecture

This application uses MVVM architecture, since it is the most relevant architecture based on the app that the developers have built before week 7 of Mobile Apps Development class. Our team decided to adjust the app to apply the MVVM architecture to avoid double-work. 
Further reason is to create clean code. By implementing MVVM on this project, it helps our team to separate the logic (`ViewModel` classes) from the View classes. This implementation results in cleaner code and less redudant in some ways, such as QuoteViewModel and BookTopicModel class that can be accessible for more than one activity.

## Project Lifecycle Implementation

Currently, this application has 3 types of [lifecycle](https://developer.android.com/guide/components/activities/activity-lifecycle) override:
1. [onCreate()](https://developer.android.com/reference/android/app/Activity#onCreate(android.os.Bundle)), which fires when the system first creates the activity. This method is used to fetch data via ViewModel and set some click listeners.
2. [onResume()](https://developer.android.com/reference/android/app/Activity#onResume()), which is called after [onStart()](https://developer.android.com/reference/android/app/Activity#onStart()) or [onPause()](https://developer.android.com/reference/android/app/Activity#onPause()) method, makes the activity available to interact with user. This method can be found in MainActivity and BookDetailActivity, in which the quote section will automatically refresh and generate a new quote.
3. [onDestroy()](https://developer.android.com/reference/android/app/Activity#onDestroy()), which is used to perform final cleanup before the activity is destroyed. This method can be found when a user exits the app, a short toast will appear.

## Installation

There is currently no release yet. You may clone the project and run the debug signature of the app in Android Studio.

## Contributing

We currently only accept contributions from collaborators.

## Support

If You need any help, You can contact Evan as the repository owner at evananindya@mail.ugm.ac.id
