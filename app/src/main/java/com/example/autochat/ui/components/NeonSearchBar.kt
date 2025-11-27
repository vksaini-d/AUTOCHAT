package com.example.autochat.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.autochat.ui.theme.NeonPink
import com.example.autochat.ui.theme.NeonCyan

@Composable
fun NeonSearchBar(
    modifier: Modifier = Modifier,
    onSearch: (String) -> Unit
) {
    var text by remember { mutableStateOf("") }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(60.dp)
            .padding(horizontal = 16.dp)
            .shadow(
                elevation = 10.dp,
                shape = RoundedCornerShape(30.dp),
                spotColor = NeonPink,
                ambientColor = NeonPink
            )
            .background(Color.Black, RoundedCornerShape(30.dp))
            .border(
                width = 2.dp,
                brush = Brush.horizontalGradient(listOf(NeonPink, NeonCyan)),
                shape = RoundedCornerShape(30.dp)
            )
            .padding(horizontal = 16.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxSize()
        ) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search",
                tint = Color.White.copy(alpha = 0.7f),
                modifier = Modifier.size(24.dp)
            )
            
            Spacer(modifier = Modifier.width(8.dp))
            
            BasicTextField(
                value = text,
                onValueChange = { 
                    text = it
                    onSearch(it)
                },
                textStyle = TextStyle(color = Color.White, fontSize = 16.sp),
                cursorBrush = SolidColor(NeonCyan),
                decorationBox = { innerTextField ->
                    if (text.isEmpty()) {
                        Text(
                            text = "Search conversations or commands...",
                            color = Color.Gray,
                            fontSize = 16.sp
                        )
                    }
                    innerTextField()
                },
                modifier = Modifier.weight(1f)
            )
            
            // Filter Icon / Magic Wand placeholder
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .background(
                        brush = Brush.linearGradient(listOf(NeonPink, NeonCyan)),
                        shape = RoundedCornerShape(8.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text("Y", color = Color.White, fontSize = 12.sp) // Placeholder for filter icon
            }
        }
    }
}
