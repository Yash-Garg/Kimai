/*
 * Copyright © 2023 Yash Garg.
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */
@file:Suppress("UnstableApiUsage", "DSL_SCOPE_VIOLATION")

plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.kotlinSerialization)
}

android {
    namespace = "dev.yashgarg.kimai.data"
    compileSdk = 33

    defaultConfig { minSdk = 30 }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

dependencies {
    implementation(libs.androidx.core)
    implementation(libs.google.dagger.hilt)

    implementation(libs.square.moshi)
    implementation(libs.square.moshi.converter)
    implementation(libs.square.moshi.metadata.reflect)
    implementation(libs.square.okhttp.logging)

    implementation(libs.kotlinx.serialization)
    testImplementation(libs.junit)

    implementation(project(":preferences"))
}
