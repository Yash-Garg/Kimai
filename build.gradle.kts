/*
 * Copyright © 2023 Yash Garg.
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */
@file:Suppress("DSL_SCOPE_VIOLATION")

import com.android.build.gradle.internal.tasks.factory.dependsOn

plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.anvil) apply false
    alias(libs.plugins.kotlinAndroid) apply false
    alias(libs.plugins.kotlinKsp) apply false
    alias(libs.plugins.kotlinSerialization) apply false
    alias(libs.plugins.whetstone) apply false
    id("dev.yashgarg.kimai.spotless")
    id("dev.yashgarg.kimai.githooks")
}

val clean by tasks.existing(Delete::class) { delete(rootProject.layout.buildDirectory) }

afterEvaluate {
    tasks.prepareKotlinBuildScriptModel.dependsOn(tasks.copyGitHooks, tasks.installGitHooks)
}
