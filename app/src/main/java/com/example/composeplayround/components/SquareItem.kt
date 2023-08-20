package com.example.composeplayround.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import com.example.composeplayround.SPACING

@Composable
fun SquareItem(contentAlignment: Alignment = Alignment.Center) {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val squareSize = (screenWidth - (4 * SPACING)) / 3 //減去總填充量
    Box(
        modifier = Modifier
            .size(squareSize)
            .border(1.dp, Color.Black),
        contentAlignment = contentAlignment
    ) {
        Text(text = "promo")
    }
}