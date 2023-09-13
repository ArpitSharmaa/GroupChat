package com.ReXtOr.GroupChat.Rooms.Repositort

import com.ReXtOr.GroupChat.Rooms.Models.listofLiveRooms

interface RepositoryInterface {
    suspend fun getallrooms():listofLiveRooms
}