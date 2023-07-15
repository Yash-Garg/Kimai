/*
 * Copyright © 2023 Yash Garg.
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */
package dev.yashgarg.kimai.api

import dev.yashgarg.kimai.models.Activity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface KimaiApi {
  @GET("activities")
  /**
   * Returns a collection of activities (which are visible to the user)
   *
   * @param projectId Project ID to filter activities
   * @param projects List of project IDs to filter activities
   * @param visible Visibility status to filter activities: 1=visible, 2=hidden, 3=all
   * @param globals Use if you want to fetch only global activities. Allowed values: true
   * @param orderBy The field by which results will be ordered. Allowed values: id, name, project
   * @param order The result order. Allowed values: ASC, DESC
   * @param searchTerm Free search term
   * @return A collection of activities
   */
  suspend fun getActivities(
    @Query("project") projectId: Int? = null,
    @Query("projects") projects: List<Int> = emptyList(),
    @Query("visible") visible: Int = 1,
    @Query("globals") globals: Boolean? = null,
    @Query("orderBy") orderBy: String? = null,
    @Query("order") order: String = "ASC",
    @Query("term") searchTerm: String? = null,
  ): Response<Activity>

  companion object {
    const val BASE_URL = "https://demo.kimai.org/api/"
  }
}
