package com.ReXtOr.GroupChat.Rooms.ViewModel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ReXtOr.GroupChat.Rooms.Network.webSocketInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RoomviewModel @Inject constructor(
    private val savedStateHandle :SavedStateHandle,
    private val webSocketsession: webSocketInterface
): ViewModel() {
    val _incomingmsges = MutableStateFlow("")
    val incommingMsges :StateFlow<String>
        get() = _incomingmsges

    fun getmsgs(){
        viewModelScope.launch {
            webSocketsession
                .getwebsocketmsg("hi")
                .collectLatest {
                    _incomingmsges.value = it
                }
        }
    }

}