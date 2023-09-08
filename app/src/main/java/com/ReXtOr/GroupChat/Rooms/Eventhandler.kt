package com.ReXtOr.GroupChat.Rooms

sealed class Eventhandler{
    data class onclickbutton(val roomName:String): Eventhandler()


}
