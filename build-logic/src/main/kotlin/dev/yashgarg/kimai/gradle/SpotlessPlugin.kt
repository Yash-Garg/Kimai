/*
 * Copyright © 2023 Yash Garg.
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */
package dev.yashgarg.kimai.gradle

import com.diffplug.gradle.spotless.SpotlessExtension
import com.diffplug.gradle.spotless.SpotlessPlugin
import com.diffplug.spotless.LineEnding
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.getByType

@Suppress("Unused")
class SpotlessPlugin : Plugin<Project> {

  override fun apply(project: Project) {
    project.pluginManager.apply(SpotlessPlugin::class)
    project.extensions.getByType<SpotlessExtension>().run {
      /** Workaround for https://github.com/diffplug/spotless/issues/1644 */
      lineEndings = LineEnding.UNIX

      kotlin {
        ktfmt().googleStyle()
        target("**/*.kt")
        targetExclude("**/build/", "/spotless/")
        trimTrailingWhitespace()
        endWithNewline()
        licenseHeaderFile(project.file("spotless/license.kt"))
      }

      kotlinGradle {
        ktfmt().kotlinlangStyle()
        target("**/*.gradle.kts")
        targetExclude("**/build/")
        licenseHeaderFile(project.file("spotless/license.kt"), "import|plugins|@file")
      }

      format("xml") {
        target("**/*.xml")
        targetExclude("**/build/", ".idea/", "/spotless/", "**/lint-baseline.xml")
        trimTrailingWhitespace()
        indentWithSpaces()
        endWithNewline()
        licenseHeaderFile(
          project.file("spotless/license.xml"),
          "<manifest|<resources|<vector|<adaptive-icon|<full-backup-content|<data-extraction-rules"
        )
      }
    }
  }
}
