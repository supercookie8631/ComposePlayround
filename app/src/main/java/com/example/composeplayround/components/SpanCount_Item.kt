package com.example.composeplayround.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material.icons.outlined.ThumbUp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.composeplayround.MyViewModel

@Composable
fun SpanCount_Item(viewModel: MyViewModel, index: Int, ItemText: String, Width: Dp, Height: Dp) {
    val thumbUpSet by viewModel.thumbUpSet
    val isThumbUp = thumbUpSet.contains(index)
    val currentIcon = if (isThumbUp) Icons.Default.ThumbUp else Icons.Outlined.ThumbUp
    Box(
        modifier = Modifier
            .width(Width)
            .height(Height)
            .border(1.dp, Color.Black),
        contentAlignment = Alignment.Center
    ) {
        Text(ItemText)
        IconButton(
            onClick = { viewModel.toggleThumbUp(index) },
            modifier = Modifier
                .align(Alignment.BottomEnd)
        ) {
            Icon(imageVector = currentIcon, contentDescription = "Thumb icon")
        }
    }
}