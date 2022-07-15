package com.himanshu.newsapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.himanshu.newsapp.R
import com.himanshu.newsapp.dataModelClass.Article
import com.himanshu.newsapp.interfaces.articleAdapterPosition

class HeadlinesListAdapter(private val articles: List<Article>,private val passPosition:articleAdapterPosition): RecyclerView.Adapter<HeadlinesListAdapter.ViewHolder> (){

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
            passPosition.pass(position)
        }
    }

    private fun bindingImage(holder: ViewHolder,position: Int) {
        Glide.with(context).load(articles[position].urlToImage)
            .into(holder.image)
    }

    override fun getItemCount(): Int = articles.size
}

