package com.ReXtOr.GroupChat.Navigation

sealed class navigationDestinations(val route:String) {
    object GroupChatGraph:navigationDestinations("GroupChatRoute")
    object CreateRoom:navigationDestinations("createRoom")

}