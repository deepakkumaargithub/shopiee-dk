plugins {
    alias(libs.plugins.androidApplication)
    id("com.google.gms.google-services")
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
        viewBinding = true
    }
}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    // Firebase
    implementation(libs.firebase.database)

    // Play Services Location
    implementation(libs.play.services.location)

    // RecyclerView
    implementation(libs.recyclerview)

    // Picasso
    implementation(libs.picasso)

    // ViewPager
    implementation(libs.viewpager)

    // Glide
    implementation(libs.glide)
    annotationProcessor(libs.glide.compiler)

    // Additional Libraries
    implementation(libs.appcompatV131)
    implementation(libs.materialV140)
}