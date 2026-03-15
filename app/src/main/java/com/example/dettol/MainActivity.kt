package com.example.dettol

import android.Manifest
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.dettol.navigation.Navigation
import com.example.dettol.ui.theme.DettolTheme
import com.example.network.DownloadCompleteReceiver
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val myBroadcastReceiver = DownloadCompleteReceiver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val filter = IntentFilter("android.intent.action.DOWNLOAD_COMPLETE")
        val listenToBroadcastsFromOtherApps = false
        val receiverFlags = if(listenToBroadcastsFromOtherApps){
            ContextCompat.RECEIVER_EXPORTED
        } else {
            ContextCompat.RECEIVER_NOT_EXPORTED
        }

        CoroutineScope(Dispatchers.Unconfined).launch {
            ContextCompat.registerReceiver(
                this@MainActivity,
                myBroadcastReceiver,
                filter,
                receiverFlags
            )
        }

        setContent {
            DettolTheme {

                val permissions = if(Build.VERSION.SDK_INT >=33) {
                    arrayOf(
                        Manifest.permission.READ_MEDIA_AUDIO,
                        Manifest.permission.READ_MEDIA_VIDEO,
                        Manifest.permission.READ_MEDIA_IMAGES
                    )
                } else {
                    arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
                }

                ActivityCompat.requestPermissions(
                    this,
                    permissions,
                    0
                )

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Navigation(context = this)
                }
            }
        }
    }


    override fun onDestroy() {
        super.onDestroy()

        this.unregisterReceiver(myBroadcastReceiver)
    }

}

