package com.ReXtOr.GroupChat.Rooms.ViewModel

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ReXtOr.GroupChat.Rooms.Models.LiveRoomsNames
import com.ReXtOr.GroupChat.Rooms.Network.Websocket
import com.ReXtOr.GroupChat.Rooms.Network.webSocketInterface
import com.ReXtOr.GroupChat.Rooms.Repositort.RepositoryInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RoomviewModel @Inject constructor(
    private val repositoryInterface: RepositoryInterface,
    private val webSocketsession: Websocket
): ViewModel() {
    var job :Job? = null
    var room_name: String=""
    var _incomingmsges = MutableStateFlow<MutableList<String>>(mutableListOf(""))
    val incommingMsges :StateFlow<MutableList<String>>
        get() = _incomingmsges
    var _roomsActive = MutableStateFlow<List<LiveRoomsNames>>(listOf())
    val roomsSctive : StateFlow<List<LiveRoomsNames>>
        get() = _roomsActive

    fun connecttosession(roomname: String){
        Log.e("TAG", "connecttosession: callee", )
        viewModelScope.launch {
            webSocketsession
                .connect(roomname)
            getmsgs()
        }
    }
    fun getmsgs(){

        viewModelScope.launch(Dispatchers.IO){
            webSocketsession
                .getwebsocketmsg()

        }
        job =  viewModelScope.launch {

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
            Log.e("TAG", "closeWebsession: called", )
            webSocketsession.closesession()
        }
        job?.cancel()
    }

    fun getlistofrooms(){
        val z = viewModelScope.async {
            repositoryInterface.getallrooms()
        }
        viewModelScope.launch {
            _roomsActive.emit(z.await().listOfRooms)

        }

    }

}