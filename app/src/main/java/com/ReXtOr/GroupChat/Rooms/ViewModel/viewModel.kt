package com.ReXtOr.GroupChat.Rooms.ViewModel

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ReXtOr.GroupChat.Rooms.Network.Websocket
import com.ReXtOr.GroupChat.Rooms.Network.webSocketInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RoomviewModel @Inject constructor(
    private val savedStateHandle :SavedStateHandle,
    private val webSocketsession: Websocket
): ViewModel() {
    var room_name: String=""
    var _incomingmsges = MutableStateFlow<MutableList<String>>(mutableListOf(""))
    val incommingMsges :StateFlow<MutableList<String>>
        get() = _incomingmsges

    fun getmsgs(roomname:String){
        viewModelScope.launch(Dispatchers.IO){
            webSocketsession
                .getwebsocketmsg(roomname)

        }
        viewModelScope.launch {
            webSocketsession.returnValue.collectLatest {
                if (it.isNotBlank()){
                    _incomingmsges.value.add("anonymus - "+it)
                }

            }
        }


    }
    fun sendmsgs(msg:String){
        var boolean = false
        viewModelScope.launch(Dispatchers.IO) {
           boolean= webSocketsession.sendmsg(msg)
            if (boolean){
                if (msg.isNotBlank()){
                    _incomingmsges.value.add("you - " +msg)
                }
            }
        }


    }

    fun closeWebsession(){
        viewModelScope.launch {
            webSocketsession.closesession()
        }
    }

}