package com.example.tugas2pam_123140084_radjaapprilla

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.tugas2pam_123140084_radjaapprilla.ui.theme.Tugas2PAM_123140084_RadjaApprillaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Tugas2PAM_123140084_RadjaApprillaTheme {
                NewsScreen()
            }
        }
    }
}
