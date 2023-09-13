package com.ReXtOr.GroupChat.Rooms.Repositort

import android.util.Log
import com.ReXtOr.GroupChat.Rooms.Models.listofLiveRooms
import com.ReXtOr.GroupChat.Rooms.Network.ApiService

class Repository(
    private val apiService: ApiService
) :RepositoryInterface{
    override suspend fun getallrooms(): listofLiveRooms {
       val response =  apiService.getrooms()
        if (response.isSuccessful){
            return response.body()!!
        }else{
            return listofLiveRooms(
                listOfRooms = listOf()
            )
        }
    }
}