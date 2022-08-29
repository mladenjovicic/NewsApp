package com.mladenjovicic.newsapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mladenjovicic.newsapp.R
import com.mladenjovicic.newsapp.data.model.local.ArticlesLocalModel
import com.mladenjovicic.newsapp.data.model.server.ArticlesServerModel
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.*

class NewsFeedHistoryAdapter : RecyclerView.Adapter<NewsFeedHistoryAdapter.MyViewHolder>() {


    private var articlesList: List<ArticlesLocalModel>? = null


    fun setArticlesList(articlesList: List<ArticlesLocalModel>) {
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
            onItemClickListener?.let { it(articlesList?.get(position)!!) }
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

        fun bind(data: ArticlesLocalModel) {
            val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
            val output = SimpleDateFormat("dd.MM.yyyy")
            val d: Date = sdf.parse(data.publishedAt) as Date
            val formattedTime: String = output.format(d)

            textViewPublishedAt.text = formattedTime
            tvTitle.text = data.title
            tvDescription.text = data.description
            tvSource.text = data.sourceName

            if (data.urlToImage != null) {
                Picasso.get()
                    .load(data.urlToImage)
                    .error(R.drawable.noimageicon)
                    .into(imageViewSource)
            } else {
                Picasso.get()
                    .load(R.drawable.noimageicon)
                    .fit()
                    .into(imageViewSource)
            }


        }
    }


    private var onItemClickListener: ((ArticlesLocalModel) -> Unit)? = null

    fun setOnItemClickListener(listener: (ArticlesLocalModel) -> Unit) {
        onItemClickListener = listener
    }
}