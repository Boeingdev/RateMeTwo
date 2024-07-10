plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.rateme2"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.rateme2"
        minSdk = 24
        targetSdk = 34

        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        dataBinding  =true // for data binding
        viewBinding  = true // for view binding
    }

    buildTypes {
        release {
            isMinifyEnabled = false

            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    implementation ("com.daimajia.androidanimations:library:2.4@aar")
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}