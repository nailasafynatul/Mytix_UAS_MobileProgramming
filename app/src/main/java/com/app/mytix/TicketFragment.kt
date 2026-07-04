package com.app.mytix

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class TicketFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return inflater.inflate(
            R.layout.fragment_ticket,
            container,
            false
        )
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        val tabLayout =
            view.findViewById<TabLayout>(R.id.tabLayout)

        val viewPager =
            view.findViewById<ViewPager2>(R.id.viewPager)

        viewPager.adapter =
            TicketPagerAdapter(this)

        TabLayoutMediator(
            tabLayout,
            viewPager
        ) { tab, position ->

            when (position) {

                0 -> tab.text = "Semua"

                1 -> tab.text = "Akan Datang"

                2 -> tab.text = "Selesai"

                3 -> tab.text = "Dibatalkan"
            }

        }.attach()
    }
}