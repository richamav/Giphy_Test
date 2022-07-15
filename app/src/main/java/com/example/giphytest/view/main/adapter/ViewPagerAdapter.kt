package com.example.giphytest.view.main.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class ViewPagerAdapter(supportFragmentManager: FragmentManager) : FragmentPagerAdapter(supportFragmentManager) {

    private  var fragmentList: ArrayList<Fragment> = ArrayList()
    private  var fragmentTitleList: ArrayList<String> = ArrayList()

    override fun getItem(position: Int): Fragment {
        return fragmentList.get(position)
    }

    override fun getPageTitle(position: Int): CharSequence {
        return fragmentTitleList.get(position)
    }

    override fun getCount(): Int {
        return fragmentList.size
    }

    fun addFragment(fragment: Fragment, title: String) {
        fragmentList.add(fragment)
        fragmentTitleList.add(title)
    }
}