# Moviez
[![Codacy Badge](https://app.codacy.com/project/badge/Grade/3cc6908b565b46f6940328734f83c051)](https://www.codacy.com/gh/kralonur/Moviez/dashboard?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=kralonur/Moviez&amp;utm_campaign=Badge_Grade)
[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=kralonur_Moviez&metric=sqale_rating)](https://sonarcloud.io/dashboard?id=kralonur_Moviez)
[![API](https://img.shields.io/badge/API-22%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=22)
[![License: GPL v3](https://img.shields.io/badge/License-GPLv3-blue.svg)](https://www.gnu.org/licenses/gpl-3.0)

#### Moviez - is movie and tv show information application. You can get information like cast, budget, revenue, runtime and much much more from this application.

## Interface
<img width="235" height="500" alt="overall-gif" src="https://user-images.githubusercontent.com/18505576/96937162-3501de00-14d0-11eb-9698-56e9f891559b.gif">

<img alt="movies_screen" src="https://user-images.githubusercontent.com/18505576/96936839-7ba30880-14cf-11eb-8573-cce08ea6b494.png" width="235" height="500"> <img alt="movie_detail_screen" src="https://user-images.githubusercontent.com/18505576/96936981-cfaded00-14cf-11eb-8a5d-d81ec8f5401b.png" width="235" height="500">

<img width="235" height="500" alt="search-screen" src="https://user-images.githubusercontent.com/18505576/96936754-52827800-14cf-11eb-8188-cd181e958e95.gif">

## Project setup

Clone the repo, open the project in Android Studio. 

Open local.properties file and enter this line; `apiKey="YOUR_TMDB_BEARER_TOKEN"`.

Then hit "Run". Done!

## Architecture
Based on mvvm architecture and repository pattern.

![Architecture diagram](https://user-images.githubusercontent.com/18505576/96736485-54601480-13c5-11eb-8bd1-2308f224d58b.png)

## Tech Stack
- Minimum SDK 22
- MVVM Architecture
- DataBinding
- Written 100% on kotlin language
- Architecture Components (Lifecycle, LiveData, ViewModel, Navigation)
- [**TMDB API**](https://developers.themoviedb.org/3/getting-started/introduction) for gettin information about movies and tv series
- [**Material Design**](https://material.io/develop/android/docs/getting-started) for implementing material design
- [**Kotlin Coroutines**](https://github.com/Kotlin/kotlinx.coroutines) for threading operations
- [**Retrofit 2**](https://github.com/square/retrofit) for constructing the REST API
- [**Moshi**](https://github.com/square/moshi) for parsing JSON
- [**OkHttp**](https://github.com/square/okhttp) for implementing interceptor, logging
- [**Glide**](https://github.com/bumptech/glide) for loading images
- [**Paging 3**](https://developer.android.com/topic/libraries/architecture/paging/v3-overview) for pagination
- [**Klock**](https://github.com/korlibs/klock) for formatting date and time
- [**Timber**](https://github.com/JakeWharton/timber) for logging

## Possible future changes
- Add imageview to detail fragments
- Better error handling
- Loading screens
- Notifications