/*
 * Copyright © 2023 Yash Garg.
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */
package dev.yashgarg.kimai.api

import dev.yashgarg.kimai.models.Activity
import dev.yashgarg.kimai.models.Customer
import dev.yashgarg.kimai.models.Ping
import dev.yashgarg.kimai.models.TimesheetActivity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface KimaiApi {
  @GET("ping")
  /** A simple route that returns a 'pong', which you can use for testing the API */
  suspend fun ping(): Response<Ping>

  @GET("activities")
  /** Returns a collection of activities (which are visible to the user) */
  suspend fun getActivities(
    @Query("project") projectId: Int? = null,
    @Query("projects") projects: List<Int> = emptyList(),
    @Query("visible") visible: Int = 1,
    @Query("globals") globals: Boolean? = null,
    @Query("orderBy") orderBy: String? = null,
    @Query("order") order: String = "ASC",
    @Query("term") searchTerm: String? = null,
  ): Response<List<Activity>>

  @GET("projects")
  /** Returns a collection of projects (which are visible to the user) */
  suspend fun getProjects(
    @Query("visible") visible: Int = 1,
  ): Response<List<Activity>>

  @GET("timesheets")
  /** Returns a collection of timesheet records (which are visible to the user) */
  suspend fun getTimesheets(
    @Query("orderBy") orderBy: String? = null,
    @Query("order") order: String? = null,
  ): Response<List<TimesheetActivity>>

  @GET("customers")
  /** Returns a collection of customers (which are visible to the user) */
  suspend fun getCustomers(
    @Query("visible") visible: Int = 1,
  ): Response<List<Customer>>

  @POST("timesheets")
  /** Creates a new timesheet record */
  suspend fun createTimesheet(
    @Query("begin") begin: String,
    @Query("end") end: String? = null,
    @Query("description") description: String? = null,
    @Query("tags") tags: List<String>? = null,
    @Query("project") project: Int? = null,
    @Query("activity") activity: Int? = null,
  ): Response<TimesheetActivity>

  companion object {
    const val BASE_URL = "https://demo.kimai.org/api/"
  }
}
