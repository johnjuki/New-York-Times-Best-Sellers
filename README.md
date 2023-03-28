<h1 align="center">NYT Best Sellers</h1>

**NYT Best Sellers** is a [FOSS](https://en.m.wikipedia.org/wiki/Free_and_open-source_software) Android application that displays the New York Times Best Sellers list, providing users with an up-to-date ranking of the most popular books in various categories.\
The app uses [New York Times](https://developer.nytimes.com/apis) Books API to fetch the list and [Google Books](https://developers.google.com/books) API to fetch the book description.


------

| ![homedark2](https://user-images.githubusercontent.com/84154246/228127526-aebe8b90-f577-4dd6-aa1e-e68fe52217d9.png) | ![listdark](https://user-images.githubusercontent.com/84154246/228127582-3c03214c-e558-4248-8fbd-19cfbd872c82.png) | ![detailsdark](https://user-images.githubusercontent.com/84154246/228127605-86844762-593f-4a18-b31e-3b5d56cd685b.png) |
|-------------------------------------------------------|-------------------------------------------------------|-------------------------------------------------------|
| ![home](https://user-images.githubusercontent.com/84154246/228127090-191b7c7b-cae1-445d-9406-17518270cda3.png) | ![list](https://user-images.githubusercontent.com/84154246/228127301-438e67c8-c32c-4f36-a8e8-4e041c8f509b.png) | ![details](https://user-images.githubusercontent.com/84154246/228127387-e5fa4dbd-476b-47aa-b1c6-49d9ee635d71.png) |

------

<h2 align="center">Tech Stack</h2>

- [Kotlin](https://kotlinlang.org/) - First class and official programming language for Android development.
- [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) - To improve performance by doing I/O tasks out of main thread asynchronously.
- [Android Architecture Components](https://developer.android.com/topic/libraries/architecture) - Collection of libraries that help you design robust, testable, and maintainable apps.
- [Retrofit](https://square.github.io/retrofit/) - A type-safe HTTP client for Android and the JVM.
- [OkHttp3](https://square.github.io/okhttp/) - OkHttp is an HTTP client for Android that’s efficient by default.
- [Gson](https://github.com/google/gson) - A Java serialization/deserialization library to convert Java Objects into JSON and back.
- [Glide](https://github.com/bumptech/glide) - An image loading and caching library for Android focused on smooth scrolling.

------


**Note:** 
- `NYT_API_KEY`. Required to fetch the books list. Get yours [here](https://developer.nytimes.com/apis).
- Find sample json responses in the assets folder.
