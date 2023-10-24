package com.example.loginlab4.googleLoginClient

data class SignInState(
    val isSignInSuccesful: Boolean = false,
    val signInError: String? = null
)