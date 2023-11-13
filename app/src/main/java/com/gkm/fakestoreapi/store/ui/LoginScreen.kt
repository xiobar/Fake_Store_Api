package com.gkm.fakestoreapi.store.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gkm.fakestoreapi.R
import com.gkm.fakestoreapi.store.preference.UserAndPassword
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun LoginScreen(
    loginViewModel: LoginViewModel
) {
    val isLoading by loginViewModel.loading.observeAsState(initial = false)

    Box(
        Modifier
            .fillMaxSize()
            .padding(18.dp)
    ) {
        if (isLoading) {
            Box(modifier = Modifier.align(Alignment.Center)) {
                CircularProgressIndicator()
            }
        } else {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Header(
                    Modifier
                        .fillMaxWidth()
                        .weight(1f)
                )
                Body(
                    Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    loginViewModel
                )
                Foot(
                    Modifier
                        .fillMaxWidth()
                        .weight(0.5f)
                )
            }
        }
    }
}

@Composable
fun Foot(modifier: Modifier) {
    Box(modifier, contentAlignment = Alignment.Center) {
        Text(
            text = stringResource(id = R.string.comment),
            modifier,
            textAlign = TextAlign.Center,
            color = Color(0xFF757575),
            fontSize = 16.sp
        )
    }
}


@Composable
fun Body(
    modifier: Modifier,
    loginViewModel: LoginViewModel,
) {
    Box(modifier.fillMaxWidth()) {
        Column(Modifier.align(Alignment.Center)) {
            LoginTextField(Modifier.fillMaxWidth(), loginViewModel)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginTextField(
    modifier: Modifier,
    loginViewModel: LoginViewModel,
    ) {
    val userAndPassword:UserAndPassword? by loginViewModel.userAndPassword.collectAsState(initial = null)
    val user: String by loginViewModel.user.observeAsState(initial = "${userAndPassword?.user}")
    val pass: String by loginViewModel.password.observeAsState(initial = "${userAndPassword?.password}")
    var passwordVisible by rememberSaveable {
        mutableStateOf(false)
    }
    var remember by rememberSaveable {
        mutableStateOf(false)
    }
    val icon: (@Composable () -> Unit)? =
        if (remember) {
            {
                Icon(
                    imageVector = Icons.Filled.Check,
                    contentDescription = null,
                    modifier = Modifier.size(SwitchDefaults.IconSize)
                )
            }
        } else {
            null
        }
    val loginButton by loginViewModel.loginButton.observeAsState(initial = false)
    //val showToast by loginViewModel.showToast.observeAsState(initial = "")

    OutlinedTextField(value = user,
        onValueChange = { loginViewModel.loginChanged(user = it, password = pass) },
        maxLines = 1,
        modifier = modifier,
        singleLine = true,
        label = {
            Text(
                text = "Usuario",
                color = Color(0xFF757575),
            )
        },
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Next
        )
    )

    OutlinedTextField(
        value = pass,
        onValueChange = { loginViewModel.loginChanged(user = user, password = it) },
        maxLines = 1,
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done),
        modifier = modifier,
        singleLine = true,
        label = {
            Text(
                text = "Password",
                color = Color(0xFF757575)
            )
        },
        trailingIcon = {
            val iconObject = if (passwordVisible) {
                Icons.Filled.Visibility
            } else {
                Icons.Filled.VisibilityOff
            }
            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                Icon(imageVector = iconObject, contentDescription = null)
            }
        },
        visualTransformation = if (passwordVisible) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation()
        }
    )
    Box(modifier) {
        Row(
            Modifier
                .padding(top = 10.dp)
                .align(Alignment.CenterEnd),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = " Recordar credenciales:")
            Switch(
                checked = remember,
                onCheckedChange = { remember = !remember },
                modifier = Modifier.padding(start = 10.dp),
                thumbContent = icon,
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color.White,
                    checkedTrackColor = Color(0xFF3F51B5)
                )
            )
        }
    }
    //val context = LocalContext.current
    Box(modifier) {
        Button(
            onClick = {
                loginViewModel.onLoginSelected()
            },
            Modifier
                .padding(top = 10.dp)
                .align(Alignment.Center),
            enabled = loginButton
        )
        {
            Text(text = "INGRESAR")
        }
        /*if (showToast.isNotEmpty()) {
            DisposableEffect(showToast) {
                val toast = Toast.makeText(context, showToast, Toast.LENGTH_SHORT)
                toast.show()
                onDispose { toast.cancel() }
            }
        }*/
    }
}

@Composable
fun Header(modifier: Modifier) {
    Image(
        painter = painterResource(id = R.drawable.ic_launcher_background),
        contentDescription = "Logo",
        modifier
            .padding(5.dp)
    )
}