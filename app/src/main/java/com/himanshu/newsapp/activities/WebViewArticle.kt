package com.himanshu.newsapp.activities

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.himanshu.newsapp.databinding.ActivityWebViewArticleBinding
import retrofit2.http.Url

class WebViewArticle : AppCompatActivity() {

    private lateinit var binding: ActivityWebViewArticleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebViewArticleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var url: String = intent.getStringExtra("url").toString()
        binding.articleWebView.loadUrl(url)
    }
}