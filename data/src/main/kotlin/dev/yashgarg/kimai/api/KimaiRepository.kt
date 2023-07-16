/*
 * Copyright © 2023 Yash Garg.
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */
package dev.yashgarg.kimai.api

import dev.yashgarg.kimai.models.Activity
import dev.yashgarg.kimai.models.Ping
import dev.yashgarg.kimai.models.TimesheetActivity
import dev.yashgarg.kimai.util.ApiResult

interface KimaiRepository {
  suspend fun ping(): ApiResult<Ping>

  suspend fun getActivities(
    projectId: Int? = null,
    projects: List<Int> = emptyList(),
    visible: Int = 1,
    globals: Boolean? = null,
    orderBy: String? = null,
    order: String = "ASC",
    searchTerm: String? = null,
  ): ApiResult<List<Activity>>

  suspend fun getProjects(
    visible: Int = 1,
  ): ApiResult<List<Activity>>

  suspend fun getTimeSheets(
    orderBy: String? = null,
    order: String? = null,
  ): ApiResult<List<TimesheetActivity>>

  suspend fun createTimeSheet(
    begin: String,
    end: String? = null,
    description: String? = null,
    tags: List<String>? = null,
    project: Int? = null,
    activity: Int? = null,
  ): ApiResult<TimesheetActivity>
}
