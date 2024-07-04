package com.example.marvel

import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter

@Stable
data class Env(
    var backgroundImage: Painter,
    var characters: Array<Character> = arrayOf(),
    val topLogo: Painter,
)

data class Character(
    val name: String,
    val painter: Painter,
    val backgroundColor: Color,
    val description: String = "",
    val logoUrl: String = "",
)

