package com.ReXtOr.GroupChat.Rooms.Screens

import androidx.compose.foundation.background
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.ReXtOr.GroupChat.Appbar
import com.ReXtOr.GroupChat.Rooms.ViewModel.RoomviewModel
import com.ReXtOr.GroupChat.ui.theme.Purple40
import com.ReXtOr.GroupChat.ui.theme.Purple80
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen(roomviewModel: RoomviewModel, navController: NavHostController){

    LaunchedEffect(key1 = "CalledOnce"){
        if (roomviewModel.room_name.isNotEmpty()){
            roomviewModel.getmsgs(roomviewModel.room_name)
        }

    }
    var msgsend by remember {
        mutableStateOf("")
    }
    var sendedmsgs by remember {
        mutableStateOf(listOf<String>())
    }
    var msges by remember {
        mutableStateOf(listOf<String>())
    }
    val scope = rememberCoroutineScope()
    DisposableEffect(key1 = "collect" ){

       val z = scope.launch (Dispatchers.IO){
            roomviewModel.incommingMsges.collectLatest {
                if (it.first().isNotBlank())    {
                    msges = msges+ it
                }

            }
        }
        onDispose {
            z.cancel()
        }
    }

//    var list : MutableList<String> = mutableListOf("Room")
//    val msges by rememberUpdatedState(newValue = list)
//    var coroutineScope = rememberCoroutineScope()
    var scrollableState = rememberLazyListState()
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
                            .background(
                                color = Color.White,
                                shape = RoundedCornerShape(10.dp)
                            )
                            .padding(7.dp)
                            .background(
                                color = Purple80,
                                shape = RoundedCornerShape(10.dp)
                            )
                            .padding(7.dp),

                        fontSize = 24.sp,
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
                    .background(color = Purple40, shape = CircleShape)
            ) {
                Text(text = "^",
                    fontSize = 28.sp)
            }
        }
    }

    
}
