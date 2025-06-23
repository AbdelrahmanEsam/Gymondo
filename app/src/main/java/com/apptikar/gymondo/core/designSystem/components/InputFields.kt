package com.apptikar.gymondo.core.designSystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import com.apptikar.gymondo.core.designSystem.theme.transparentColor


@Composable
fun GymondoTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChanged: (String) -> Unit,
    imeAction: ImeAction = ImeAction.Go,
    focusDirection: FocusDirection? = null,
    onGo: () -> Unit,
    leadingComposable: @Composable (() -> Unit)? = null,
    trailingComposable: @Composable (() -> Unit)? = null,
    placeholder: String? = null,
    inputType: KeyboardType = KeyboardType.Text,
    singleLine: Boolean = true,
    isEnabled: Boolean = true,
    textStyle: TextStyle,
    backgroundColor: Color? = null,
) {
    val focusManager = LocalFocusManager.current
    Column(modifier = modifier, verticalArrangement = Arrangement.Top) {

        TextField(
            enabled = isEnabled,
            value = value,
            onValueChange = { newValue ->
                onValueChanged.invoke(newValue)
            },
            textStyle = textStyle,
            leadingIcon = leadingComposable,
            trailingIcon = trailingComposable,
            shape = MaterialTheme.shapes.medium,
            placeholder = {
                placeholder?.let {
                    Text(
                        modifier = Modifier.fillMaxSize(),
                        text = it,
                        textAlign = TextAlign.Start,
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            },
            modifier = Modifier
                .background(
                    color = backgroundColor ?: MaterialTheme.colorScheme.transparentColor,
                    shape = MaterialTheme.shapes.medium
                )
                .fillMaxWidth(),
            singleLine = singleLine,
            visualTransformation = VisualTransformation.None,
            keyboardOptions = KeyboardOptions(
                keyboardType = inputType,
                imeAction = imeAction
            ),
            keyboardActions = KeyboardActions(
                onGo = {
                    focusDirection?.let {
                        focusManager.moveFocus(it)
                    }
                    onGo()
                },
            ),
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = MaterialTheme.colorScheme.transparentColor,
                unfocusedIndicatorColor = MaterialTheme.colorScheme.transparentColor,
                disabledIndicatorColor = MaterialTheme.colorScheme.transparentColor,
                focusedContainerColor = MaterialTheme.colorScheme.secondary,
                unfocusedContainerColor = MaterialTheme.colorScheme.secondary,
                cursorColor = MaterialTheme.colorScheme.primary,
            )
        )
    }
}