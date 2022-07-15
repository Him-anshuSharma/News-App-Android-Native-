package com.himanshu.newsapp.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.himanshu.newsapp.R
import com.himanshu.newsapp.activities.ArticleActivity
import com.himanshu.newsapp.dataModelClass.Article

class headlinesListAdapter(private val articles: List<Article>): RecyclerView.Adapter<headlinesListAdapter.ViewHolder> (){

    private lateinit var context: Context

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val image: ImageView
        val headline: TextView
        val description: TextView
        init {
            image = view.findViewById(R.id.news_card_Image)
            headline = view.findViewById(R.id.news_card_headline)
            description = view.findViewById(R.id.news_card_content)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.news_card, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.headline.text = articles[position].title
        bindingImage(holder,position)
        holder.description.text = articles[position].description
        holder.itemView.setOnClickListener {
            val intent = Intent(context,ArticleActivity::class.java)
            context.startActivity(intent)
        }
    }

    private fun bindingImage(holder: ViewHolder,position: Int) {
        Glide.with(context).load(articles[position].urlToImage)
            .into(holder.image)
    }

    override fun getItemCount(): Int = articles.size
}

