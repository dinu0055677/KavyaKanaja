package com.example.kavyakanaja

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SettingsScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(20.dp)
    ) {
        Text(
            text = "Settings",
            color = Color.White,
            fontSize = 34.sp
        )

        Spacer(modifier = Modifier.height(24.dp))

        SettingItem("Account")
        SettingItem("Content and Display")
        SettingItem("Data Saver")
        SettingItem("Playback")
        SettingItem("Devices")
        SettingItem("Notifications")
        SettingItem("Audio Quality")
        SettingItem("Storage")
        SettingItem("Privacy and Social")
    }
}

@Composable
fun SettingItem(title: String) {
    Text(
        text = title,
        color = Color.White,
        fontSize = 24.sp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
    )
}
