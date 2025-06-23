Gymondo is an app will help you to know the weather in any city you want. for the upcoming 10 days

## Clean architecture with MVI , slicing by feature and every feature by layer

## We have shared core package have

- Design system components and theme
- Utils to help in DI, networking , navigation and more .

## Data package that have shared dataStore and http client classes .

## DI package that have two modules

- App module to provide the shared dependencies.
- Dispatcher module to provide coroutine dispatchers

# Navigation package that have our navigation graph (in bigger sample we can take different approach)

# features package that contains our features.

 <img src="images/AndroidTemplate-CleanArchitecture.jpg" alt="ArchiTecture logo"/>

## Tests

- [Mockk](https://mockk.io/) library
- [Junit](https://junit.org/) Unit tests
- [Coroutine test](https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-test/) to test
  our coroutines and flows

## General Used Libraries

- Dependency injection (with [Hilt](http://google.github.io/hilt/))
- Network calls (with [Ktor](https://ktor.io/docs/http-client-engines.html#minimal-version))
- Reactive programming (
  with [Kotlin Flows](https://kotlinlang.org/docs/reference/coroutines/flow.html))
- Android architecture components to share ViewModels during configuration changes
- [Splash Screen](https://developer.android.com/develop/ui/views/launch/splash-screen) Support
- Google [Material Design](https://material.io/blog/android-material-theme-color) library
- Declarative UI (with [Jetpack Compose](https://developer.android.com/jetpack/compose))
    - Compose Navigation (
      with [Hilt Support](https://developer.android.com/jetpack/compose/libraries#hilt-navigation)
      )
- Edge To Edge Configuration

# Extra mile if it is bigger project

1. We can support adaptive layouts
2. Modularize the app or may use micro-frontend arch
3. Use gradle version catalog for shared build logic
4. Add CI pipeline
5. Add Home Widget to display current weather to user
6. cover more test cases and write UI testing
