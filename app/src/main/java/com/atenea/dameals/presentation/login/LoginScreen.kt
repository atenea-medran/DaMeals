package com.atenea.dameals.presentation.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.Checkbox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.atenea.dameals.R
import com.atenea.dameals.components.CheckAVSComponent
import com.atenea.dameals.presentation.favoritemeallist.ui.theme.Red

const val LOGIN_TEXT_FIELD_USER = "LOGIN_TEXT_FIELD_USER"
const val LOGIN_TEXT_FIELD_PASSWORD = "LOGIN_TEXT_FIELD_PASSWORD"

@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit,
    onLoginFail: () -> Unit
) {

    var composeChecked by remember {
        mutableStateOf(false)
    }

    var checked by remember {
        mutableStateOf(false)
    }

    var email by remember {
        mutableStateOf("atenea-medran")
    }

    var password by remember {
        mutableStateOf("contrasena")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(150.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(Red)
        ) {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(id = R.drawable._ic_launcher_foreground),
                contentDescription = "DaMeals"
            )
        }


        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
        )

        TextField(
            modifier = Modifier
                .testTag(LOGIN_TEXT_FIELD_USER)
                .clip(RoundedCornerShape(20.dp))
            ,
            value = email,
            placeholder = {
                Text("Email")
            },
            onValueChange = { newValue ->
                email = newValue
            },
            leadingIcon = {
                Image(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = ""
                )
            }
        )

        Spacer(modifier = Modifier.size(20.dp))

        TextField(
            modifier = Modifier
                .testTag(LOGIN_TEXT_FIELD_PASSWORD)
                .clip(RoundedCornerShape(20.dp))
            ,
            value = password,
            placeholder = {
                Text("contrasena")
            },
            onValueChange = {
                password = it
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Outlined.Lock,
                    contentDescription = ""
                )
            },
            trailingIcon = {
                IconButton(
                    onClick = {}
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.eye),                        contentDescription = ""
                    )
                }
            }
        )

        Spacer(
            modifier = Modifier
                .size(20.dp)
        )

        Button(
            onClick = {
                if (authenticate(email, password)) {
                    onLoginSuccess()
                } else {
                    onLoginFail()
                }
            },
            colors = ButtonDefaults
                .buttonColors(
                    backgroundColor = Red,
                    contentColor = Color.White
                )
        ) {
            Text(
                text = stringResource(R.string.login)
            )
        }
        Spacer(
            modifier = Modifier
                .size(20.dp)
        )
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                modifier = Modifier
                    .clearAndSetSemantics {
                        contentDescription = "Checkbox recordar"
                        stateDescription = if (composeChecked) {
                            "Estado recordar"
                        } else {
                            "Estado no recordar"
                        }
                    }
                    .focusable(),
                checked = composeChecked,
                onCheckedChange = { composeChecked = it }
            )
            Text(text = "Recordar usuario")
            AndroidView(
                modifier = Modifier
                    .clickable {
                        val newState = !checked
                        checked = newState
                    }.padding(10.dp),
                factory = { context ->
                    CheckAVSComponent(context).apply {
                        this.checked = checked
                    }
                },
                update = {
                    it.checked = checked
                }
            )
        }

    }
}

fun authenticate(user: String, password: String) =
    user == "atenea-medran" && password == "contrasena"


@Preview
@Composable
fun LoginScreenPreview() {
    LoginScreen(
        onLoginSuccess = {},
        onLoginFail = {}
    )
}
