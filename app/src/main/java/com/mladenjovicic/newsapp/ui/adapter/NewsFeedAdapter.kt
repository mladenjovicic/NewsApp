package com.mladenjovicic.newsapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mladenjovicic.newsapp.R
import com.mladenjovicic.newsapp.data.model.Articles
import com.squareup.picasso.Picasso
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class NewsFeedAdapter() : RecyclerView.Adapter<NewsFeedAdapter.MyViewHolder>() {

    private var articlesList: List<Articles>? = null

    fun setArticlesLiset(articlesList: List<Articles>) {
        this.articlesList = articlesList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.row_news_feed, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(articlesList?.get(position)!!)
        holder.itemView.setOnClickListener {
            if (holder.FeedNewsBox != null) {

            } else {

            }
        }
    }

    override fun getItemCount(): Int {
        if (articlesList == null) return 0
        else return articlesList?.size!!
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val FeedNewsBox = itemView.findViewById<LinearLayout>(R.id.FeedNewsBox)
        val imageViewSource = itemView.findViewById<ImageView>(R.id.imageViewSource)
        val textViewPublishedAt = itemView.findViewById<TextView>(R.id.textViewPublishedAt)
        val textViewAuthor = itemView.findViewById<TextView>(R.id.textViewAuthor)
        val textViewTitle = itemView.findViewById<TextView>(R.id.textViewTitle)

        fun bind(data: Articles) {
            /*val inputFormatter: DateTimeFormatter =
                DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SS'Z'", Locale.ENGLISH)

            val outputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyy", Locale.ENGLISH)
            val date = LocalDate.parse(data.publishedAt, inputFormatter)*/

            textViewPublishedAt.text = data.publishedAt
            textViewAuthor.text = data.author
            textViewTitle.text = data.title


            if (data.urlToImage != null) {
                Picasso.get().load(data.urlToImage).into(imageViewSource)
            }
        }
    }

}

