package com.korniykom.webvetcare.presentation.components.textfields

import androidx.compose.runtime.Composable
import com.korniykom.webvetcare.presentation.theme.Neutral40
import com.korniykom.webvetcare.presentation.theme.NeutralVar50
import com.korniykom.webvetcare.presentation.theme.NeutralVar60
import com.korniykom.webvetcare.presentation.theme.NeutralVar80
import com.korniykom.webvetcare.presentation.theme.NeutralVar95
import com.korniykom.webvetcare.presentation.theme.NeutralVar99
import com.korniykom.webvetcare.presentation.theme.Red50
import com.korniykom.webvetcare.presentation.theme.Red80
import com.korniykom.webvetcare.presentation.theme.Red95
import com.korniykom.webvetcare.presentation.theme.Teal90
import com.korniykom.webvetcare.presentation.theme.Teal99

@Composable
fun textFieldContainerColor(
    enabled: Boolean,
    isFocused: Boolean,
    isError: Boolean
) = when {
    !enabled -> NeutralVar95
    isError -> Red95
    isFocused -> Teal99
    else -> NeutralVar99
}

@Composable
fun textFieldBorderColor(
    isError: Boolean,
    isFocused: Boolean
) = when {
    isError -> Red50
    isFocused -> Teal90
    else -> NeutralVar80
}

@Composable
fun textFieldTextColor(enabled: Boolean, isError: Boolean) = when {
    !enabled -> NeutralVar50
    isError -> Red50
    else -> Neutral40
}

@Composable
fun textFieldPlaceholderColor(isError: Boolean = false) = when {
    isError -> Red80
    else -> NeutralVar60
}

