package com.example.loginlab4.ui.theme

sealed class AppScreens (val route : String) {
    object SignInScreen: AppScreens("signin_screen")
    object SignupScreen: AppScreens("signup_screen")
    object ProfileScreen: AppScreens("profile")
}