# Drivers App

Drivers App is a test app, to show an example of a MVVM architecture consuming a REST service to show the available drivers that are around the city of Hamburg. Letting the
users select a driver and check their position in a map.

## What I should know

If you want to clone or download this project and try it out, you should follow the next steps:

### Maps API KEY

In the [project.properties] file, you would find something like:

*project.properties:

```
google_maps_api_key="YOUR MAPS API KEY"
```

The Google Maps **API Key** could be obtained at this [link](https://developers.google.com/maps/documentation/android-sdk/get-api-key)

## What's included in the project?

- [x] Github project
- [x] Integrated Fastlane & Travis CI to automate the project builds with two steps. (First step, build. Second step, run tests)
- [x] App modularized with app, domain, data, common (for extensions)
- [x] Followed layer separation by modules. Not by features in this case
- [x] Dependencies organized using `buildSrc` and managed with buckets
- [x] MVVM architecture + use case
- [x] Business models & Data models
- [x] Mapper between models
- [x] Dependency injection (with Koin)
- [x] Hide Secret API Key
- [x] Added Unit tests
- [x] Basic Local Cache (while the app is open)
- [x] Custom JSON API created for this project [here](https://github.com/albertmiro/mock-json-api)

### Improvements:

- [ ] Create a proper datasource getting the local data, stored in a DB and use the Repository pattern in order to get the data from the local datasource or the remote one
- [ ] Add Detekt, Lint, Danger tools
- [ ] Add and improve unit Tests
- [ ] UI Tests (Espresso)
- [ ] Integration Tests (AndroidX Test)
- [ ] Code coverage report (Jacoco)

## Libraries used:

Some of the libraries used in this project are the following ones:

- AndroidX
- Retrofit
- Okhttp
- RxJava
- Koin
- Leak Canary
- Mockito
- JUnit

The detail of all the libraries used can be found at: [buildSrc/src/main/java/dependencies.kt]

## Other information of interest

This project is a refactor from another one I made, that was not following all the good practices I did learn,
so I decided to refactor the old one, and add the layer separation, change Dagger to use Koin for
dependency injection due to the size of the project and the simplicity of Koin, include more tests and use a
more meaningful gradle dependency organization, between other improvements.
