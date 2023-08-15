/*
 * Copyright © 2023 Yash Garg.
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */
@file:Suppress("UnstableApiUsage", "DSL_SCOPE_VIOLATION")

plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.anvil)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.kotlinKsp)
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.whetstone)
}

android {
    namespace = "dev.yashgarg.kimai.data"
    compileSdk = 34

    defaultConfig { minSdk = 30 }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

dependencies {
    implementation(libs.androidx.core)

    implementation(libs.bundles.square)
    implementation(libs.androidx.room.ktx)
    ksp(libs.androidx.room.compiler)

    implementation(libs.kotlinx.serialization)
    implementation(project(":preferences"))
}
