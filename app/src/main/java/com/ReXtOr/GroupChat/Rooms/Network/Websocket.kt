package com.ReXtOr.GroupChat.Rooms.Network

import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.websocket.WebSockets
import io.ktor.client.plugins.websocket.webSocket
import io.ktor.client.plugins.websocket.webSocketSession
import io.ktor.client.request.url
import io.ktor.websocket.CloseReason
import io.ktor.websocket.Frame
import io.ktor.websocket.WebSocketSession
import io.ktor.websocket.close
import io.ktor.websocket.readText
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import okhttp3.WebSocket
import kotlin.math.log

class Websocket (private val client: HttpClient): webSocketInterface {
    var session: WebSocketSession? = null
    val _returnvalue = MutableStateFlow<String>("")
    val returnValue: StateFlow<String>
        get() = _returnvalue

    override suspend fun connect(roomname:String) {
        session = client.webSocketSession {
//            url("ws://192.168.29.199:8080/chatroom/$roomname")
            url("ws://ancient-blade-398510.el.r.appspot.com/chatroom/$roomname")
        }
    }


    override suspend fun getwebsocketmsg() {

        try {

               session!!.incoming
                   .receiveAsFlow()
                   .collectLatest {

                       _returnvalue.value = (it as Frame.Text).readText()

                   }



    }catch (_:Exception){

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
        if (session != null) {
//            Log.e("TAG", "closesession: called session", )
            session?.close(CloseReason(CloseReason.Codes.GOING_AWAY,"User Left"))
        }else{
            Log.e("TAG", "closesession: called session", )
        }

    }


}