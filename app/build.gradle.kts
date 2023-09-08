plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id ("kotlin-kapt")
    id ("kotlin-parcelize")
}

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
}

dependencies {

    implementation ("org.jetbrains.kotlin:kotlin-stdlib:1.9.0")
    implementation("androidx.core:core-ktx:1.10.1")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation ("androidx.legacy:legacy-support-v4:1.0.0")
    implementation ("androidx.multidex:multidex:2.0.1")

    //Extra
    implementation ("com.github.bumptech.glide:glide:4.14.2")

    //Room
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
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:$kotlinVersion")

    //Testing
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}