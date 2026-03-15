package com.example.dettol.screens.error

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.dettol.navigation.Destination


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ErrorScreen(
    message: String = "",
    navController: NavController
){
    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.height(64.dp)
                    .padding(horizontal = 4.dp, vertical = 5.dp)
                    .clip(RoundedCornerShape(50)),
                title = {

                },
                navigationIcon = {
                    Box(
                        contentAlignment = Alignment.CenterStart,
                        modifier = Modifier.fillMaxHeight()
                            .padding(start = 10.dp)
                            .clickable {
                                navController.popBackStack(
                                    Destination.SearchScreen.name,
                                    true,
                                    false
                                )
                            }
                    ){
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            null,
                            modifier = Modifier.size(30.dp)
                        )
                    }

                }
            )
        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
                .padding(16.dp).padding(it)
        ) {
            Text(
                text = "Error",
                fontSize = 64.sp
            )

            Text(
                text = message,
                fontSize = 32.sp
            )

        }
    }



}