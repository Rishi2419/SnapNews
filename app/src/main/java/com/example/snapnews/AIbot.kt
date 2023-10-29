package com.example.snapnews

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient

class AIbot : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aibot)

        val myWeb = findViewById<WebView>(R.id.webView)

        myWeb.webViewClient= WebViewClient()

        myWeb.apply {
            loadUrl("https://bard.google.com/chat/5f1b645ebbc0a43a")
            settings.javaScriptEnabled=true
        }
    }
}