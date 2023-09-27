package com.example.loginlab4

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.auth.FirebaseAuth

@Composable
fun SuccessScreen() {
    Image(painter = painterResource(id = R.drawable.success_screen), contentDescription = "Main Menu",
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop)

    Text(text = getToken().toString(),
        fontSize = 13.sp,
        modifier = Modifier.padding(65.dp))
}

private fun getToken(): Any? {
    val user = FirebaseAuth.getInstance().currentUser
    var token: Any? = null
    if (user != null) {
        token =  user.getIdToken(true).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                token = task.result?.token
            }
        }
    }
    return token
}

@Preview
@Composable
fun SuccessPreview() {
    SuccessScreen()
}