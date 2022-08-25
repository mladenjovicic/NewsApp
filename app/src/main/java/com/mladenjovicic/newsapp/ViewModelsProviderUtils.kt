package com.mladenjovicic.newsapp

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mladenjovicic.newsapp.ui.newsEverything.NewsEverythingViewModel
import com.mladenjovicic.newsapp.ui.newsTopHeadlines.NewsTopHeadlinesViewModel
import com.mladenjovicic.newsapp.ui.savedNews.SavedNewsViewModel

object ViewModelsProviderUtils {

    fun getNewsEverythingViewModel(activity: FragmentActivity) =
        ViewModelProvider(activity, NewsEverythingViewModelFactory())[NewsEverythingViewModel::class.java]

    class NewsEverythingViewModelFactory : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return InjectorUtils.getNewsEverythingViewModel() as T
        }
    }



    fun getSavedNewsViewModel(activity: FragmentActivity) =
        ViewModelProvider(activity, SavedNewsViewModelFactory())[SavedNewsViewModel::class.java]

    class SavedNewsViewModelFactory : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return InjectorUtils.getSavedNewsViewModel() as T
        }
    }

    fun getNewsTopHeadlinesViewModel(activity: FragmentActivity) =
        ViewModelProvider(activity, NewsTopHeadlinesViewModelFactory())[NewsTopHeadlinesViewModel::class.java]

    class NewsTopHeadlinesViewModelFactory : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return InjectorUtils.getNewsTopHeadlinesViewModel() as T
        }
    }
}