plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id ("kotlin-parcelize")
}
apply(from = "../shared_dependencies.gradle")

android {
    namespace = "com.neotica.tourism"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.neotica.tourism"
        minSdk = 27
        targetSdk = 33
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        viewBinding = true
    }
    dynamicFeatures += setOf(":favorite")
}

dependencies {
    implementation(project(":core"))

    /*//Room
    val roomVersion = "2.5.2"
    implementation("androidx.room:room-ktx:2.5.2")
    implementation ("androidx.room:room-runtime:$roomVersion")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1")
    //noinspection KaptUsageInsteadOfKsp
    kapt ("androidx.room:room-compiler:$roomVersion")
    androidTestImplementation ("androidx.room:room-testing:$roomVersion")

    //Retrofit
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation ("com.squareup.okhttp3:logging-interceptor:4.9.3")

    //Coroutines
    val kotlinVersion = "1.6.2"
    api ("org.jetbrains.kotlinx:kotlinx-coroutines-core:$kotlinVersion")
    api ("org.jetbrains.kotlinx:kotlinx-coroutines-android:$kotlinVersion")
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:$kotlinVersion")*/
}