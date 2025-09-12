# SpaceX Rockets App

This Android application lists rockets from the SpaceX API and shows detailed information about each rocket.

## Features

- Display a list of all SpaceX rockets  
- For each rocket:
  - Name and image
  - Technical specifications such as height, diameter, and mass  
- Show more detailed information on a separate screen when a rocket is clicked

## Platforms and Modules

- Android Studio
- Kotlin
- Retrofit — for handling API requests  
- Glide — for image loading  

## API

Data is fetched from the [SpaceX REST API](https://api.spacexdata.com).  
Used endpoint:

```bash
GET https://api.spacexdata.com/v4/rockets
