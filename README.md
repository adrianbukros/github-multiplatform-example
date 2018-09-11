# GitHub Kotlin Multiplatform Example

This is a sample application using Kotlin Multiplatform for Android and iOS. Has a simple business logic implemented in Kotlin and used by the two native application. The UI is implemented on both platforms.

## Usage
You can import the whole project into IntelliJ IDEA and the two client application to Android Studio and Xcode.

To build the project from IntelliJ IDEA:
1. Uncheck the "Configure on demand" option at Preferences/Build, Execution, Deployment/Gradle-Android Compiler
2. If Gradle is missing the Android SDK directory, you can create `local.properties` file in the project's root directory with the following content:
```
ndk.dir=/Users/<YOUR USERNAME>/Library/Android/sdk/ndk-bundle
sdk.dir=/Users/<YOUR USERNAME>/Library/Android/sdk
```

To build the android app from Android Studio:
1. Uncheck the "Configure on demand" option at Preferences/Build, Execution, Compiler
2. If Gradle is missing the Android SDK directory, you can create `local.properties` file in the androidApp directory with the following content:
```
ndk.dir=/Users/<YOUR USERNAME>/Library/Android/sdk/ndk-bundle
sdk.dir=/Users/<YOUR USERNAME>/Library/Android/sdk
```
3. Click Run/Edit Configurations/+ sign/Android app. Select the "app" module on the config settings page.

To run the iOS app from Xcode:
1. Call `pod install` in terminal at root/iosApp.
2. Open the client-mpp.xcworkspace from the project root/client-mpp/ios