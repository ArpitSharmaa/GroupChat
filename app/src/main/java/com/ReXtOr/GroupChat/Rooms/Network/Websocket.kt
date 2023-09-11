package com.ReXtOr.GroupChat.Rooms.Network

import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.websocket.WebSockets
import io.ktor.client.plugins.websocket.webSocket
import io.ktor.websocket.CloseReason
import io.ktor.websocket.Frame
import io.ktor.websocket.WebSocketSession
import io.ktor.websocket.close
import io.ktor.websocket.readText
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import okhttp3.WebSocket
import kotlin.math.log

class Websocket : webSocketInterface {
    val client = HttpClient(CIO) {
        install(WebSockets)
    }
    var session: WebSocketSession? = null
    val _returnvalue = MutableStateFlow<String>("")
    val returnValue: StateFlow<String>
        get() = _returnvalue

    override suspend fun getwebsocketmsg(roomname: String) {


        try {
            client.webSocket(
//                host = "192.168.29.199",
//                port = 8080,
//                path = "/chatroom/$roomname"
                "ws://ancient-blade-398510.el.r.appspot.com/chatroom/$roomname"
            ) {
                session = this
                for (frame in incoming) {
                    val z = frame as Frame.Text
                    val value = z.readText()
                    Log.e("TAG", "getwebsocketmsg:$value ")
                    _returnvalue.value = z.readText()
                }
            }
        } catch (_: Exception) {

        }

    }

    override suspend fun sendmsg(msg: String):Boolean {
        if (session != null) {
            val frame = Frame.Text(msg)
            session!!.send(frame)
            return true
        }else{
            return false
        }

    }

    override suspend fun closesession() {
        session?.close(CloseReason(CloseReason.Codes.NORMAL,"UserLeaves"))
    }


}