package com.example.loginlab4

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource

@Composable
fun SuccessScreen() {
Image(painter = painterResource(id = R.drawable.success_screen), contentDescription = "Main Menu",
    modifier = Modifier.fillMaxSize(),
    contentScale = ContentScale.Crop)
}