package com.himanshu.newsapp.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.himanshu.newsapp.R
import com.himanshu.newsapp.adapters.HeadlinesListAdapter
import com.himanshu.newsapp.dataModelClass.Article
import com.himanshu.newsapp.dataModelClass.HeadlinesResponseResult
import com.himanshu.newsapp.databinding.HomeScreenBinding
import com.himanshu.newsapp.interfaces.articleAdapterPosition
import com.himanshu.newsapp.retrofitInstance.retrofitInstance
import kotlinx.coroutines.launch
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var articles: MutableList<Article>
    private lateinit var binding: HomeScreenBinding
    private lateinit var clickedArticlePosition: articleAdapterPosition
    private var searchNewsOpen = true
    private var category = "headlines"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = HomeScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        clickedArticlePosition = object : articleAdapterPosition {
            override fun pass(position: Int) {
                val intent = Intent(this@MainActivity, WebViewArticle::class.java)
                intent.putExtra("url", articles[position].url)
                startActivity(intent)
            }
        }


        lifecycleScope.launchWhenCreated {
            try {
                val headlines = retrofitInstance.api.getHeadlines(
                    "in",
                    "16d2ba05ce0644e0be6536b97767fed1"
                )
                setArticles(headlines)
            } catch (e: Error) {
                Toast.makeText(applicationContext, e.message.toString(), Toast.LENGTH_LONG).show()
            }
        }

        binding.searchNews.setOnClickListener {
            if (searchNewsOpen) {
                binding.appTitle.visibility = View.GONE
                binding.searchNews.setImageResource(R.drawable.icon_news)
                binding.headlineTextInput.visibility = View.VISIBLE
                searchNewsOpen = false
            } else {
                val query = binding.headlineTextInput.text.toString()
                binding.appTitle.visibility = View.VISIBLE
                binding.searchNews.setImageResource(R.drawable.icon_search)
                binding.headlineTextInput.visibility = View.GONE
                searchNewsOpen = true
                lifecycleScope.launch {
                    try {
                        val headlines = retrofitInstance.api.getCustomHeadlines(query,
                            "16d2ba05ce0644e0be6536b97767fed1")
                        updateArticles(headlines)
                    } catch (e: Error) {
                        Toast.makeText(applicationContext, e.message.toString(), Toast.LENGTH_LONG)
                            .show()
                    }
                }
            }
        }

        //HEADLINES
        binding.Headlines.setOnClickListener {
            category = "Headlines"
            binding.currentCategory.text = category
            lifecycleScope.launch {
                try {
                    val headlines =
                        retrofitInstance.api.getHeadlines("in", "16d2ba05ce0644e0be6536b97767fed1")
                    updateArticles(headlines)
                } catch (e: Error) {
                    Toast.makeText(applicationContext, e.message.toString(), Toast.LENGTH_LONG)
                        .show()
                }
            }
        }

        //SPORTS
        binding.sports.setOnClickListener {
            category = "sport"
            binding.currentCategory.text = category
            lifecycleScope.launch {
                try {
                    val headlines = retrofitInstance.api.getSportsHeadlines("in",
                        "sports",
                        "16d2ba05ce0644e0be6536b97767fed1")
                    updateArticles(headlines)
                } catch (e: Error) {
                    Toast.makeText(applicationContext, e.message.toString(), Toast.LENGTH_LONG)
                        .show()
                }
            }
        }

        //Politics
        binding.politics.setOnClickListener {
            category = "politics"
            binding.currentCategory.text = category
            lifecycleScope.launch {
                try {
                    val headlines = retrofitInstance.api.getPoliticsHeadlines(
                        "in",
                        "politics",
                        "16d2ba05ce0644e0be6536b97767fed1"
                    )
                    updateArticles(headlines)
                } catch (e: Error) {
                    Toast.makeText(applicationContext, e.message.toString(), Toast.LENGTH_LONG)
                        .show()
                }
            }
        }

        //TECHNOLOGY
        binding.Technology.setOnClickListener {
            category = "Technology"
            binding.currentCategory.text = category
            lifecycleScope.launch {
                try {
                    val headlines = retrofitInstance.api.getTechnologyHeadlines(
                        "in",
                        "technology",
                        "16d2ba05ce0644e0be6536b97767fed1"
                    )
                    updateArticles(headlines)
                } catch (e: Error) {
                    Toast.makeText(applicationContext, e.message.toString(), Toast.LENGTH_LONG)
                        .show()
                }
            }
        }

    }

    private fun updateArticles(headlines: Response<HeadlinesResponseResult>) {
        //Toast.makeText(applicationContext,"Update article",Toast.LENGTH_LONG).show()
        articles.clear()
        articles = headlines.body()!!.articles.toMutableList()
        if (!articles.isEmpty()) {
            setRecyclerView()
        }
    }

    private fun setArticles(headlines: Response<HeadlinesResponseResult>) {

        articles = headlines.body()!!.articles.toMutableList()
        setRecyclerView()
    }

    private fun setRecyclerView() {
        val newsHeadlinesAdapter = HeadlinesListAdapter(articles, clickedArticlePosition)
        binding.headlinesRecyclerView.adapter = newsHeadlinesAdapter
        binding.headlinesRecyclerView.layoutManager = LinearLayoutManager(this)
    }
}