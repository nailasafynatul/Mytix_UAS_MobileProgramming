package com.app.mytix

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class TicketPagerAdapter(
    fragment: Fragment
) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 4

    override fun createFragment(position: Int): Fragment {

        return when (position) {

            0 -> SemuaFragment()

            1 -> AkanDatangFragment()

            2 -> SelesaiFragment()

            else -> DibatalkanFragment()
        }
    }
}