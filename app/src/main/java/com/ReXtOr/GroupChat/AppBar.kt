package com.ReXtOr.GroupChat

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.ReXtOr.GroupChat.Rooms.ViewModel.RoomviewModel
import com.ReXtOr.GroupChat.ui.theme.Purple40
import com.ReXtOr.GroupChat.ui.theme.Purple80
@Composable
fun Appbar(navHostController: NavHostController, roomviewModel: RoomviewModel){
    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .background(Purple80)
                .fillMaxWidth(1f)
                .fillMaxHeight(0.06f)
                .border(
                    width = 1.dp,
                    color = Purple80,
                    shape = RoundedCornerShape(10)
                )
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back Botton",
                modifier = Modifier
                    .padding(10.dp)
                    .clickable(enabled = true
                    ){
                        roomviewModel.closeWebsession()
                        navHostController.navigateUp()

                    }

            )
            Spacer(modifier = Modifier.fillMaxWidth(0.2f))
            Text(
                text = "Random Chatz",
                modifier = Modifier.padding(10.dp),
                textAlign = TextAlign.Center,
                color = Purple40,
                fontSize = 20.sp,

                )
            Spacer(modifier = Modifier.fillMaxWidth(0.4f))
            Spacer(modifier = Modifier.fillMaxWidth(0.4f))
            Icon(
                imageVector = Icons.Default.AccountCircle,
                contentDescription = "Account",
                modifier = Modifier
                    .padding(10.dp)

            )
        }
    }
}