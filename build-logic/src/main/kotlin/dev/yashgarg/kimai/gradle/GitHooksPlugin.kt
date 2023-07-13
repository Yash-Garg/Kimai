/*
 * Copyright © 2023 Yash Garg.
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */
package dev.yashgarg.kimai.gradle

import org.apache.tools.ant.taskdefs.condition.Os
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.Copy
import org.gradle.api.tasks.Exec
import org.gradle.kotlin.dsl.register

@Suppress("Unused")
class GitHooksPlugin : Plugin<Project> {

  override fun apply(project: Project) {
    project.tasks.register<Copy>("copyGitHooks") {
      description = "Copies the git hooks from /hooks to the .git/hooks folder."
      from("${project.rootDir}/hooks/") {
        include("**/*.sh")
        rename("(.*).sh", "$1")
      }
      into("${project.rootDir}/.git/hooks")
    }

    project.tasks.register<Exec>("installGitHooks") {
      description = "Installs the pre-commit hooks with permissions"
      commandLine("chmod", "-R", "+x", ".git/hooks/")
      onlyIf { !Os.isFamily(Os.FAMILY_WINDOWS) }
    }
  }
}
