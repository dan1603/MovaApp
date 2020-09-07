# Mova App

This is a test android application, which allows user to search random Gif image, and get it into the search history.

#### Screens
- Home Screen: Displays list of recently searched gifs, and has input field, which allows you to search another image. If you search by keyword, that is already saved in search history, you will just get new random image by this keyword.
- Detail Screen: Displays the gif, you opened from search history, or new query. If the gif is not found, this screen will display you "Not found" message.

#### Architecuture
- Pattern: MVVM
- Language: Kotlin

#### Application Layers
- Api Service
- Remote Data source
- Data Source
- Repository
- Usecases
- View Model
- View

#### Components & Libs
- Dagger 2
- Room
- PagingLibrary
- LiveData
- DataBinding
- AndroidJetpack

#### Api
For retreiving random gif-image by keyword, the Giphy API is used.

#### Demonstration

<img src="https://i.imgur.com/b57oPxC.jpg" width=300 /><img src="https://i.imgur.com/dx69fyk.jpg" width=300 />

You can watch video demonstration <a href="https://youtu.be/sxMT2eA_ER4">here</a>.
