package com.mladenjovicic.newsapp.ui.newsTopHeadlines

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.mladenjovicic.newsapp.R
import com.mladenjovicic.newsapp.ViewModelsProviderUtils
import com.mladenjovicic.newsapp.ui.adapter.NewsFeedAdapter

class NewsTopHeadlinesFragment : Fragment(), AdapterView.OnItemSelectedListener {

    lateinit var recyclerViewNewsFeeds: NewsFeedAdapter
    lateinit var spinnerCategory: Spinner


    companion object {
        fun newInstance() = NewsTopHeadlinesFragment()
    }

    private lateinit var viewModel: NewsTopHeadlinesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_top_headlines, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        subscribeUI()
    }

    private fun subscribeUI() {
        viewModel = ViewModelsProviderUtils.getNewsTopHeadlinesViewModel(requireActivity())
        spinnerCategory = view?.findViewById(R.id.spinnerCategory)!!

        initRecyclerNewsTopHeadlinesFeed()
        initSpinner()
        getServerNewsTopHeadlines(category = "business", country = "us")
    }

    private fun getServerNewsTopHeadlines(
        country: String? = null,
        category: String? = null,
        sources: String? = null,
        domains: String? = null,
    ) {
        val newsLoadingSpinner =
            view?.findViewById<ProgressBar>(R.id.newsTopHeadlinesLoadingSpinner)
        viewModel.getServerNewsTopHeadlines(
            country = country,
            category = category,
            sources = sources,
            domains = domains
        )
        viewModel.requestState.observe(viewLifecycleOwner) {
            if (it.pending)
                newsLoadingSpinner!!.visibility = View.VISIBLE
            if (it.successful) {
                newsLoadingSpinner!!.visibility = View.GONE
                viewModel.newsLiveData.observe(viewLifecycleOwner) { news ->
                    if (it != null) {
                        if (news.status == "ok") {
                            recyclerViewNewsFeeds.setArticlesList(news.articles)
                            recyclerViewNewsFeeds.notifyDataSetChanged()
                        } else {
                            Toast.makeText(
                                requireContext(),
                                "Error: ${news.message}",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                }
            }
        }
    }

    private fun initRecyclerNewsTopHeadlinesFeed() {
        val recyclerViewNewsFeed =
            view?.findViewById<RecyclerView>(R.id.recyclerViewTopHeadlinesNewsFeed)
        recyclerViewNewsFeeds = NewsFeedAdapter()
        recyclerViewNewsFeed?.adapter = recyclerViewNewsFeeds
        recyclerViewNewsFeeds.setOnItemClickListener { news ->
            viewModel.addNewsLocal(
                author = news.author,
                title = news.title,
                description = news.description,
                publishedAt = news.publishedAt,
                content = news.content,
                url = news.url,
                urlToImage = news.urlToImage,
                sourceId = news.source.id,
                sourceName = news.source.name,
                timeStampSave = System.currentTimeMillis().toString()
            )
            val myIntent = Intent(Intent.ACTION_VIEW, Uri.parse(news.url))
            startActivity(myIntent)
        }
    }

    private fun initSpinner() {
        val adapterCategory = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.category,
            R.layout.spinner_item
        )
        adapterCategory.setDropDownViewResource(R.layout.spinner_dropdown_item)
        spinnerCategory.adapter = adapterCategory
        spinnerCategory.onItemSelectedListener = this
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        when (parent?.id) {
            R.id.spinnerCategory -> {
                val category = parent.getItemAtPosition(position).toString()
                getServerNewsTopHeadlines(category = category)
            }
        }

    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }

}