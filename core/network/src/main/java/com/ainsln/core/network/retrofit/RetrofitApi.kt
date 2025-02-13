package com.ainsln.core.network.retrofit

import com.ainsln.core.network.model.AuthResponse
import com.ainsln.core.network.model.UserProfileResponse
import com.ainsln.core.network.model.WorkspaceResponse
import com.ainsln.core.network.model.auth.AuthBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Url

interface RetrofitApi {
    @POST
    suspend fun authenticate(@Url url: String, @Body request: AuthBody): AuthResponse

    @POST("/api/2.0/authentication/logout")
    suspend fun logout()

    @GET("/api/2.0/files/@my")
    suspend fun getMyDocuments(): Result<WorkspaceResponse>

    @GET("/api/2.0/files/@trash")
    suspend fun getTrash(): Result<WorkspaceResponse>

    @GET("/api/2.0/files/rooms")
    suspend fun getRooms(): Result<WorkspaceResponse>

    @GET("/api/2.0/people/@self")
    suspend fun getMyProfile(): Result<UserProfileResponse>
}
