# peek a book

<img src="https://lh3.googleusercontent.com/EZQt9gl3JkT9_yWlS0TwFLN_F4siiEk_RVqUOeGXgN_nqcTfw0rdDbsS_jegbUP9U21x_WRdD6gmlEdS_V-rFMDsEHCbIA2_bcYGyHTZefjaLc4T50ku-6gZNs6lStfh" width="100" height="100">

BookApp is an Android app built for users to read their favorite books. Functional features of <b>peek a book</b> are browsing books, viewing curated book list, viewing book details, reading books, and creating book-to-read list. Other additional feature is reading quotes while scrolling down on the app.

## Project Status

This project is currently at an early stage of development.

## Project Architecture

This application uses MVVM architecture because it is the most relevant architecture based on the app that the developers have built before week 7 of Mobile Apps Development class. Our team decided to adjust the app to apply the MVVM architecture to avoid double-work.  Further reason is to create clean code. By implementing MVVM on this project, it helps our team to separate the logic (`ViewModel` classes) from the View classes. This implementation results in cleaner code and less redudant in some ways, such as QuoteViewModel and BookTopicModel class that can be accessible for more than one activity.

## Project Lifecycle Implementation

Currently, this application has 2 types of [lifecycle](https://developer.android.com/guide/components/activities/activity-lifecycle) overrides:
1. [onCreate()](https://developer.android.com/reference/android/app/Activity#onCreate(android.os.Bundle)), which are called when the system first creates the activity. This method is used to fetch data via ViewModel and set some click listeners.
2. [onResume()](https://developer.android.com/reference/android/app/Activity#onResume()), which is called after [onStart()](https://developer.android.com/reference/android/app/Activity#onStart()) or [onPause()](https://developer.android.com/reference/android/app/Activity#onPause()) method, makes the activity available to interact with user. This method can be found in MainActivity and BookDetailActivity, in which the quote section will automatically refresh and generate a new quote.

## Installation

There is currently no release yet. You may clone the project and run the debug signature of the app in Android Studio.

## Screenshots

Here are some screenshots taken from Bookapp.

### Home

<img src="https://user-images.githubusercontent.com/50491841/119216848-4a8bc480-bb00-11eb-9e2f-9d897d25dbf1.jpg" width="270" height="480"> <img src="https://user-images.githubusercontent.com/50491841/119217279-5c229b80-bb03-11eb-8d25-21dac8f308f0.jpg" width="270" height="480">


### Book Detail

<img src="https://user-images.githubusercontent.com/50491841/119216878-8161da80-bb00-11eb-91ba-c7a00c16c85a.jpg" width="270" height="480"> <img src="https://user-images.githubusercontent.com/50491841/119216894-a0f90300-bb00-11eb-8709-d1a6a6caff49.jpg" width="270" height="480">

### Reading Book

It is recommended to have Google Chrome as default browser and use landscape orientation.

<img src="https://user-images.githubusercontent.com/50491841/119216916-c4bc4900-bb00-11eb-9a80-0b6f19bbd07d.jpg" width="480" height="270">

### Searching Book

<img src="https://user-images.githubusercontent.com/50491841/119217006-7a879780-bb01-11eb-8477-3df9f6ec8dce.jpg" width="270" height="480">

### My Bookshelf

Contains books that the user has read before.

<img src="https://user-images.githubusercontent.com/50491841/119217053-da7e3e00-bb01-11eb-8a52-8064c0961f7b.jpg" width="270" height="480">

### User Profile

<img src="https://user-images.githubusercontent.com/50491841/119217110-1c0ee900-bb02-11eb-9f89-91529acb3844.jpg" width="270" height="480">

## Contributing

We currently only accept contributions from collaborators.

## Support

If You need any help, You can contact Evan as the repository owner at evananindya@mail.ugm.ac.id
