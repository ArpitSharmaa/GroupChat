package com.ReXtOr.GroupChat.Rooms.Network

import androidx.compose.runtime.InternalComposeTracingApi
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @GET
    suspend fun getrooms():Response<String>

    @POST
    suspend fun resgisterRoom(@Body string: String):Response<String>
}