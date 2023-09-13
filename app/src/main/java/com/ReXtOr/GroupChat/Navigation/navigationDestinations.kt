package com.ReXtOr.GroupChat.Navigation

sealed class navigationDestinations(val route:String) {
    object GroupChatGraph:navigationDestinations("GroupChatRoute")
    object CreateRoom:navigationDestinations("createRoom")
    object Chatroom:navigationDestinations("chatscreen")
    object Activerooms:navigationDestinations("activerooms")
    object NavigateScreen:navigationDestinations("navigateScreen")

}