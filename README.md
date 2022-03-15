# Finerio Connect SDK Android

[![](https://jitpack.io/v/Finerio-Connect/finerio-connect-sdk-android.svg)](https://jitpack.io/#Finerio-Connect/finerio-connect-sdk-android)
 ![](https://img.shields.io/badge/minSDK-16+-blue.svg) ![Language](https://img.shields.io/badge/Language-Java-orange.svg) ![Language](https://img.shields.io/badge/Language-Kotlin-purple.svg)  

This repository contains a subset of the Finerio Connect SDK. It currently includes the following Finerio Connect libraries.   

### Installation

#### Using Project Build Gradle

Add next configuration to project `build.gradle`:  

```build.gradle
buildscript {  
    ext.kotlin_version = "1.5.30"  
    ...  
    dependencies {  
         classpath "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version"  
         ...  
    }  
}  
```

#### Using Module Gradle

Add next configuration to module `build.gradle`:  

```build.gradle
dependencies {   
    implementation 'com.github.Finerio-Connect:finerio-connect-sdk-android:1.0.3'
}  
```

#### Using Manifest

Add next permission into manifest.xml:    

```xml
<uses-permission android:name="android.permission.INTERNET"/>      
```

#### **Import the module:**

```java
import com.finerioconnect.sdk.core.FinerioConnectSDK;
```

#### Initialize FinerioConnect Core SDK

Java:  

```java
FinerioConnectSDK finerioConnectSDK = FinerioConnectSDK.Companion.getShared();  
finerioConnectSDK.setApiKey("api_key");   
```

Kotlin:  

```kotlin
val finerioConnectSDK = FinerioConnectSDK.shared  
finerioConnectSDK.apiKey = "api_key"  
```

#### ## Configure SDK Core

### Logs and debugging

Logs and debugging
In some cases you'll find usefull to see what is happening inside FinerioBudgetSDK. If so, you can enable logs for debugging purposes.

Java:

```java
FinerioConnectSDK.Companion.getShared().setLogLevel(LogLevel.INFO);
```

Kotlin:

```kotlin
FinerioConnectSDK.shared.logLevel = LogLevel.INFO
```

### Customize fonts

You can customize the default SDK font by adding the type face instance, using the following example:

Java:

```java
Typeface openSansRegular = ResourcesCompat.getFont(requireActivity(), R.font.open_sans_regular);
Fonts fonts = new Fonts(openSansRegular, null, null, null);
finerioConnectSDK.setFonts(fonts);
```

Kotlin:

```kotlin
val openSansRegular = ResourcesCompat.getFont(requireActivity(), R.font.open_sans_regular)
FinerioConnectSDK.shared.fonts = Fonts(openSansRegular)
```

### And finally configure it

```
finerioConnectSDK.configure();
```

### License
