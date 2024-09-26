# android-exam

- Android application features:
  - Displaying random user's remotes source - https://randomuser.me
  - Load cache data when network connection is OFF or when Cache is available.
  - Swipe top to refresh the load data(Cache data will be deleted and update a new sets of data)
  - Swipe bottom request additional sets of users(Pagination)
  - Displaying selected user's information on different activity.

- Architectural design pattern
  - MVVM with repository
  - Dependency injection using Dagger 2
  - View and Data Binding
  - Rx Java
  - Retrofit
  - Material design
  - Room Database
  - okhttp3

- Tools
    - android material
    - jakewharton.rxbinding4:rxbinding-recyclerview
    - jakewharton.rxbinding4:rxbinding-swiperefreshlayout
    - rx java and jakewharton.rxbinding3:rxbinding
    - com.squareup.retrofit2:retrofit
    - com.squareup.okhttp3:okhttp-urlconnection
    - com.google.dagger:dagger
    - androidx.room:room-rxjava

## Goal of the exam ##
To assess a developer's skills in terms of developing Android apps and decision-making on solving common development tasks.

## Tasks ##

- Fork this repository
- Create an Android project with the following features:
    - Create User Interfaces with XML in android
    - Loads and shows a list of Persons from a remote source(can be from some random source / https://randomuser.me)
    - Caches the loaded list of Persons
    - Prevents any loading from the remote source if the cache is available
    - Shows the full details of a Person on a separate screen
    - Swipe to refresh (Force update the cache data)
    - Loading more data when scrolling down bottom.
    - Each `Person` must have the following data: or use the API data https://randomuser.me
        - First name
        - Last name
        - Birthday
        - Age (derived from Birthday)
        - Email address
        - Mobile number
        - Address
        - Contact person
        - Contact person's phone number
    - Uses Clean Architecture
    - Uses Modularization 

- Exam Strategy
    - Start with a Basic Skeleton
    - Implement Core Features
    - Detail Screen and Navigation
    - Additional Features
      - Swipe to refresh
      - Pagination
    - Testing and Material Design
    - Git and Documentation
- Ensure all requirements are met and send an email to peopleops@cybilltek.com once done.

Any libraries or tools of the developer's choosing may be used.

## Optional requirements ##

- Changes are committed following Git Flow
- Follows [this guideline](https://github.com/ribot/android-guidelines)
- Unit tests
- UI tests
- Material design
- Has proguard enabled
- Respects Activity/Fragment lifecycle
- `Mock` flavour that uses mock web server and doesn't have a `release` variant
- `Prod` flavour that uses a random remote source and doesn't have a `debug` variant
- Uses Model-View-ViewModel or similar design pattern
- Uses the Observable pattern
- Uses Dependency Injection
- Uses RxJava
