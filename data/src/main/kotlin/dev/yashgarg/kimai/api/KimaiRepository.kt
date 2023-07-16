/*
 * Copyright © 2023 Yash Garg.
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */
package dev.yashgarg.kimai.api

import dev.yashgarg.kimai.models.Activity
import dev.yashgarg.kimai.models.Ping
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
}
