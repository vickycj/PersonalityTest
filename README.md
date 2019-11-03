# Personality Trait Android Application

Architecture Design Pattern - MVVM (Model View ViewModel) 

Tech Stack
-----------

- Programming language    - **Kotlin**
- NetWork API             - **RetroFit**
- Dependancy Injection    - **Dagger 2 for Kotlin**
- Offline Storing         - **Room ORM (Arch components)**
- Streams                 - **RxAndroid and LiveData**
- Architecture Components - **ViewModel, LiveData, Android JetPAck Components**
- Testing Framework       - **Junit, Mockito , RoboElectric**


Application Details
-------------

- Application fetches the data form github repo using retrofit.
- Fetched data streams into the view and updates the UI through LiveData.
- App works both in portrait and landscape mode.
- Two Components are developed in isolation , Question card and Options List, which can be reusable.
- App follows the idea of MFE (Micro front end) wherein the application components can be reused in other projects.
- Unit testing covers application functionality for the viewmodel using Junit and mockito.
- View assertion is achieved using roboelectric which runs as unit test cases instead of instrumentation.
