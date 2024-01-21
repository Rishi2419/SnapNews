package com.example.snapnews

import android.content.ClipData.Item
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest

class MainActivity : AppCompatActivity(), NewsItemClicked {

    private lateinit var mAdaptor: SnapNewsAdaptor
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<ImageView>(R.id.circleai)


        button.setOnClickListener{
            startActivity(Intent(this,AIbot::class.java))
            finish()
        }
        var recyclerView=findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.layoutManager=LinearLayoutManager(this)

        fetchdata()
        mAdaptor= SnapNewsAdaptor(this)
        recyclerView.adapter=mAdaptor


    }
    private fun fetchdata(){

        val url ="https://saurav.tech/NewsAPI/everything/cnn.json"

        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET,url, null,
            {
                val newsjsonarray = it.getJSONArray("articles")
                val newsarray = ArrayList<News>()
                for(i in 0 until newsjsonarray.length() ){
                    val newsjsonobject = newsjsonarray.getJSONObject(i)
                    val news = News(
                        newsjsonobject.getString("title"),
                        newsjsonobject.getString("author"),
                        newsjsonobject.getString("url"),
                        newsjsonobject.getString("urlToImage"),
                    )
                    newsarray.add(news)
                }

                mAdaptor.updateNews(newsarray)
            },
            {
                val errorText = if (it?.localizedMessage != null) {
                    "Error: ${it.localizedMessage}"
                } else {
                    "Unknown error occurred"
                }
                Toast.makeText(this, errorText, Toast.LENGTH_SHORT).show()
            }

        )

// Access the RequestQueue through your singleton class.
        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)
    }

    override fun onItemClicked(item: News) {
     val builder = CustomTabsIntent.Builder()
        val customTabsIntent=builder.build()
        customTabsIntent.launchUrl(this, Uri.parse(item.url));
    }
}