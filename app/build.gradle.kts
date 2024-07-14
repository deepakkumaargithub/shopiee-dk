plugins {
    alias(libs.plugins.androidApplication)
    id("com.google.gms.google-services")  // Google Services plugin for Firebase
}

android {
    namespace = "com.example.shoppie"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.shoppie"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    buildFeatures {
        viewBinding = true  // Enables view binding
    }
}

dependencies {
    // AndroidX Libraries
    implementation(libs.appcompat)  // AppCompat library
    implementation(libs.material)  // Material Design library
    implementation(libs.activity)  // Activity library
    implementation(libs.constraintlayout)  // ConstraintLayout library
    implementation(libs.recyclerview)  // RecyclerView library
    implementation(libs.viewpager)  // ViewPager library

    // Firebase
    implementation(libs.firebase.database)  // Firebase Realtime Database

    // Google Play Services
    implementation(libs.play.services.location)  // Play Services Location

    // Image loading libraries
    implementation(libs.picasso)  // Picasso for image loading
    implementation(libs.glide)  // Glide for image loading
    annotationProcessor(libs.compiler)  // Annotation processor for Glide

    // Testing Libraries
    testImplementation(libs.junit)  // JUnit for unit tests
    androidTestImplementation(libs.ext.junit)  // AndroidX Test - JUnit
    androidTestImplementation(libs.espresso.core)  // Espresso for UI tests


    // ViewPager
    implementation(libs.viewpager)

     

    // New items
    implementation(libs.appcompatV131)
    implementation(libs.materialV140)



}

