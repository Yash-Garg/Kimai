/*
 * Copyright © 2023 Yash Garg.
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins { `kotlin-dsl` }

dependencies {
    implementation(libs.build.spotless)
    implementation(libs.build.kotlin)
}

afterEvaluate {
    tasks.withType<JavaCompile>().configureEach {
        sourceCompatibility = JavaVersion.VERSION_17.toString()
        targetCompatibility = JavaVersion.VERSION_17.toString()
    }

    tasks.withType<KotlinCompile>().configureEach {
        kotlinOptions { jvmTarget = JavaVersion.VERSION_17.toString() }
    }
}

gradlePlugin {
    plugins {
        register("spotless") {
            id = "dev.yashgarg.kimai.spotless"
            implementationClass = "dev.yashgarg.kimai.gradle.SpotlessPlugin"
        }
        register("githooks") {
            id = "dev.yashgarg.kimai.githooks"
            implementationClass = "dev.yashgarg.kimai.gradle.GitHooksPlugin"
        }
    }
}
