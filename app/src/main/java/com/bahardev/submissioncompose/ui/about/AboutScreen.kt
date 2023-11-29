package com.bahardev.submissioncompose.ui.about

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

@Composable
fun AboutScreen(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = "https://bahardev.my.id/assets/img/bahardev.png",
                contentDescription = null,
                modifier = modifier
                    .height(200.dp)
                    .clip(RoundedCornerShape(20.dp))
            )
            Spacer(modifier = modifier.height(12.dp))
            Text(text = "Baharudin Zaelani", fontWeight = FontWeight.Bold, fontSize = 23.sp)
            Text(text = "bahardev1101@gmail.com")
        }
    }
}