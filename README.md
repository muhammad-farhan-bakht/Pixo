<h1 align="center">PIXO</h1>

<p align="center">  
Pixo is a small demo application based on Modern Android application tech-stacks and mix MVVM & MVI Architecture.<br>This project is for focusing especially on the Jetpack Libraries like Hilt for dependency injection and Paging 3.<br>
Also fetching data from the network via repository pattern.
</p>
</br>

<p align="center">
<img src="/art/app_screen_shot_group.png"/>
</p>


## Tech stack & Open-source libraries
- Minimum SDK level 21
- [Kotlin](https://kotlinlang.org/) based, [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) + [Flow](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/) for asynchronous.
- Animations (Shared element transitions - Fade in/out)
- JetPack
  - LiveData - notify domain layer data to views.
  - Lifecycle - dispose of observing data when lifecycle state changes.
  - ViewModel - UI related data holder, lifecycle aware.
  - Hilt (alpha) for dependency injection.
  - Paging 3 - load and display pages of data from a larger dataset.
  - Navigation - (save args) for navigation between fragments.
  - Databinding - (bind adapters) to bind UI components in layouts to data sources.
  - KTX - Provide concise, idiomatic Kotlin to Jetpack, Android platform, and other APIs.
- Architecture
  - MVI Architecture (Model - View - Intent)
  - MVVM Architecture (View - Model - Model - View)
  - Android Unidirectional Data Flow - Kotlin Flow
  - Repository pattern
- [Retrofit2 & OkHttp3](https://github.com/square/retrofit) - construct the REST APIs and paging network data.
- [Gson](https://github.com/google/gson) - A JSON library for Kotlin and Java.
- [Glide](https://github.com/bumptech/glide) - loading images.
- [Timber](https://github.com/JakeWharton/timber) - logging.
- [Material-Components](https://github.com/material-components/material-components-android) - Material design components like ripple animation, cardView.


## MAD Score
<img src="/art/summary.png"/>
<img src="/art/kotlin.png"/>


## Architecture
Pixo is based on MVI & MVVM architecture and a repository pattern.

<img src="/art/android_mvi_architecture_full.png" align="center" width="100%"/>


## Open API

Pixo using the [Pixabay API](https://pixabay.com/) for constructing RESTful API.<br>
Pixabay API provides a RESTful API interface to highly detailed objects built from thousands of lines of data related to Images.


## Pixo | Stock Photo Mobile App UI | Dribbble

Used Ui/UX designs from [Dribbble](https://dribbble.com/) by [Raviteja Bandila](https://dribbble.com/shots/6031189-Pixo-Stock-Photo-Mobile-App-UI/attachments/6031189-Pixo-Stock-Photo-Mobile-App-UI?mode=media)


## Find this repository useful? :heart:
Support it by joining __[stargazers](https://github.com/muhammad-farhan-bakht/Pixo/stargazers)__ for this repository. :star: <br>
And __[follow](https://github.com/muhammad-farhan-bakht)__ me for my next creations! 🤩

# License
```xml
Designed and developed by 2021 Muhammad Farhan

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
