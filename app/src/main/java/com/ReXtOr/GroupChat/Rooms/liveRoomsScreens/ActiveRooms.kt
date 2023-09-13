package com.ReXtOr.GroupChat.Rooms.liveRoomsScreens

import android.graphics.drawable.shapes.OvalShape
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.ReXtOr.GroupChat.Appbar
import com.ReXtOr.GroupChat.Navigation.navigationDestinations
import com.ReXtOr.GroupChat.Rooms.ViewModel.RoomviewModel
import com.ReXtOr.GroupChat.ui.theme.DarkColorSchemeColir
import com.ReXtOr.GroupChat.ui.theme.LightColorSchemeColor
import com.ReXtOr.GroupChat.ui.theme.Purple80

@Composable
fun ActiveRooms(
    roomviewModel: RoomviewModel,
    navController: NavHostController
){
    var reload by remember {
        mutableStateOf(true)
    }
    LaunchedEffect(key1 = reload){
        roomviewModel.getlistofrooms()
    }
    Column {
        Appbar(navHostController = navController, roomviewModel =roomviewModel )
        Icon(
            imageVector = Icons.Default.Refresh,
            contentDescription = "Refresh",
            modifier = Modifier
                .clickable {
                    reload = !reload
                }
            )
        LazyColumn(
            modifier = Modifier
                .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally
        ){

            items(
                items=  roomviewModel.roomsSctive.value
            ){
                Column (
                    horizontalAlignment = Alignment.CenterHorizontally
                ){

                    Text(
                        text = it.nameOfRoom,
                        fontWeight = FontWeight.Black,
                        modifier = Modifier
                            .clickable {
                                roomviewModel.room_name = it.nameOfRoom
                                navController.navigate(navigationDestinations.Chatroom.route)
                            }
                            .padding(7.dp)
                            .background(
                                color = if (isSystemInDarkTheme()) {
                                    DarkColorSchemeColir.primary
                                } else {
                                    LightColorSchemeColor.primary
                                },
                                shape = RoundedCornerShape(5.dp)
                            )
                            .padding(7.dp),
                        fontSize = 24.sp
                    )
                }
            }
        }
    }

}