package com.mladenjovicic.newsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.mladenjovicic.newsapp.ui.newsEverything.NewsEverythingFragment
import com.mladenjovicic.newsapp.ui.newsTopHeadlines.NewsTopHeadlinesFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)

        val newsEverythingView = NewsEverythingFragment()
        val newsTopHeadlinesView = NewsTopHeadlinesFragment()

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, NewsEverythingFragment.newInstance())
                .commitNow()
            bottomNavigationView.selectedItemId = R.id.nav_everything
        }

        bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_everything -> setCurrentFragment(newsEverythingView)
                R.id.nav_topHeadlines -> setCurrentFragment(newsTopHeadlinesView)
            }
            true
        }
    }

    private fun setCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.container, fragment)
            commit()
        }
}