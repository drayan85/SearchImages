# Search-Image
This app is built using https://rapidapi.com/contextualwebsearch/api/web-search API

This repository contains a detailed Image Search app that implementsClean Architecture with MVVM designpattern using Dagger2, Room, RxJava2, OkHttp3, Glide and Retrofit2

<p align="center">
  <img src="https://user-images.githubusercontent.com/4456232/125580083-565d4c93-4465-4ef7-90d4-4824eca2df1d.png" width="250">
  <img src="https://user-images.githubusercontent.com/4456232/125580126-a6f468ec-5454-4863-b24d-e7162543326e.png" width="250">
  <img src="https://user-images.githubusercontent.com/4456232/125580100-e2ed5432-abe2-49c5-93cc-d7b9761cf2d5.png" width="250">
</p>

## - How to build on your environment
You will need to create an account from [RapidAPI](https://rapidapi.com/) to obtain the API key as per 
instructions given in https://docs.rapidapi.com/docs.

In data layer `RemoteSearchImageDataSource.java`
```
return mApiServiceInterface.getSearchImagesBasedOnQuery(BuildConfig.RAPID_API_HOST,
                BuildConfig.API_KEY, query, page, perSize, true, false);
```
**Option 1**
============
Replace `BuildConfig.API_KEY` with your key you have obtain from RapidAPI

**Option 2**
============

On Your MacBook Open the Terminal

Put this into ``` ~/.gradle/gradle.properties```

``` RAPID_API_KEY={api_key_received_from_rapidapi_com}```


Basic layers overview
---------------------
- Modular approch followed
- It is heavily implemented by following standard clean architecture principle.
- Unit testing written for domain and data layers.
- Offline capability.
- [S.O.L.I.D](https://en.wikipedia.org/wiki/SOLID) priciple followed for more understandable, flexible and maintainable.

App layers/module:
- **Domain** - Would execute business logic which is independent of any layer and is just a pure kotlin/java package with no android specific dependency.The domain layer responsibility is to simply contain the UseCase instance used to retrieve data from the Data layer and pass it onto the Presentation layer. 

- **Data** - Would dispense the required data for the application to the domain layer by implementing interface exposed by the domain. The Data layer is our access point to external data layers and is used to fetch data from multiple sources (the cache and remote in our case).

- **Presentation** - This layer's responsibility is to handle the presentation of the User Interface, but at the same time knows nothing about the user interface itself. This layer has no dependence on the Android Framework, it is a pure Kotlin module. Each ViewModel class that is created implements the ViewModel class found within the Architecture components library. This ViewModel can then be used by the UI layer to communicate with UseCases and retrieve data.

### Library reference resources:
1. RxJava2: https://github.com/amitshekhariitbhu/RxJava2-Android-Samples
2. Dagger2: https://github.com/MindorksOpenSource/android-dagger2-example
3. Retrofit2: https://square.github.io/retrofit/
4. OkHttp3: https://square.github.io/okhttp/
5. Glide: https://bumptech.github.io/glide/
6. Room: https://developer.android.com/topic/libraries/architecture/room.html
7. LiveData: https://developer.android.com/topic/libraries/architecture/livedata
8. ViewModel: https://developer.android.com/topic/libraries/architecture/viewmodel
9. Gson: https://github.com/google/gson
10. Mockito: http://site.mockito.org



### License
```
   Copyright (C) 2021 Paramanathan Ilanthirayan

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
