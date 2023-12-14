package com.example.foodie.ui.presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph

@RootNavGraph(start = true)
@Destination
@Composable
fun HomePage(viewModel: HomeViewModel = hiltViewModel()) {
    Column() {
        LazyColumn(state = rememberLazyListState()) {
            items(items = viewModel.recipeList) { recipe ->
                Text(text = recipe.name, color = Color.Black)
                
              
            }
        }
    }
}