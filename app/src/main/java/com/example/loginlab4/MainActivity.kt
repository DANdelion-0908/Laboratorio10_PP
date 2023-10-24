package com.example.loginlab4

import android.content.IntentSender
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.loginlab4.googleLoginClient.GoogleLoginClient
import com.example.loginlab4.googleLoginClient.SignInScreen
import com.example.loginlab4.googleLoginClient.SignInViewModel
import com.google.android.gms.auth.api.identity.Identity
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private val googleLoginClient by lazy {
        GoogleLoginClient(
            context = applicationContext,
            oneTapClient = Identity.getSignInClient(applicationContext)
        )
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppNavigation()
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "sign_in") {
                composable("sign_in") {
                    val viewModel = viewModel<SignInViewModel>()
                    val state by viewModel.state.collectAsStateWithLifecycle()

                    val launcher = rememberLauncherForActivityResult(
                        contract = ActivityResultContracts.StartActivityForResult(),
                        onResult = { result ->
                            if(result.resultCode == RESULT_OK) {
                                lifecycleScope.launch {
                                    val signInResult = googleLoginClient.signInWithIntent(
                                        intent = result.data?: return@launch
                                    )
                                    viewModel.onSignInResult(signInResult)
                                }
                            }
                        }
                    )

                    LaunchedEffect(key1 = state.isSignInSuccesful) {
                        if(state.isSignInSuccesful) {
                            Toast.makeText(
                                applicationContext,
                                "Se pudo",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }

                    SignInScreen(
                        state = state,
                        onSignInState = {
                            lifecycleScope.launch {
                                val signInIntendSender = googleLoginClient.signIn()
                                //launcher.launch(
                                    //IntentSenderRequest.Builder(
                                      //  signInIntendSender?: return@launch
                                    //).build()
                                //)
                            }
                        }
                    )
                }
            }
        }
    }
}


