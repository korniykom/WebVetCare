package com.korniykom.webvetcare

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.korniykom.webvetcare.presentation.components.textfields.PasswordTextField
import com.korniykom.webvetcare.presentation.screens.landing_page.LandingPageRoot
import com.korniykom.webvetcare.presentation.theme.WebVetCareTheme


@Composable
fun App() {
   WebVetCareTheme {
       Box(
           modifier = Modifier.widthIn(max = 300.dp).padding(16.dp)
       ){
           Column {
               // Default / empty state
               var emptyState by remember { mutableStateOf(TextFieldState()) }
               var emptyVisible by remember { mutableStateOf(false) }
               PasswordTextField(
                   state = emptyState,
                   isPasswordVisible = emptyVisible,
                   onToggleVisibilityClick = { emptyVisible = !emptyVisible },
                   placeholder = "Enter password",
                   title = "Default / Empty"
               )

               Spacer(modifier = Modifier.height(16.dp))

               // Filled state
               var filledState by remember { mutableStateOf(TextFieldState("MySecret123")) }
               var filledVisible by remember { mutableStateOf(false) }
               PasswordTextField(
                   state = filledState,
                   isPasswordVisible = filledVisible,
                   onToggleVisibilityClick = { filledVisible = !filledVisible },
                   placeholder = "Enter password",
                   title = "Filled"
               )

               Spacer(modifier = Modifier.height(16.dp))

               // Error state
               var errorState by remember { mutableStateOf(TextFieldState("123")) }
               var errorVisible by remember { mutableStateOf(false) }
               PasswordTextField(
                   state = errorState,
                   isPasswordVisible = errorVisible,
                   onToggleVisibilityClick = { errorVisible = !errorVisible },
                   placeholder = "Enter password",
                   title = "Error",
                   isError = true,
                   supportingText = "Password too short"
               )

               Spacer(modifier = Modifier.height(16.dp))

               // Disabled state
               var disabledState by remember { mutableStateOf(TextFieldState("CannotEdit")) }
               var disabledVisible by remember { mutableStateOf(false) }
               PasswordTextField(
                   state = disabledState,
                   isPasswordVisible = disabledVisible,
                   onToggleVisibilityClick = { disabledVisible = !disabledVisible },
                   placeholder = "Enter password",
                   title = "Disabled",
                   enabled = false
               )
           }
       }
   }
}