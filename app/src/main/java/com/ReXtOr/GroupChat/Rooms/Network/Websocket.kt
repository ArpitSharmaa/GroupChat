package com.ReXtOr.GroupChat.Rooms.Network

import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.websocket.WebSockets
import io.ktor.client.plugins.websocket.webSocket
import io.ktor.websocket.Frame
import io.ktor.websocket.WebSocketSession
import io.ktor.websocket.readText
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import okhttp3.WebSocket

class Websocket : webSocketInterface{
    val client = HttpClient(CIO){
        install(WebSockets)
    }
    val _returnvalue = MutableStateFlow<String>("")
    val returnValue : StateFlow<String>
        get() = _returnvalue
    override suspend fun getwebsocketmsg(roomname:String): StateFlow<String> {



            client.webSocket(
                host = "192.168.29.199",
                port = 8080,
                path = "chatroom/hi"
            ){
                for (frame in incoming){
                    val z = frame as Frame.Text
                    val value = z.readText()
                    Log.e("TAG", "getwebsocketmsg: $value", )
                    _returnvalue.value = z.readText()
                }
            }
        return returnValue
    }


}