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
    implementation(libs.firebase.database)
    implementation(libs.play.services.location)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    // firebase
    implementation (libs.firebase.database)
    implementation (libs.firebase.auth)





    // ViewModel and LiveData
    implementation (libs.lifecycle.extensions)

    // RecyclerView
    implementation( libs.recyclerview)

    // Picasso
    implementation (libs.picasso)

    // ViewPager

    implementation ("androidx.viewpager:viewpager:1.0.0")
    //implementation ("com.github.bumptech.glide:glide:4.16.0")
    //indicator

    // Others
    implementation (libs.appcompat.v131)
    implementation (libs.material.v140)
}
