# Bookapp

BookApp is an Android app built for users to read their favorite books. Functional features of BookApp are browsing books, viewing curated book list, viewing book details, and reading books. Other additional feature is reading quotes while scrolling down on the app.

## Project Status

This project is currently at an early stage of development.

## Project Architecture

This application uses MVVM architecture, since it is the most relevant architecture based on the app that the developers have built before week 7 of Mobile Apps Development class. Our team decided to adjust the app to apply the MVVM architecture to avoid double-work. 
Further reason is to create clean code. By implementing MVVM on this project, it helps our team to distinct the logic (ViewModel class) with the View class. This implementation results in cleaner code and less redudant in some ways, such as QuoteViewModel class that can be accessible for more than one activity.

## Project Lifecycle Implementation

Currently, this application has 3 types of lifecycle override :
1. onCreate(), which fires when the system first creates the activity. This method is used to fetch data and set some click listener to determine what to do when the user click something.
2. onResume(), which is called after onPause() method, for the activity to start interacting with the user. This method can be found in MainActivity and BookDetailActivity, in which the quote section will automatically refresh and generate a new quote.
3. onDestroy(), which is used to perform final cleanup before the activity is destroyed. This method can be found when a user exits the app, a short toast will appear.

## Installation

There is currently no release yet. You may clone the project and run the debug signature of the app in Android Studio.

## Contributing

We currently only accept contributions from collaborators.

## Support

If You need any help, You can contact Evan as the repository owner at evananindya@mail.ugm.ac.id
