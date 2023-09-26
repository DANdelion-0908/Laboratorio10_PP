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
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

var auth: FirebaseAuth = Firebase.auth

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignupScreen(navController: NavController) {

    var context = LocalContext.current

    Image(painter = painterResource(id = R.drawable.signup_screen), contentDescription = "Login Screen",
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop)

    Column (
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
        //verticalArrangement = Arrangement.SpaceBetween

    ) {
        var userEmail by rememberSaveable { mutableStateOf("") }
        var userPassword by rememberSaveable { mutableStateOf("") }
        var userName by rememberSaveable { mutableStateOf("") }
        var userPassWordVerification by rememberSaveable { mutableStateOf("") }

        DisplayMetrics()
        val screenHeight = with(LocalDensity.current) {
            LocalContext.current.resources.displayMetrics.heightPixels.toDp()
        }

        Spacer(modifier = Modifier.height(screenHeight / 2))

        TextField(
            value = userName, onValueChange = { userName = it},
            label = { Text ("Nombre de Usuario")},
            singleLine = true,
            shape = CircleShape,
            colors = TextFieldDefaults.textFieldColors(
                textColor = Color.White,
                containerColor = Color(0x00000000),
                unfocusedLabelColor = Color.White)
        )

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
            value = userPassword, onValueChange = { userPassword = it},
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

        TextField(
            value = userPassWordVerification, onValueChange = { userPassWordVerification = it},
            label = { Text ("Verifica tu Contraseña")},
            singleLine = true,
            shape = CircleShape,
            colors = TextFieldDefaults.textFieldColors(
                textColor = Color.White,
                containerColor = Color(0x00000000),
                unfocusedLabelColor = Color.White),
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )

        Spacer(modifier = Modifier.height(30.dp))

        Button(
            modifier = Modifier
                .width(190.dp)
                .height(60.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xffa167ba)),
            onClick = { signupFun(userEmail, userName, userPassword, userPassWordVerification, context, navController) }) {

            Text(text = "Registrarse")
        }

        Spacer(modifier = Modifier.height(1.dp))

        Text(
            color = Color.White,
            text = "¿Ya tienes una cuenta?")

        Button(
            modifier = Modifier
                .width(190.dp)
                .height(35.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0x00000000)),
            onClick = {
                navController.navigate(route = AppScreens.LoginScreen.route)
            }) {

            Text(text = "INICIAR SESIÓN",
                color = Color(0xffa167ba))
        }
    }
}

private fun signupFun(email: String, name: String, password: String, passwordVerification: String, context: Context, navController: NavController) {

    if (email.isBlank() || name.isBlank() || password.isBlank()) {
        Toast.makeText(context, "Asegúrate de llenar todos los campos", Toast.LENGTH_LONG).show()
        return
    }

    if (password != passwordVerification) {
        Toast.makeText(context, "Las contraseñas no coinciden", Toast.LENGTH_LONG).show()
        return
    }

    auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
        if (it.isSuccessful) {
            Toast.makeText(context, "Te has registrado correctamente", Toast.LENGTH_LONG).show()
            navController.navigate(route = AppScreens.LoginScreen.route)

        } else {
            Toast.makeText(context, "No te has podido registrar", Toast.LENGTH_LONG).show()
        }
    }
}

@Preview
@Composable
fun SignUpPreview() {
    AppNavigation()
}
