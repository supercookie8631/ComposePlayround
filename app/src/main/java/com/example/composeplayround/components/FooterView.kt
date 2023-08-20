package com.example.composeplayround.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.composeplayround.ListItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun FooterView(
    fakeList: ListItem,
    itemIndex: Int,
    lazyListState: LazyListState,
    coroutineScope: CoroutineScope,
    squareSizeWidth: Dp,
    showButton: Boolean,
    modifier: Modifier
) {
    AnimatedVisibility(
        visible = showButton,
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .border(1.dp, Color.Black)
                .background(Color.LightGray)
                .width(squareSizeWidth / 2)
                .height(squareSizeWidth / 4),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "剩餘 ${fakeList.items.size + 2 - itemIndex} 項商品"
            )
            IconButton(
                onClick = {
                    coroutineScope.launch {
                        lazyListState.scrollToItem(index = 0)
                    }
                },
                modifier = Modifier
                    .align(Alignment.BottomCenter)
            ) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowUp,
                    contentDescription = "KeyboardArrowUp",
                    tint = Color.Black
                )
            }
        }
    }
}
