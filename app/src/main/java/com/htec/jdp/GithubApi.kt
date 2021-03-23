package com.htec.jdp

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubApi {
    @GET("users/{user}/repos")
    fun getReposForUser(@Path("user") user: String): Single<List<RepoModel>>
}