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
import com.ReXtOr.GroupChat.Rooms.Screens.CreateNewRoom



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

                CreateNewRoom()
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