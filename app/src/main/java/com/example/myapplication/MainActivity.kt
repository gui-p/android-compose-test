package com.example.myapplication

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.core.content.ContextCompat.startActivity
import com.example.myapplication.ui.theme.MyApplicationTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                Scaffold {
                    innerPadding -> CenteredContent(innerPadding, activity = this)
                }
            }
        }
    }
}

@Composable
fun CenteredContent(padding : PaddingValues, activity: Activity){
    Column (
        verticalArrangement =  Arrangement.Center,
        horizontalAlignment =  Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        var isColorRed by remember { mutableStateOf(false) }

        val color = if (isColorRed) {
            Color.Red
        } else {
            Color.Blue
        }

        ChangeColorButton(color) {
            isColorRed = !isColorRed
            val intent = Intent("android.media.action.IMAGE_CAPTURE")
            startActivityForResult(activity, intent, 1001, null)
        }
    }
}

@Composable
fun ChangeColorButton( color: Color,  onClick : () -> Unit) {
    Button(
        onClick = { onClick() },
        colors = ButtonDefaults.buttonColors(color)
    ) {
        Text("Take a photo")
    }
}