package com.example.autochat.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.draw.alpha
import com.example.autochat.ui.components.HypnoticRing
import com.example.autochat.ui.components.NeonSearchBar

@Composable
fun DashboardScreen() {
    var masterToggle by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()) {
        // Background
        HypnoticRing(modifier = Modifier.alpha(0.3f)) // Dimmed background

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 40.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Top Control
            NeonSearchBar(
                onSearch = { /* TODO */ },
                modifier = Modifier.padding(16.dp)
            )

            Spacer(modifier = Modifier.height(40.dp))

            // Master Toggle
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    "Master Auto-Reply",
                    color = Color.White,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(end = 16.dp)
                )
                Switch(
                    checked = masterToggle,
                    onCheckedChange = { masterToggle = it }
                )
            }

            // Stats Placeholder
            Text(
                "Replies Sent: 0",
                color = Color.Gray,
                fontSize = 14.sp
            )
        }
    }
}


