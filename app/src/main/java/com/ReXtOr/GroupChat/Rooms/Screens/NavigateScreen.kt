package com.ReXtOr.GroupChat.Rooms.Screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.navigation.NavHostController
import com.ReXtOr.GroupChat.Navigation.navigationDestinations

@Composable
fun NavigateScreen(navHostController: NavHostController){
    Column (
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Button(onClick = {
            navHostController.navigate(navigationDestinations.CreateRoom.route)
        }) {
            Text(text = "Make Your Own Room")
        }

        Button(onClick = {
            navHostController.navigate(navigationDestinations.Activerooms.route)
        }) {
            Text(text = "Active Rooms")
        }
    }
}