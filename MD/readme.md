# Mobile Development Codebase 🌾

This folder is used to store Ricehero app projects that developed using android studio as IDE. 

# Ricehero Mobile App

Ricehero was created to develop the utilization of technology in agriculture and assist farmers in the farming process, espicially in acccurately detecting diseases and
pests in rice. From the results, information will be provided about the symptoms, causes, prevention, and treatment of the disease. The app also has other main features 
which are Crop Monitoring Schedule, Rice Price and Yield Forecasting, Weather API for Monitoring, consultation, and farming tips & tricks.

# About the Android Project

This application implementing MVVM Architecture Pattern. By using the mvvm architecture pattern, it will separate the business logic (model), user interface (view), and 
viewmodel to make application development, maintenance, and testing easier. View will observe live data from viewmodel while the data that observed in view model 
is obtained from data source layer (model and repository). The data used in this application is static data and response data (API). Static data is used to display 
information about plant diseases and plant descriptions. while the response data from the API is used to display the prediction results of the plants that are detected.

# Technical Development
- Kotlin Programming Language
- Google Maps Android API
- Jetpack Library
  - Navigation
  - Coroutine
  - ViewModel
  - LiveData
  - Room
  - CameraX
  - DataStore
- Glide
- Retrofit2
