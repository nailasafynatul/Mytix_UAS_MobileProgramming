package com.app.mytix

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var bottomNav: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNav = findViewById(R.id.bottomNavigation)

        if (savedInstanceState == null) {

            when (intent.getStringExtra("openFragment")) {

                "ticket" -> {
                    replaceFragment(TicketFragment())
                    bottomNav.selectedItemId = R.id.nav_ticket
                }

                "home" -> {
                    replaceFragment(HomeFragment())
                    bottomNav.selectedItemId = R.id.nav_home
                }

                "favorite" -> {
                    replaceFragment(FavoriteFragment())
                    bottomNav.selectedItemId = R.id.nav_favorite
                }

                "profile" -> {
                    replaceFragment(ProfileFragment())
                    bottomNav.selectedItemId = R.id.nav_profile
                }

                else -> {
                    replaceFragment(HomeFragment())
                    bottomNav.selectedItemId = R.id.nav_home
                }
            }
        }

        bottomNav.setOnItemSelectedListener {

            when (it.itemId) {

                R.id.nav_home -> {
                    replaceFragment(HomeFragment())
                    true
                }

                R.id.nav_ticket -> {
                    replaceFragment(TicketFragment())
                    true
                }

                R.id.nav_favorite -> {
                    replaceFragment(FavoriteFragment())
                    true
                }

                R.id.nav_profile -> {
                    replaceFragment(ProfileFragment())
                    true
                }

                else -> false
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {

        supportFragmentManager.beginTransaction()
            .replace(
                R.id.fragmentContainer,
                fragment
            )
            .commit()
    }
}