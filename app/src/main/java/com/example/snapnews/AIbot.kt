package com.example.snapnews

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity

class AIbot : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aibot)
        //
//        fun onBackPressed() {
//            startActivity(Intent(this,MainActivity::class.java))
//        }



        val myWeb = findViewById<WebView>(R.id.webView)

        myWeb.webViewClient = WebViewClient()

        myWeb.apply {
            Log.d("Rishi", "AI bot called")

            loadUrl("https://bard.google.com/chat/5f1b645ebbc0a43a")
            settings.javaScriptEnabled = true
        }

    }
}