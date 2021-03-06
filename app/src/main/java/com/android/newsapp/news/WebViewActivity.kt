package com.android.newsapp.news

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import com.android.newsapp.R

class WebViewActivity : AppCompatActivity() {

    companion object {
        const val INTENT_WEB_URL = "web_url"
        fun launchWebViewActivity(activity: Activity, url: String) {
            var intent = Intent(activity, WebViewActivity::class.java)
            intent.putExtra(INTENT_WEB_URL, url)
            activity.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
        }
        intent.getStringExtra(INTENT_WEB_URL)?.let {
            findViewById<WebView>(R.id.web_view).loadUrl(it)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId==android.R.id.home){
          super.onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}
