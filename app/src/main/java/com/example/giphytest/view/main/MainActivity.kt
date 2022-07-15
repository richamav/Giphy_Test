package com.example.giphytest.view.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.giphytest.databinding.ActivityMainBinding
import com.example.giphytest.utils.hideKeyboard
import com.example.giphytest.view.main.adapter.ViewPagerAdapter
import com.example.giphytest.view.main.fragments.FavouriteFragment
import com.example.giphytest.view.main.fragments.list.ListFragment
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupTabLayout()
    }

    private fun setupTabLayout() {
        setSupportActionBar(binding.toolbar)
        setupFragmentsForViewPager()
        binding.tablayout.setupWithViewPager(binding.viewpager)
        setupTabSelectedListener()
    }

    private fun setupFragmentsForViewPager() {
        var adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(ListFragment(), "List")
        adapter.addFragment(FavouriteFragment(), "Fav")
        binding.viewpager.adapter = adapter
    }

    private fun setupTabSelectedListener(){
        binding.tablayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab) {
                if(tab.position ==1) hideKeyboard()
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
    }
}