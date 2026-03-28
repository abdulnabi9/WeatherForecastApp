 Weather Forecast App

 A modern Android app that displays a 3-day weather forecast with offline support, built using Clean Architecture and MVVM.

 Features

 Search weather by city name
 3-day forecast (from 5-day API data)
 Offline-first support using Room
️ Error handling (invalid city, no internet)




  Architecture

The app follows Clean Architecture with MVVM:
Presentation Layer → Jetpack Compose UI
Domain Layer → Business logic
Data Layer → API + Room database



 Tech Stack

Kotlin
Jetpack Compose
Retrofit
Room Database
Hilt (Dependency Injection)
Coroutines + Flow
Coil (Image loading)



 API Used

OpenWeatherMap Forecast API
Endpoint: `/data/2.5/forecast`



 Key Implementation Details

 3-Day Forecast Logic

The API provides data every 3 hours for 5 days.
The app filters this data to extract one entry per day (12:00 PM) to display a clean 3-day forecast.


 Offline-First Approach

When online:

     Fetch data from API
     Save to Room database
When offline:

     Load last saved data from database

 Error Handling

Invalid city → “City not found”
No internet → “No Internet Connection”
Empty input → Prompt user


 API Key Handling

API key is stored in `local.properties`
Injected using BuildConfig and Hilt
Not exposed in source code



 Screens

Search Screen
Weather Forecast List
Error State
Offline Mode



  How to Run
Clone the repository
Add your API key in `local.properties`:

   
   WEATHER_API_KEY=your_api_key_here 
Sync project
Run the app



 Future Improvements

Location-based weather
Unit conversion (°C / °F)
Weather animations
UI enhancements



 
