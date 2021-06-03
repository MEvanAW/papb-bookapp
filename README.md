# peek a book

<img src="https://lh3.googleusercontent.com/EZQt9gl3JkT9_yWlS0TwFLN_F4siiEk_RVqUOeGXgN_nqcTfw0rdDbsS_jegbUP9U21x_WRdD6gmlEdS_V-rFMDsEHCbIA2_bcYGyHTZefjaLc4T50ku-6gZNs6lStfh" width="100" height="100">

<b>peek a book</b> is an Android app built for users to read their favorite books freely. With <b>peek a book</b>, users can read books anywhere and anytime, as long as they are connected to the internet. Users can also put some books they want to read in the future, in the bookshelf. On the profile page, users can track books they have read, books they want to read, and books they are currently reading. This application also gives users quotes to read while using the app. <b>peek a book</b> is also useful for those who want to build a lasting reading habit since it always reminds users to pick up their reading. This mobile application retrieves books from [Google Books APIs](https://developers.google.com/books).

## Installation

There is currently no release yet. You may clone the project and run the debug signature of the app in Android Studio.

Clone <b>peek a book</b> to your local machine
```bash 
  git clone https://github.com/MEvanAW/papb-bookapp.git
```

## Features

- Browsing books
- Viewing curated book list
- Viewing book details
- Reading books
- Creating books-to-read list
- Reading quotes while scrolling down on the app

## Project Status

This project is currently at an early stage of development.

## Project Architecture

This application uses MVVM architecture because it is the most relevant architecture based on the app that the developers have built before week 7 of Mobile Apps Development class. Our team decided to adjust the app to apply the MVVM architecture to avoid double-work.  Further reason is to create clean code. By implementing MVVM on this project, it helps our team to separate the logic (`ViewModel` classes) from the View classes. This implementation results in cleaner code and less redudant in some ways, such as QuoteViewModel and BookTopicModel class that can be accessible for more than one activity.

## Project Lifecycle Implementation

Currently, this application has 2 types of [lifecycle](https://developer.android.com/guide/components/activities/activity-lifecycle) overrides:
1. [onCreate()](https://developer.android.com/reference/android/app/Activity#onCreate(android.os.Bundle)), which are called when the system first creates the activity. This method is used to fetch data via ViewModel and set some click listeners.
2. [onResume()](https://developer.android.com/reference/android/app/Activity#onResume()), which is called after [onStart()](https://developer.android.com/reference/android/app/Activity#onStart()) or [onPause()](https://developer.android.com/reference/android/app/Activity#onPause()) method, makes the activity available to interact with user. This method can be found in MainActivity and BookDetailActivity, in which the quote section will automatically refresh and generate a new quote.

## Screenshots

Here are some screenshots taken from <b>peek a book</b>.

### Home

<img src="https://user-images.githubusercontent.com/50491841/120643621-98a9ac00-c4a0-11eb-9ad3-d0f63e95128c.jpg" width="270" height="566"> <img src="https://user-images.githubusercontent.com/50491841/120643944-f938e900-c4a0-11eb-87d1-4ef06765049a.jpg" width="270" height="566">


### Book Detail

<img src="https://user-images.githubusercontent.com/50491841/119216878-8161da80-bb00-11eb-91ba-c7a00c16c85a.jpg" width="270" height="480"> <img src="https://user-images.githubusercontent.com/50491841/119216894-a0f90300-bb00-11eb-8709-d1a6a6caff49.jpg" width="270" height="480">

### Reading Book

It is recommended to have Google Chrome as default browser and use landscape orientation.

<img src="https://user-images.githubusercontent.com/50491841/119216916-c4bc4900-bb00-11eb-9a80-0b6f19bbd07d.jpg" width="480" height="270">

### Searching Book

<img src="https://user-images.githubusercontent.com/50491841/119217006-7a879780-bb01-11eb-8477-3df9f6ec8dce.jpg" width="270" height="480">

### My Bookshelf

Contains books that the user has read before or save to read for later.

<img src="https://user-images.githubusercontent.com/50491841/120644338-69e00580-c4a1-11eb-900d-fbfa40a8d8b8.jpg" width="270" height="566">

### User Profile

<img src="https://user-images.githubusercontent.com/50491841/119217110-1c0ee900-bb02-11eb-9f89-91529acb3844.jpg" width="270" height="480">

## Authors
- [Indira Pravianti](https://github.com/indirapravianti)
- [M. Evan Anindya W.](https://github.com/MEvanAW)
- [Muhammad Nauval Rafli](https://github.com/nauvalrafli)

## Contributing

We currently only accept contributions from collaborators.

## Support

If you need any help, You can contact Evan as the repository owner at evananindya@mail.ugm.ac.id
