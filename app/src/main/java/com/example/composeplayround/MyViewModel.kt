package com.example.composeplayround

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyViewModel : ViewModel() {
    private val _fakeList = MutableLiveData(
        ListItem(List(30) { index -> "goods $index" })
    )
    val fakeList: LiveData<ListItem> get() = _fakeList

    private val _thumbUpSet = mutableStateOf(HashSet<Int>())
    val thumbUpSet: State<Set<Int>> = _thumbUpSet

    fun toggleThumbUp(index: Int) {
        val newSet = _thumbUpSet.value.toMutableSet()
        if (newSet.contains(index)) {
            newSet.remove(index)
        } else {
            newSet.add(index)
        }
        _thumbUpSet.value = newSet as HashSet<Int>
    }

}

data class ListItem(val items: List<String>)
