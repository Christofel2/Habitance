package com.example.habitance.screen.FinisActivity

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp

@Composable
fun FinisPage(){
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = "Finis Screen", fontSize = 20.sp)
    }
}