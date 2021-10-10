package com.example.youtubeapp

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.widget.ImageButton
import android.widget.LinearLayout
import androidx.core.content.getSystemService


lateinit var noConnectionLinearLayout: LinearLayout
lateinit var refreshImageButton: ImageButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        checkConnection()

    }


    private fun checkConnection(){
        noConnectionLinearLayout = findViewById(R.id.no_connection_LL)
        val cm = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        if (activeNetwork?.isConnectedOrConnecting == true){
            noConnectionLinearLayout.visibility = INVISIBLE
        }
        else {
            noConnectionLinearLayout.visibility = VISIBLE
            refreshImageButton = findViewById(R.id.refresh_btn)
            refreshImageButton.setOnClickListener {
                checkConnection()
            }
        }
    }

}