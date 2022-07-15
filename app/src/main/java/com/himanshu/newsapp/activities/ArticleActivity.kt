package com.himanshu.newsapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.himanshu.newsapp.R
import com.himanshu.newsapp.dataModelClass.Article
import com.himanshu.newsapp.databinding.ActivityArticleBinding

class ArticleActivity() : AppCompatActivity() {

    private lateinit var binding: ActivityArticleBinding
    private lateinit var article:Article

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArticleBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Glide.with(applicationContext).load(article.urlToImage).into(binding.articleImage)
        binding.articleContent.text = article.title
        binding.articleContent.text = article.content

    }
}