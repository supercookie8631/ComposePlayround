package com.example.composeplayround

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import com.example.composeplayround.components.FilterBar
import com.example.composeplayround.components.NineGrid
import com.example.composeplayround.ui.theme.ComposePlayroundTheme
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.composeplayround.components.FooterView
import com.example.composeplayround.components.SpanCount_Item

val SPACING = 16.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposePlayroundTheme {
                MyScreen()
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MyScreen(viewModel: MyViewModel = viewModel()) {
    val fakeList by viewModel.fakeList.observeAsState(initial = ListItem(emptyList()))

    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val squareSizeWidth = (screenWidth - (2 * SPACING))
    val squareSize = (screenWidth - (3 * SPACING)) / 2

    var isGridView by remember { mutableStateOf(true) }
    val lazyListState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(SPACING)
    ) {
        LazyColumn(
            state = lazyListState,
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(SPACING)
        ) {
            item {
                NineGrid()
            }
            stickyHeader {
                FilterBar(onClick = {
                    isGridView = !isGridView
                })
            }
            items(fakeList.items.size) { index ->
                AnimatedVisibility(
                    visible = !isGridView,
                    enter = fadeIn() + expandVertically(),
                    exit = fadeOut() + shrinkVertically()
                ) {
                    SpanCount_Item(
                        viewModel,
                        index,
                        fakeList.items[index],
                        squareSizeWidth,
                        squareSize
                    )
                }
                if (isGridView && index % 2 == 0) {
                    val nextItem = fakeList.items.getOrNull(index + 1)
                    Crossfade(targetState = nextItem) { next ->
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(SPACING)
                        ) {
                            SpanCount_Item(
                                viewModel,
                                index,
                                fakeList.items[index],
                                squareSize,
                                squareSize
                            )
                            if (next != null) {
                                SpanCount_Item(viewModel, index + 1, next, squareSize, squareSize)
                            }
                        }
                    }
                }
            }
        }
        val showButton by remember {
            derivedStateOf {
                lazyListState.firstVisibleItemIndex > 0
            }
        }
        val itemIndex by remember {
            derivedStateOf {
                lazyListState.firstVisibleItemIndex + lazyListState.layoutInfo.visibleItemsInfo.size - 1
            }
        }
        FooterView(
            fakeList = fakeList,
            itemIndex = itemIndex,
            lazyListState = lazyListState,
            coroutineScope = coroutineScope,
            squareSizeWidth = squareSizeWidth,
            showButton = showButton,
            modifier = Modifier
                .align(Alignment.BottomCenter)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MyScreenPreview() {
    MyScreen()
}
