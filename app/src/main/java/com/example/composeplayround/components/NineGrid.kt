package com.example.composeplayround.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.composeplayround.SPACING

@Composable
fun NineGrid() {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(SPACING)
        ) {
            repeat(3) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(SPACING),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    SquareItem(Alignment.CenterStart)
                    SquareItem()
                    SquareItem(Alignment.CenterEnd)
                }
            }
        }
    }
}