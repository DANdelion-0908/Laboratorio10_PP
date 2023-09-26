package com.example.loginlab4.ui.theme

sealed class AppScreens (val route : String) {
    object LoginScreen: AppScreens("login_screen")
    object SignupScreen: AppScreens("signup_screen")
}