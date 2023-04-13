<h1 align="center">Reading Radar</h1>

**Reading Radar** is a [FOSS](https://en.m.wikipedia.org/wiki/Free_and_open-source_software) Android application that displays the New York Times Best Sellers list, providing users with an up-to-date ranking of the most popular books in various categories.\
The app uses [New York Times](https://developer.nytimes.com/apis) Books API to fetch the list and [Google Books](https://developers.google.com/books) API to fetch the book description.


------

<div align="center">
<img src="https://user-images.githubusercontent.com/84154246/231700424-805b7c07-ddb3-4042-9481-19c8d958cad7.png" width=30% height=30%> | <img src="https://user-images.githubusercontent.com/84154246/231700820-e330efc2-5b5a-4ba2-b09e-e60dbc051b1b.png" width=30% height=30%> | <img src="https://user-images.githubusercontent.com/84154246/231700572-cb163c85-2b45-48c6-8ab3-69073c022b65.png" width=30% height=30%> 
</div>

------

<h2 align="center">Tech Stack</h2>

- [Kotlin](https://kotlinlang.org/) - First class and official programming language for Android development.
- [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) - To improve performance by doing I/O tasks out of main thread asynchronously.
- [Android Architecture Components](https://developer.android.com/topic/libraries/architecture) - Collection of libraries that help you design robust, testable, and maintainable apps.
  - [Jetpack Compose](https://developer.android.com/jetpack/compose?gclsrc=ds&gclsrc=ds) - Jetpack Compose is Android’s recommended modern toolkit for building native UI
  - [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) -  observable data holder class
  - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Stores UI-related data that isn't destroyed on UI changes
  - [Room database](https://developer.android.com/jetpack/androidx/releases/room) - Persistence library provides an abstraction layer over SQLite to allow for more robust database access while harnessing the full power of SQLite
- [Retrofit](https://square.github.io/retrofit/) - A type-safe HTTP client for Android and the JVM.
- [OkHttp3](https://square.github.io/okhttp/) - OkHttp is an HTTP client for Android that’s efficient by default.
- [Gson](https://github.com/google/gson) - A Java serialization/deserialization library to convert Java Objects into JSON and back.
- [Coil](https://coil-kt.github.io/coil/compose/) - An image loading library for Android backed by Kotlin Coroutines.

------


**Note:** 
- `NYT_API_KEY`. Required to fetch the books list. Get yours [here](https://developer.nytimes.com/apis).
- Find sample json responses in the assets folder.
