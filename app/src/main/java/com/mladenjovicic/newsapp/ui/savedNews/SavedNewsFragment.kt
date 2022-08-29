package com.mladenjovicic.newsapp.ui.savedNews

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mladenjovicic.newsapp.R
import com.mladenjovicic.newsapp.ViewModelsProviderUtils
import com.mladenjovicic.newsapp.ui.adapter.NewsFeedHistoryAdapter

class SavedNewsFragment : Fragment() {

    companion object {
        fun newInstance() = SavedNewsFragment()
    }

    private lateinit var viewModel: SavedNewsViewModel
    private lateinit var recyclerViewNewsHistoryFeeds: NewsFeedHistoryAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_saved_news, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        subscribeUI()
    }

    private fun subscribeUI() {
        viewModel = ViewModelsProviderUtils.getSavedNewsViewModel(requireActivity())
        viewModel.getNewsLocal()
        initRecyclerNewFeed()
        viewModel.newsLocalLiveData!!.observe(requireActivity()) { listNews ->
            if (listNews != null) {
                recyclerViewNewsHistoryFeeds.setArticlesList(listNews)
                recyclerViewNewsHistoryFeeds.notifyDataSetChanged()
            }
        }
    }

    private fun initRecyclerNewFeed() {
        val recyclerViewHistory = view?.findViewById<RecyclerView>(R.id.recyclerViewHistory)
        recyclerViewNewsHistoryFeeds = NewsFeedHistoryAdapter()
        recyclerViewHistory?.adapter = recyclerViewNewsHistoryFeeds
        recyclerViewNewsHistoryFeeds.setOnItemClickListener { news ->
            val myIntent = Intent(Intent.ACTION_VIEW, Uri.parse(news.url))
            startActivity(myIntent)
        }
    }

}