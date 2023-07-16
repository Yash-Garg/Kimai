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
import dev.yashgarg.kimai.util.handleApi
import javax.inject.Inject

class KimaiRepositoryImpl @Inject constructor(private val api: KimaiApi) : KimaiRepository {
  override suspend fun ping(): ApiResult<Ping> = handleApi { api.ping() }

  override suspend fun getActivities(
    projectId: Int?,
    projects: List<Int>,
    visible: Int,
    globals: Boolean?,
    orderBy: String?,
    order: String,
    searchTerm: String?
  ): ApiResult<List<Activity>> = handleApi { api.getActivities() }

  override suspend fun getProjects(visible: Int): ApiResult<List<Activity>> = handleApi {
    api.getProjects()
  }

  override suspend fun getTimeSheets(
    orderBy: String?,
    order: String?
  ): ApiResult<List<TimesheetActivity>> = handleApi { api.getTimesheets() }
}
