package com.example.pullrequests.network

import com.example.pullrequests.model.PullRequest
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NetworkInterface {
    @GET("/repos/{owner}/{repo}/pulls")
    suspend fun getPullRequests(
        @Path("owner") owner: String,
        @Path("repo") repo: String,
        @Query("state") state: String = "closed",
        @Query("page") page: Int,
        @Query("per_page") pageSize: Int
    ): List<PullRequest>
}
