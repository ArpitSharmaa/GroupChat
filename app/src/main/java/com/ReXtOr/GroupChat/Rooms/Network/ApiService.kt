package com.ReXtOr.GroupChat.Rooms.Network

import androidx.compose.runtime.InternalComposeTracingApi
import com.ReXtOr.GroupChat.Rooms.Models.listofLiveRooms
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @GET("/registeredroom")
    suspend fun getrooms():Response<listofLiveRooms>

}