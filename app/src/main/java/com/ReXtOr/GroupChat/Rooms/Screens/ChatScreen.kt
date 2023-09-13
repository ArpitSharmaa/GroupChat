package com.ReXtOr.GroupChat.Rooms.Screens

import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcher
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavHostController
import com.ReXtOr.GroupChat.Appbar
import com.ReXtOr.GroupChat.Rooms.ViewModel.RoomviewModel
import com.ReXtOr.GroupChat.ui.theme.DarkColorSchemeColir
import com.ReXtOr.GroupChat.ui.theme.LightColorSchemeColor
import com.ReXtOr.GroupChat.ui.theme.Purple40
import com.ReXtOr.GroupChat.ui.theme.Purple80
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen(roomviewModel: RoomviewModel, navController: NavHostController,onBackPressed:()->Unit ){

    val dispatcher = LocalOnBackPressedDispatcherOwner.current
    DisposableEffect(key1 = "BackHandler") {
        val backCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                // Call the provided onBackPressed function when the back button is pressed
                onBackPressed()
            }
        }

        // Register the callback

        dispatcher?.onBackPressedDispatcher?.addCallback(backCallback)

        // Remove the callback when the Composable is disposed
        onDispose {
            backCallback.remove()
        }
    }

    val context  = LocalContext.current
    val lifecleoberver= LocalLifecycleOwner.current
    DisposableEffect(lifecleoberver){
        val obsever = LifecycleEventObserver{_,event->
            if (event == Lifecycle.Event.ON_START){
                if (roomviewModel.room_name.isNotEmpty()){
                    try {
                        roomviewModel.connecttosession(roomviewModel.room_name)
                    }catch (e:Exception){
                        Toast.makeText(context, e.localizedMessage, Toast.LENGTH_SHORT).show()
                    }

                }
            }else if (event == Lifecycle.Event.ON_STOP){
                roomviewModel.closeWebsession()
            }

        }
        lifecleoberver.lifecycle.addObserver(obsever)

        onDispose {
            lifecleoberver.lifecycle.removeObserver(obsever)
        }

    }
    var msgsend by remember {
        mutableStateOf("")
    }

//    var sendedmsgs by remember {
//        mutableStateOf(listOf<String>())
//    }
    var msges by remember {
        mutableStateOf(listOf<String>())
    }
    val scope = rememberCoroutineScope()
//    DisposableEffect(key1 = "collect" ){
//
//       val z = scope.launch (Dispatchers.IO){
//            roomviewModel.incommingMsges.collectLatest {
//                if (it.first().isNotBlank())    {
//                    msges = msges+ it
//                }
//
//            }
//        }
//        onDispose {
//            z.cancel()
//        }
//    }



    var shouldrecompose by remember {
        mutableStateOf(false)
    }
    LaunchedEffect(key1 = shouldrecompose, block = {
        roomviewModel.incommingMsges.collectLatest {
            shouldrecompose = !shouldrecompose
        }
    })

//    var list : MutableList<String> = mutableListOf("Room")
//    val msges by rememberUpdatedState(newValue = list)
//    var coroutineScope = rememberCoroutineScope()
    val scrollableState = rememberLazyListState()
    Column (
        modifier = Modifier
            .fillMaxSize()
    ){
        Appbar(navHostController = navController,roomviewModel)

        LazyColumn(
            verticalArrangement = Arrangement.Bottom,
            modifier = Modifier
                .fillMaxHeight(0.9f),
            state = scrollableState
        ){
            items(
                items = roomviewModel.incommingMsges.value
            ){
                Column (
                horizontalAlignment = Alignment.Start
//                modifier = Modifier.fillMaxHeight(0.5f)
                ){
                    Text(

                        text = it,
                        modifier = Modifier
                            .padding(7.dp)
                            .background(
                                color = if (isSystemInDarkTheme()) {
                                    DarkColorSchemeColir.primary
                                } else {
                                    LightColorSchemeColor.primary
                                },
                                shape = RoundedCornerShape(10.dp)
                            )
                            .padding(7.dp),

                        fontSize = 20.sp,
                    )
                    Spacer(modifier = Modifier.fillMaxSize())
                }


            }

//            items(sendedmsgs){
//                Column(
//                    horizontalAlignment = Alignment.End
//                ) {
//                    Text(
//                        text = it,
//                        modifier = Modifier
//                            .background(
//                                color = Purple80,
//                                shape = RoundedCornerShape(10.dp)
//                            )
//                            .padding(14.dp),
//                        fontSize = 24.sp
//                    )
//                }
//            }
        }
        Row (
            verticalAlignment = Alignment.Bottom
        ){
            OutlinedTextField(
                value = msgsend,
                onValueChange = {
                    msgsend = it
                },
                modifier = Modifier
                    .height(IntrinsicSize.Max)
                    .fillMaxWidth(0.8f)
            )
            Button(
                onClick = {
                if (msgsend.isNotBlank()){
                    scope.launch {
                        roomviewModel.sendmsgs(msgsend)
                        msgsend =""
                        scrollableState.animateScrollToItem(roomviewModel.incommingMsges.value.size)
                    }

                }


            },
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(text = "^",
                    fontSize = 28.sp)
            }
        }
    }

    
}
