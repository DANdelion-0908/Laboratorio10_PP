package com.example.loginlab4

import android.content.Context
import android.util.DisplayMetrics
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.loginlab4.ui.theme.AppScreens
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.GetTokenResult

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavController) {
    val context = LocalContext.current

    Image(painter = painterResource(id = R.drawable.login_screen), contentDescription = "Login Screen",
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop)

    Column (
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
        //verticalArrangement = Arrangement.SpaceBetween

    ) {
        var userEmail by rememberSaveable { mutableStateOf("") }
        var userPassWord by rememberSaveable { mutableStateOf("") }

        DisplayMetrics()
        val screenHeight = with(LocalDensity.current) {
            LocalContext.current.resources.displayMetrics.heightPixels.toDp()
        }

        Spacer(modifier = Modifier.height(screenHeight / 2))

        TextField(
            value = userEmail, onValueChange = { userEmail = it},
            label = { Text ("Correo Electrónico")},
            singleLine = true,
            shape = CircleShape,
            colors = TextFieldDefaults.textFieldColors(
                textColor = Color.White,
                containerColor = Color(0x00000000),
                unfocusedLabelColor = Color.White)
            )

        TextField(
            value = userPassWord, onValueChange = { userPassWord = it},
            label = { Text ("Contraseña")},
            singleLine = true,
            shape = CircleShape,
            colors = TextFieldDefaults.textFieldColors(
                textColor = Color.White,
                containerColor = Color(0x00000000),
                unfocusedLabelColor = Color.White),
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )

        Spacer(modifier = Modifier.height(80.dp))

        Button(
            modifier = Modifier
                .width(190.dp)
                .height(60.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xffa167ba)),
            onClick = { loginFun(userEmail, userPassWord, context, navController) }) {

            Text(text = "Iniciar Sesión")
        }

        Spacer(modifier = Modifier.height(9.dp))

        Text(
            color = Color.White,
            text = "¿No tienes una cuenta?")

        Button(
            modifier = Modifier
                .width(190.dp)
                .height(35.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0x00000000)),
            onClick = {
                navController.navigate(route = AppScreens.SignupScreen.route)
            }) {

            Text(text = "REGISTRO",
                color = Color(0xffa167ba))
        }
    }
}

private fun loginFun(email: String, password: String, context: Context, navController: NavController) {

    if (email.isBlank() || password.isBlank()) {
        Toast.makeText(context, "Asegúrate de llenar todos los campos", Toast.LENGTH_LONG).show()
        return
    }

    auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
        if (it.isSuccessful) {
            Toast.makeText(context, "Iniciaste sesión correctamente", Toast.LENGTH_LONG).show()
            navController.navigate(route = AppScreens.SuccessScreen.route)

        } else {
            Toast.makeText(context, "Correo o contraseña inválidos.", Toast.LENGTH_LONG).show()
        }
    }
}

@Preview
@Composable
fun LoginPreview() {
    AppNavigation()
}
