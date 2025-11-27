package com.example.autochat

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.autochat.ui.screens.DashboardScreen
import com.example.autochat.ui.screens.LoginScreen
import com.example.autochat.ui.theme.AutoChatTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AutoChatTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    var isLoggedIn by remember { mutableStateOf(false) }
                    
                    if (!isLoggedIn) {
                        LoginScreen(onLoginSuccess = { isLoggedIn = true })
                    } else {
                        DashboardScreen()
                    }
                }
            }
        }
    }
}
