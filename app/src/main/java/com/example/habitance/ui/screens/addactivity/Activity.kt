package com.example.habitance.ui.screens.addactivity

import java.util.Date

data class Activity(
    val name: String,
    val unit: String,
    val target: String,
    val category: String,
    val start: Date,
    val end: Date
)
