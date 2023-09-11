package com.ReXtOr.GroupChat.Rooms.Network

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.websocket.WebSockets
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

interface webSocketInterface {

    suspend fun getwebsocketmsg(roomname:String)
    suspend fun sendmsg(msg:String):Boolean
    suspend fun closesession()
}