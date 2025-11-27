package com.example.autochat.ui.screens

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.autochat.ui.components.GalaxyBackground
import com.example.autochat.ui.components.LampMascot
import com.example.autochat.ui.components.LampState
import com.example.autochat.ui.theme.NeonGreen

@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit
) {
    var lampState by remember { mutableStateOf(LampState.SAD) }
    var showForm by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()) {
        // Background
        GalaxyBackground()

        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Lamp Section (Left)
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                contentAlignment = Alignment.Center
            ) {
                LampMascot(
                    state = lampState,
                    onPull = {
                        lampState = LampState.HAPPY
                        showForm = true
                    }
                )
            }

            // Form Section (Right - Slides in)
            AnimatedVisibility(
                visible = showForm,
                enter = slideInHorizontally(
                    initialOffsetX = { it }, // Slide in from right
                    animationSpec = tween(1000)
                ) + fadeIn(),
                modifier = Modifier.weight(1f)
            ) {
                Card(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFF101020)),
                    shape = RoundedCornerShape(16.dp),
                    border = androidx.compose.foundation.BorderStroke(1.dp, NeonGreen)
                ) {
                    Column(
                        modifier = Modifier.padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            "Welcome Back",
                            color = Color.White,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        
                        OutlinedTextField(
                            value = "",
                            onValueChange = {},
                            label = { Text("Username") },
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = NeonGreen,
                                unfocusedBorderColor = Color.Gray,
                                focusedLabelColor = NeonGreen,
                                unfocusedLabelColor = Color.Gray
                            ),
                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        
                        OutlinedTextField(
                            value = "",
                            onValueChange = {},
                            label = { Text("Password") },
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = NeonGreen,
                                unfocusedBorderColor = Color.Gray,
                                focusedLabelColor = NeonGreen,
                                unfocusedLabelColor = Color.Gray
                            ),
                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(24.dp))
                        
                        Button(
                            onClick = onLoginSuccess,
                            colors = ButtonDefaults.buttonColors(containerColor = NeonGreen),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("Login", color = Color.Black)
                        }
                    }
                }
            }
        }
    }
}
