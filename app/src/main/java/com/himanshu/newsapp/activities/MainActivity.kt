package com.himanshu.newsapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.himanshu.newsapp.R
import com.himanshu.newsapp.adapters.headlinesListAdapter
import com.himanshu.newsapp.dataModelClass.Article
import com.himanshu.newsapp.dataModelClass.HeadlinesResponseResult
import com.himanshu.newsapp.databinding.HomeScreenBinding
import com.himanshu.newsapp.retrofitInstance.retrofitInstance
import retrofit2.Response
import java.lang.Error

class MainActivity : AppCompatActivity() {

    private lateinit var articles: List<Article>
    private lateinit var binding: HomeScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = HomeScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
        val newsHeadlinesAdapter = headlinesListAdapter(articles)
        binding.headlinesRecyclerView.adapter = newsHeadlinesAdapter
        binding.headlinesRecyclerView.layoutManager = LinearLayoutManager(this)
    }
}