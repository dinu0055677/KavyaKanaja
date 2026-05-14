package com.example.kavyakanaja

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import androidx.navigation.NavController

@Composable
fun DrawerMenu(navController: NavController, onSettingsClick: () -> Unit, onCloseDrawer: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(0.7f)
            .background(Color(0xFF121212))
            .padding(20.dp)
    ) {
        Spacer(modifier = Modifier.height(30.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = null,
                modifier = Modifier.size(60.dp)
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column {
                Text(
                    text = "Guest",
                    color = Color.White,
                    fontSize = 20.sp
                )

                Text(
                    text = "Sign in",
                    color = Color.LightGray
                )
            }
        }

        Spacer(modifier = Modifier.height(40.dp))

        DrawerItem("What's New", onClick = onCloseDrawer)
        DrawerItem("Listening History", onClick = onCloseDrawer)

        DrawerItem(
            title = "Settings and Privacy",
            onClick = {
                onSettingsClick()
                onCloseDrawer()
            }
        )

        Spacer(modifier = Modifier.weight(1f))

        DrawerItem(
            title = "Logout",
            onClick = {
                navController.navigate("login") {
                    popUpTo(0) { inclusive = true }
                }
                onCloseDrawer()
            }
        )
    }
}

@Composable
fun DrawerItem(
    title: String,
    onClick: () -> Unit = {}
) {
    Text(
        text = title,
        color = Color.White,
        fontSize = 22.sp,
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick()
            }
            .padding(vertical = 18.dp)
    )
}
