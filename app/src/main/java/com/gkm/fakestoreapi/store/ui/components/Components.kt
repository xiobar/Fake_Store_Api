package com.gkm.fakestoreapi.store.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp

@Composable
fun ButtonView(
    onClcik: () -> Unit,
    modifier: Modifier,
    condition: Boolean,
    text: String
) {
    Button(
        onClick = onClcik,
        modifier = modifier,
        enabled = condition,
        colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary)
    ) {
        TextView(text = text)
    }
}

@Composable
fun SwitchView(
    condition: Boolean,
    checked: (Boolean) -> Unit,
    modifier: Modifier,
    icon: (@Composable () -> Unit)?
) {
    Switch(
        checked = condition,
        onCheckedChange = checked,
        modifier = modifier,
        thumbContent = icon,
        colors = SwitchDefaults.colors(
            checkedThumbColor = MaterialTheme.colorScheme.onPrimary,
            checkedTrackColor = MaterialTheme.colorScheme.primary
        )
    )
}

@Composable
fun TextView(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        modifier = modifier
    )
}

@Composable
fun OptionsDesigns(modifier: Modifier, image: Int, name: Int) {
    Surface(
        modifier
            .padding(10.dp)
            .size(150.dp),
        color = MaterialTheme.colorScheme.surface,
        shape = RoundedCornerShape(20.dp),
        shadowElevation = 10.dp,
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(10.dp)
        ) {
            Surface(
                color = MaterialTheme.colorScheme.surface,
                /*shape = CircleShape,*/
                modifier = Modifier
                    .weight(1f)
                    .wrapContentHeight()
                    .size(60.dp)
            ) {
                Icon(
                    painter = painterResource(id = image),
                    contentDescription = stringResource(id = name),
                    tint = MaterialTheme.colorScheme.tertiary,
                    modifier = Modifier
                        .wrapContentSize(Alignment.Center)
                        .size(40.dp)
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .weight(1f)
            ) {
                Text(
                    text = stringResource(id = name),
                    modifier = Modifier
                        .weight(1f),
                    color = MaterialTheme.colorScheme.tertiary
                )
                Icon(
                    imageVector = Icons.Filled.ArrowForward,
                    contentDescription = "arrow go",
                    tint = MaterialTheme.colorScheme.tertiary,
                    modifier = Modifier
                        .weight(0.3f)
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarViewBack(
    text: String,
    navigation: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = {
            TextView(
                text = text,
                modifier = Modifier.padding(start = 10.dp)
            )
        },
        navigationIcon = {
            IconButton(
                onClick = navigation
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "back"
                )
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.tertiary,
            navigationIconContentColor = MaterialTheme.colorScheme.surface,
            titleContentColor = MaterialTheme.colorScheme.surface
        ),
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldView(
    text: String,
    textValue: (String) -> Unit,
    label: (@Composable () -> Unit)?,
    modifier:Modifier = Modifier
) {
    TextField(
        value = text,
        onValueChange = textValue,
        label = label,
        modifier = modifier.padding(10.dp),
        maxLines = 1,
        singleLine = true,
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done
        ),
    )
}