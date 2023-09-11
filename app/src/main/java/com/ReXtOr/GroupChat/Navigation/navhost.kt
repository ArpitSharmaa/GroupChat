package com.ReXtOr.GroupChat.Navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.ReXtOr.GroupChat.Rooms.Screens.ChatScreen
import com.ReXtOr.GroupChat.Rooms.Screens.CreateNewRoom
import com.ReXtOr.GroupChat.Rooms.ViewModel.RoomviewModel
import com.ReXtOr.GroupChat.ui.theme.Purple40
import com.ReXtOr.GroupChat.ui.theme.Purple80


@Composable
fun Navigation(){
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = navigationDestinations.GroupChatGraph.route
    ){
        navigation(
            startDestination = navigationDestinations.CreateRoom.route,
            route= navigationDestinations.GroupChatGraph.route,

        ){
            composable(
                route = navigationDestinations.CreateRoom.route
            ){
                val viewModel= it.getviewmodel<RoomviewModel>(navController = navController)
                CreateNewRoom(viewModel,navController)
            }
            composable(
                route = navigationDestinations.Chatroom.route
            ){
                val viewModel= it.getviewmodel<RoomviewModel>(navController = navController)
                ChatScreen(
                    roomviewModel = viewModel,
                    navController = navController
                )
            }

        }
    }
}
@Composable
inline fun <reified T: ViewModel> NavBackStackEntry.getviewmodel(navController: NavController):T{
    val route = destination.parent?.route
    val parent = remember(key1 = this) {

        navController.getBackStackEntry(route!!)

    }
    return hiltViewModel(parent)
}