package com.mladenjovicic.newsapp.ui.newsEverything

import android.annotation.SuppressLint
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

class NewsEverythingFragment : Fragment(), AdapterView.OnItemSelectedListener {
    lateinit var recyclerViewNewsFeeds: NewsFeedAdapter
    lateinit var spinnerSortBy: Spinner
    lateinit var editTextSearch: EditText
    lateinit var btnSearch: Button
    var sortBy = "popularity"


    companion object {
        fun newInstance() = NewsEverythingFragment()
    }

    private lateinit var viewModel: NewsEverythingViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_news_everything, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeUI()
    }


    private fun subscribeUI() {
        viewModel = ViewModelsProviderUtils.getNewsEverythingViewModel(requireActivity())
        editTextSearch = view?.findViewById(R.id.editTextSearch)!!
        btnSearch = view?.findViewById(R.id.btnSearch)!!
        spinnerSortBy = view?.findViewById(R.id.spinnerSortBy)!!

        initRecyclerNewFeed()
        initSpinner()
        btnSearch.setOnClickListener {
            if (editTextSearch.text.isNullOrBlank()) {
                Toast.makeText(requireActivity(), "Error", Toast.LENGTH_LONG).show()
            } else {
                getServerNewsEverything(query = editTextSearch.text.toString(), sorting = sortBy)
            }
        }
    }


    private fun getServerNewsEverything(
        query: String? = null,
        sorting: String? = null,
        from: String? = null,
        to: String? = null,
    ) {
        val newsLoadingSpinner = view?.findViewById<ProgressBar>(R.id.newsLoadingSpinner)
        viewModel.getServerNewsEverything(query = query, sorting = sorting, from = from, to = to)
        viewModel.requestState.observe(viewLifecycleOwner) {
            if (it.pending)
                newsLoadingSpinner!!.visibility = View.VISIBLE
            if (it.successful) {
                newsLoadingSpinner!!.visibility = View.GONE
                viewModel.newsLiveData.observe(viewLifecycleOwner) { news ->
                    if (it != null) {
                        if (news.status == "ok") {
                            recyclerViewNewsFeeds.setArticlesLiset(news.articles, requireActivity())
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

    private fun initRecyclerNewFeed() {
        val recyclerViewNewsFeed = view?.findViewById<RecyclerView>(R.id.recyclerViewNewsFeed)
        recyclerViewNewsFeeds = NewsFeedAdapter()
        recyclerViewNewsFeed?.adapter = recyclerViewNewsFeeds
    }

    private fun initSpinner() {
        val adapterSortBy =
            ArrayAdapter.createFromResource(requireContext(), R.array.sortBy, R.layout.spinner_item)
        adapterSortBy.setDropDownViewResource(R.layout.spinner_dropdown_item)
        spinnerSortBy.adapter = adapterSortBy
        spinnerSortBy.onItemSelectedListener = this
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        when (parent?.id) {
            R.id.spinnerSortBy -> {
                sortBy = parent.getItemAtPosition(position).toString()
            }
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
    }
}