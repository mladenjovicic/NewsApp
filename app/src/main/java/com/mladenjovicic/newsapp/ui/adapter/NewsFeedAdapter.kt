package com.mladenjovicic.newsapp.ui.adapter

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mladenjovicic.newsapp.R
import com.mladenjovicic.newsapp.data.model.server.ArticlesServerModel
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.*


class NewsFeedAdapter : RecyclerView.Adapter<NewsFeedAdapter.MyViewHolder>() {

    private var articlesList: List<ArticlesServerModel>? = null
    private lateinit var mContext: Activity


    fun setArticlesLiset(articlesList: List<ArticlesServerModel>, mContext: Activity) {
        this.articlesList = articlesList
        this.mContext = mContext
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.row_news_feed, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(articlesList?.get(position)!!)
        holder.itemView.setOnClickListener {


            val myIntent = Intent(Intent.ACTION_VIEW, Uri.parse(articlesList?.get(position)!!.url))
            mContext.startActivity(myIntent)
        }
    }


    override fun getItemCount(): Int {
        return if (articlesList == null) 0
        else articlesList?.size!!
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageViewSource = itemView.findViewById<ImageView>(R.id.imageViewSource)
        private val textViewPublishedAt = itemView.findViewById<TextView>(R.id.textViewPublishedAt)
        private val tvDescription = itemView.findViewById<TextView>(R.id.tvDescription)
        private val tvTitle = itemView.findViewById<TextView>(R.id.tvTitle)
        private val tvSource = itemView.findViewById<TextView>(R.id.tvSource)

        fun bind(data: ArticlesServerModel) {
            val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
            val output = SimpleDateFormat("dd.MM.yyyy")
            val d: Date = sdf.parse(data.publishedAt) as Date
            val formattedTime: String = output.format(d)

            textViewPublishedAt.text = formattedTime
            tvTitle.text = data.title
            tvDescription.text = data.description
            tvSource.text = data.source.name

            Picasso.get().load(data.urlToImage).into(imageViewSource)
        }
    }
}

