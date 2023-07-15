/*
 * Copyright © 1936 Yash Garg.
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */
@file:Suppress("UnstableApiUsage", "DSL_SCOPE_VIOLATION")

plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.kotlinKapt)
    alias(libs.plugins.hilt)
}

android {
    namespace = "dev.yashgarg.kimai"
    compileSdk = 34

    defaultConfig {
        applicationId = "dev.yashgarg.kimai"
        minSdk = 30
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        setProperty("archivesBaseName", "${defaultConfig.applicationId}-v$versionName")
        vectorDrawables { useSupportLibrary = true }
    }

    buildTypes {
        release {
            isShrinkResources = true
            isMinifyEnabled = true
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

    kotlinOptions { jvmTarget = JavaVersion.VERSION_17.toString() }

    buildFeatures {
        compose = true
        buildConfig = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion =
            libs.compose.compiler.get().versionConstraint.requiredVersion
    }

    packaging { resources { excludes += "/META-INF/{AL2.0,LGPL2.1}" } }
}

dependencies {
    implementation(libs.bundles.androidx)
    implementation(libs.bundles.compose)
    implementation(platform(libs.compose.bom))
    implementation(libs.google.dagger.hilt)
    implementation(libs.bundles.square)
    implementation(libs.accompanist.systemuicontroller)

    implementation(project(":data"))
    implementation(project(":preferences"))

    kapt(libs.androidx.room.compiler)
    kapt(libs.google.dagger.hilt.compiler)

    testImplementation(libs.junit)
    debugImplementation(libs.compose.ui.tooling)
    debugImplementation(libs.compose.ui.test.manifest)
}
