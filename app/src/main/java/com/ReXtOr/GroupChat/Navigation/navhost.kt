package com.ReXtOr.GroupChat.Navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
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
import com.ReXtOr.GroupChat.Rooms.Screens.NavigateScreen
import com.ReXtOr.GroupChat.Rooms.ViewModel.RoomviewModel
import com.ReXtOr.GroupChat.Rooms.liveRoomsScreens.ActiveRooms


@Composable
fun Navigation(){
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = navigationDestinations.GroupChatGraph.route
    ){
        navigation(
            startDestination = navigationDestinations.NavigateScreen.route,
            route= navigationDestinations.GroupChatGraph.route,

        ){
            composable(
                route = navigationDestinations.NavigateScreen.route
            ){
                NavigateScreen(navHostController = navController)
            }
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
                ){
                    viewModel.closeWebsession()
                    navController.navigateUp()
                }
            }
            composable(
                route = navigationDestinations.Activerooms.route
            ){
                val viewModel = it.getviewmodel<RoomviewModel>(navController = navController)
                ActiveRooms(
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