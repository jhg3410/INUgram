plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
    id(libs.plugins.hilt.plugin.get().pluginId)
}

android {
    namespace = "jik.inu.inugram"
    compileSdk = 34

    defaultConfig {
        applicationId = "jik.inu.inugram"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("debug")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    // modules
    implementation(projects.core.designsystem)
    implementation(projects.data)
    implementation(projects.feature.certification)
    implementation(projects.feature.home)
    implementation(projects.feature.upload)
    implementation(projects.feature.mypage)

    implementation(libs.androidx.ktx)
    implementation(libs.androidx.runtime.ktx)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.navigation)

    // compose
    implementation(libs.compose.ui)
    implementation(libs.compose.material3)
    implementation(libs.compose.tooling)
    implementation(libs.compose.preview)

    // lifecycle
    implementation(libs.lifecycle.viewmodel)
    implementation(libs.lifecycle.runtime)

    // hilt
    implementation(libs.hilt.android)
    implementation(libs.hilt.navigation)
    kapt(libs.hilt.compiler)

    // splash
    implementation(libs.splashscreen)

    // test
    testImplementation(libs.junit)

    // android test
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso)
    androidTestImplementation(libs.compose.test.junit)

    // debug
    debugImplementation(libs.compose.tooling)
    debugImplementation(libs.compose.test.manifest)
}