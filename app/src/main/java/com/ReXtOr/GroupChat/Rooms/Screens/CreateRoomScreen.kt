package com.ReXtOr.GroupChat.Rooms.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight


import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.ReXtOr.GroupChat.Appbar
import com.ReXtOr.GroupChat.Navigation.navigationDestinations
import com.ReXtOr.GroupChat.Rooms.ViewModel.RoomviewModel
import com.ReXtOr.GroupChat.ui.theme.Purple40


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateNewRoom(viewModel: RoomviewModel, navController: NavHostController) {
    Appbar(navHostController = navController, roomviewModel = viewModel)
    var _roomName by remember {
        mutableStateOf(
            value = ""
        )
    }
    var isErrorapplied by remember {
        mutableStateOf(false)
    }
    Column(
        modifier = Modifier
            .fillMaxSize(1f)
            .padding(20.dp),
//        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.fillMaxHeight(0.4f))
        OutlinedTextField(
            value = _roomName,
            onValueChange = { roomName ->
                _roomName = roomName
                isErrorapplied = false
            },
            modifier = Modifier,
            label = {
                Text("Enter The Name Of Your Room")
            },
            isError = isErrorapplied

            )
        Spacer(modifier = Modifier.fillMaxHeight(0.8f))
        FloatingActionButton(

            onClick = {
                if (_roomName.isEmpty()){
                    isErrorapplied= true
                }else{
                    viewModel.room_name = _roomName
                    navController.navigate(navigationDestinations.Chatroom.route)
                }


            },
            modifier = Modifier
                .align(Alignment.End)
                .background(color = Purple40, shape = CircleShape)
        ) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = "Move Forward",
//                modifier = Modifier.fillMaxHeight(0.8f)
            )
        }
    }
}








