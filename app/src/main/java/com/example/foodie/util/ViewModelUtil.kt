package com.example.foodie.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

fun ViewModel.launch(func: suspend CoroutineScope.() -> Unit): Job {
    return viewModelScope.launch { func() }
}