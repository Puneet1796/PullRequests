# Pull Requests

The project demonstrates a list of all the 'closed' pull requests in a single repository on Github. It is developed entirely with Kotlin and follows MVVM architecture pattern. Essentials errors have been handled such as API Errors `401 - Unauthorized, 404 - API Not found`, `No Internet`.

The following libraries have been used in the project to reduce the boilerplate as well as to make use of the Android SDK in a better way.
- [Glide](https://github.com/bumptech/glide) - Image processing
- [Retrofit](https://github.com/square/retrofit) - Network calls
- [Kotlin Coroutines](https://github.com/Kotlin/kotlinx.coroutines) - Handling api responses
- ActivityKtx - provides helpful extensions/delegates such as `viewModels()`
- [Hilt](https://github.com/google/dagger) - Dependency injection
- [Http Logging Interceptor](https://github.com/square/okhttp/tree/master/okhttp-logging-interceptor) - Logging API calls to the console
- Swipe Refresh Layout - Provides Swipe to refresh functionality
- Paging 3 - Provides Paginated data to UI
