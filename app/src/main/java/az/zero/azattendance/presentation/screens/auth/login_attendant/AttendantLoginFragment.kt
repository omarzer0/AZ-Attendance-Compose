package az.zero.azattendance.presentation.screens.auth.login_attendant

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import az.zero.azattendance.R
import az.zero.azattendance.core.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AttendantLoginFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?,
    ): View {
        return setFragmentContent {
            AttendantLoginScreen(navController = findNavController())
        }
    }
}

@Composable
fun AttendantLoginScreen(
    navController: NavController,
) {
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var passwordVisibility by rememberSaveable { mutableStateOf(false) }

    AttendantLoginScreen(
        email = email,
        password = password,
        emailLabel = "Email",
        passwordLabel = "Password",
        isPasswordVisible = passwordVisibility,
        onEmailChange = { email = it },
        onPasswordChange = { password = it },
        onLoginClick = {},
        onSingUpClick = {},
        onPasswordVisibilityChanged = { passwordVisibility = !passwordVisibility }
    )

}

@Composable
fun AttendantLoginScreen(
    email: String,
    onEmailChange: (String) -> Unit,
    password: String,
    onPasswordChange: (String) -> Unit,
    emailLabel: String,
    passwordLabel: String,
    isPasswordVisible: Boolean,
    onSingUpClick: () -> Unit,
    onLoginClick: () -> Unit,
    onPasswordVisibilityChanged: () -> Unit,
) {

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AttendantLoginHeader(text = stringResource(id = R.string.app_name))

        AttendantLoginBody(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            email = email,
            onEmailChange = onEmailChange,
            password = password,
            onPasswordChange = onPasswordChange,
            emailLabel = emailLabel,
            passwordLabel = passwordLabel,
            isPasswordVisible = isPasswordVisible,
            onPasswordVisibilityChanged = onPasswordVisibilityChanged
        )

        Spacer(modifier = Modifier.height(16.dp))

        ButtonWithText(
            modifier = Modifier.width(250.dp),
            text = "Login",
            onClick = onLoginClick,
            contentPadding = PaddingValues(vertical = 12.dp),
        )

        Spacer(modifier = Modifier.height(16.dp))
    }

}

@Composable
fun AttendantLoginBody(
    modifier: Modifier = Modifier,
    email: String,
    onEmailChange: (String) -> Unit,
    password: String,
    onPasswordChange: (String) -> Unit,
    emailLabel: String,
    passwordLabel: String,
    isPasswordVisible: Boolean,
    onPasswordVisibilityChanged: () -> Unit,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val focusManager = LocalFocusManager.current


        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            value = email,
            onValueChange = onEmailChange,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Email),
            keyboardActions = KeyboardActions(onNext = { focusManager.moveFocus(FocusDirection.Down) }),
            label = { Text(text = emailLabel) },
            leadingIcon = {
                IconButton(
                    enabled = false,
                    onClick = {
                        onPasswordVisibilityChanged()
                    }) {
                    Icon(imageVector = Icons.Filled.Email, "Email")
                }
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = if (isSystemInDarkTheme()) Color.White else Color.Black
            ),
        )

        Spacer(modifier = Modifier.height(16.dp))

        PasswordTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            password = password,
            onValueChange = onPasswordChange,
            label = passwordLabel,
            isPasswordVisible = isPasswordVisible,
            onPasswordVisibilityChanged = onPasswordVisibilityChanged,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(
                onDone = { focusManager.clearFocus() }
            ),
        )
    }
}

@Composable
fun AttendantLoginHeader(
    modifier: Modifier = Modifier,
    text: String,
) {
    TopAppBar(
        modifier = modifier,
        title = {
            Text(
                text = text,
                color = MaterialTheme.colors.onPrimary
            )
        },
    )
}

@Composable
fun PasswordTextField(
    modifier: Modifier = Modifier,
    password: String,
    onValueChange: (String) -> Unit,
    label: String,
    colors: TextFieldColors = TextFieldDefaults.outlinedTextFieldColors(
        textColor = if (isSystemInDarkTheme()) Color.White else Color.Black
    ),
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    isPasswordVisible: Boolean,
    onPasswordVisibilityChanged: () -> Unit,
) {
    OutlinedTextField(
        modifier = modifier,
        value = password,
        onValueChange = onValueChange,
        keyboardOptions = keyboardOptions.copy(keyboardType = KeyboardType.Password),
        keyboardActions = keyboardActions,
        label = { Text(text = label) },
        visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        colors = colors,
        trailingIcon = {
            val image = if (isPasswordVisible) Icons.Filled.Visibility
            else Icons.Filled.VisibilityOff

            IconButton(onClick = {
                onPasswordVisibilityChanged()
            }) {
                Icon(imageVector = image, "Toggle password")
            }
        }
    )
}

@Composable
fun ButtonWithText(
    modifier: Modifier = Modifier,
    text: String,
    textStyle: TextStyle = MaterialTheme.typography.body1,
    shape: Shape = MaterialTheme.shapes.small,
    onClick: () -> Unit,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    elevation: ButtonElevation? = ButtonDefaults.elevation(),
    border: BorderStroke? = null,
    colors: ButtonColors = ButtonDefaults.buttonColors(),
    contentPadding: PaddingValues = PaddingValues(vertical = 12.dp),
) {
    Button(
        modifier = modifier,
        shape = shape,
        colors = colors,
        onClick = onClick,
        contentPadding = contentPadding,
        border = border,
        elevation = elevation,
        enabled = enabled,
        interactionSource = interactionSource,
    ) {
        Text(
            text = text,
            style = textStyle,
        )
    }
}