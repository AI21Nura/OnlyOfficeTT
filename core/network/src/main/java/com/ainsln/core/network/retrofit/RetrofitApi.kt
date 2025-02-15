package com.ainsln.core.network.retrofit

import com.ainsln.core.network.model.AuthResponse
import com.ainsln.core.network.model.UserProfileResponse
import com.ainsln.core.network.model.StorageResponse
import com.ainsln.core.network.model.auth.AuthBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Url

interface RetrofitApi {
    @POST
    suspend fun authenticate(@Url url: String, @Body request: AuthBody): AuthResponse

    @POST("/api/2.0/authentication/logout")
    suspend fun logout()

    @GET("/api/2.0/files/@my")
    suspend fun getMyDocuments(): Result<StorageResponse>

    @GET("/api/2.0/files/{id}")
    suspend fun getFolderById(@Path("id") id: Long): Result<StorageResponse>

    @GET("/api/2.0/files/@trash")
    suspend fun getTrash(): Result<StorageResponse>

    @GET("/api/2.0/files/rooms")
    suspend fun getRooms(): Result<StorageResponse>

    @GET("/api/2.0/people/@self")
    suspend fun getMyProfile(): Result<UserProfileResponse>
}
