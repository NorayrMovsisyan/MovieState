plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.serialazation)
}

android {
    namespace = "com.example.moviestate"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.moviestate"
        minSdk = 28
        targetSdk = 36
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
            buildConfigField(
                "String",
                "ACCESS_TOKEN",
                "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI4YTc5Mzg0ZmVkNDc2ZGFhZjUyZTdhNzMwYzliYmQxOSIsIm5iZiI6MTc1NjkwNTMzNy4xNjgsInN1YiI6IjY4YjgzZjc5NWUwZDQ3YjA3NThjZDgxMSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.cBMD72hCzBbaiv3xp2S4rfti9c659QFZPtvLA-Jzz_4"
            )

            buildConfigField(
                "String",
                "API_KEY",
                "8a79384fed476daaf52e7a730c9bbd19"
            )

            buildConfigField(
                "String",
                "BASE_URL",
                "\"https://api.themoviedb.org/\""
            )
        }

        debug {
            buildConfigField(
                "String",
                "ACCESS_TOKEN",
                "\"eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI4YTc5Mzg0ZmVkNDc2ZGFhZjUyZTdhNzMwYzliYmQxOSIsIm5iZiI6MTc1NjkwNTMzNy4xNjgsInN1YiI6IjY4YjgzZjc5NWUwZDQ3YjA3NThjZDgxMSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.cBMD72hCzBbaiv3xp2S4rfti9c659QFZPtvLA-Jzz_4\""
            )

            buildConfigField(
                "String",
                "API_KEY",
                "\"8a79384fed476daaf52e7a730c9bbd19\""
            )

            buildConfigField(
                "String",
                "BASE_URL",
                "\"https://api.themoviedb.org/\""
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)

    //retrofit
    implementation(libs.retrofit)
    implementation(libs.retrofit2.kotlinx.serialization.converter)
    implementation(libs.logging.interceptor)

    //koin
    implementation(libs.koin.core)
    implementation(libs.koin.android)
    implementation(libs.koin.androidx.compose)

    //kotlin
    implementation(libs.kotlinx.serialization.json)

    //coil
    implementation(libs.coil)
    implementation(libs.coil.compose)

    //paging
    implementation(libs.androidx.paging.runtime)
    implementation(libs.androidx.paging.compose)

    //navigation
    implementation(libs.androidx.navigation.compose)

    implementation(libs.core)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}