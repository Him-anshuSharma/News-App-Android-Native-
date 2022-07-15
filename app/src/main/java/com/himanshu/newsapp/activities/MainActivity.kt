package com.himanshu.newsapp.activities

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.himanshu.newsapp.adapters.HeadlinesListAdapter
import com.himanshu.newsapp.dataModelClass.Article
import com.himanshu.newsapp.dataModelClass.HeadlinesResponseResult
import com.himanshu.newsapp.databinding.HomeScreenBinding
import com.himanshu.newsapp.interfaces.articleAdapterPosition
import com.himanshu.newsapp.retrofitInstance.retrofitInstance
import retrofit2.Response
import java.io.Serializable
import java.lang.Error

class MainActivity : AppCompatActivity() {

    private lateinit var articles: List<Article>
    private lateinit var binding: HomeScreenBinding
    private lateinit var clickedArticlePosition: articleAdapterPosition

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = HomeScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        clickedArticlePosition = object : articleAdapterPosition {
            override fun pass(position: Int) {
                val intent = Intent(applicationContext,WebViewArticle::class.java)
                intent.putExtra("url",articles[position].url)
                startActivity(intent)
            }
        }


        lifecycleScope.launchWhenCreated {
            try {
                val headlines = retrofitInstance.api.getHeadlines()
                setArticles(headlines)
            }
            catch (e: Error){
                Toast.makeText(applicationContext,e.message.toString(),Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun setArticles(headlines: Response<HeadlinesResponseResult>) {
        articles = headlines.body()!!.articles;
        setRecyclerView()
    }

    private fun setRecyclerView() {
        val newsHeadlinesAdapter = HeadlinesListAdapter(articles,clickedArticlePosition)
        binding.headlinesRecyclerView.adapter = newsHeadlinesAdapter
        binding.headlinesRecyclerView.layoutManager = LinearLayoutManager(this)
    }
}