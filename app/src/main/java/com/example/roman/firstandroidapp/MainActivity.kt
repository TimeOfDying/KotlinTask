package com.example.roman.firstandroidapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import java.io.IOException
import java.io.LineNumberReader

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recViewMain.layoutManager = LinearLayoutManager(this);
        importJson()

    }

    private fun importJson() {
        val url = "https://api.github.com/users/square/repos";
        val request = Request.Builder()
                .url(url)
                .build()
        val client = OkHttpClient()
        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call?, response: Response?) {
                val body = response?.body()?.string();
                val repos = Gson().fromJson(body, Repo.List::class.java);
                val reposStr: ArrayList<String> = ArrayList()
                for (item: Repo in repos) {
                    reposStr.add(item.name)
                }

                runOnUiThread {
                    recViewMain.adapter = recViewAdapter(this@MainActivity, reposStr);
                }
            }

            override fun onFailure(call: Call?, e: IOException?) {
                println("error");
            }
        })
    }
}


data class Repo(val name: String) {
    class List : ArrayList<Repo>();
}



