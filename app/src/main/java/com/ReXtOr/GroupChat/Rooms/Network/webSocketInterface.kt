package com.ReXtOr.GroupChat.Rooms.Network

import kotlinx.coroutines.flow.StateFlow

interface webSocketInterface {
    suspend fun getwebsocketmsg(roomname:String):StateFlow<String>
}