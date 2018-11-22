# GitHub Kotlin Multiplatform Example

This is a sample application using Kotlin Multiplatform for Android and iOS. Has a simple business logic implemented in Kotlin and used by the two native application. The UI is implemented on both platforms.

## Usage
You can import the whole project into IntelliJ IDEA and the iOS app to Xcode (Android Studio is not working with this setup, but it's possible to work on the Android side from IntelliJ).

To build the project from IntelliJ IDEA:
1. If Gradle is missing the Android SDK directory, you can create `local.properties` file in the project's root directory with the following content:
```
ndk.dir=/Users/<YOUR USERNAME>/Library/Android/sdk/ndk-bundle
sdk.dir=/Users/<YOUR USERNAME>/Library/Android/sdk
```

To run the iOS app from Xcode:
1. Call `pod install` in terminal at root/iosApp.
2. Open the client-mpp.xcworkspace from the project root/client-mpp/ios