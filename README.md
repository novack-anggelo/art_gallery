# Art Gallery App
Simple app to display different art pieces from Art Institute of Chicago.

### Screenshots
![screen_record.gif](screen_record.gif)

### Architecture
The app has a simple Clean architecture implementation with MVVM architecture.
The architecture is feature drove, each feature contains a domain layer, a data layer and a presentation layer.
data: communication with the API, hold DTO models and mapping to domain models
domain: business logic, hold domain models, domain state and use cases
ui: presentation layer, hold UI models, UI params and mapping from domain models
As features are somehow related I decided to put data layer in a common package

## Solution
The app is fully build with Jetpack Compose and Material3. for networking I'm using Retrofit and Moshi for serialization.
The app uses 2 screens:
[ArtOverviewScreen](app/src/main/java/com/novack/art_gallery/art_overview/ui/ArtOverviewScreen.kt)) this is the entry point of the app, here I fetch the list of artworks and display them in a list
[ArtDetailsScreen](app/src/main/java/com/novack/art_gallery/art_details/ui/ArtDetailsScreen.kt)) detail view of an artwork, here I fetch the details of an artwork and display them

## AI usage
I'm using this project as a chance to use AI for assisting my coding, taking care of repetitive tasks and supporting on tasks I'm not familiar with.
My conclusion so far is: AI is great for assisting developers, but still makes a lot of mistakes and questionable decision, so must be supervised and used with care.
I've used AI for:
- add dependencies: Hilt, retrofit, moshi, kotlinx Immutable
- navigation setup: Create simple routes for my screens
- generate tests
- generate mock data and data for my ParamProviders