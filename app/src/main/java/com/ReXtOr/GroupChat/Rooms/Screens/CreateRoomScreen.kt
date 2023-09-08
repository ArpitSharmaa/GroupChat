package com.ReXtOr.GroupChat.Rooms.Screens

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight


import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ReXtOr.GroupChat.Rooms.ViewModel.RoomviewModel



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateNewRoom() {
    var _roomName by remember {
        mutableStateOf(
            value = ""
        )
    }
    val viewModel = hiltViewModel<RoomviewModel>()
    val msges by viewModel.incommingMsges.collectAsState()
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

            },
            modifier = Modifier,
            label = {
                Text("Enter The Name Of Your Room")
            },

            )
        Spacer(modifier = Modifier.fillMaxHeight(0.8f))
        FloatingActionButton(

            onClick = {
                viewModel.getmsgs()
                Log.e("CheckingMsgs", "CreateNewRoom: $msges", )
            },
            modifier = Modifier
                .align(Alignment.End)
        ) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = "Move Forward",
//                modifier = Modifier.fillMaxHeight(0.8f)
            )
        }
    }
}








