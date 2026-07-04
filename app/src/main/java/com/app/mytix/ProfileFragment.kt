package com.app.mytix

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ScrollView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.cardview.widget.CardView

class ProfileFragment : Fragment() {

    companion object {
        var currentUsername = "Kelompok 2"
        var currentNama = "Kelompok 2"
        var currentEmail = "kelompok2@gmail.com"
    }

    private lateinit var profileScroll: ScrollView
    private lateinit var profileFragmentContainer: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(
            R.layout.fragment_profile,
            container,
            false
        )
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        profileScroll =
            view.findViewById(R.id.profileScroll)

        profileFragmentContainer =
            view.findViewById(R.id.profileFragmentContainer)

        val cardMyProfile =
            view.findViewById<CardView>(R.id.cardMyProfile)

        val cardSettings =
            view.findViewById<CardView>(R.id.cardSettings)

        cardMyProfile.setOnClickListener {

            profileScroll.visibility = View.GONE
            profileFragmentContainer.visibility = View.VISIBLE

            childFragmentManager.beginTransaction()
                .replace(
                    R.id.profileFragmentContainer,
                    MyProfileFragment()
                )
                .addToBackStack(null)
                .commit()
        }

        cardSettings.setOnClickListener {

            profileScroll.visibility = View.GONE
            profileFragmentContainer.visibility = View.VISIBLE

            childFragmentManager.beginTransaction()
                .replace(
                    R.id.profileFragmentContainer,
                    SettingsFragment()
                )
                .addToBackStack(null)
                .commit()
        }

        updateProfile(
            currentNama,
            currentEmail
        )
    }

    fun updateProfile(
        nama: String,
        email: String
    ) {

        currentNama = nama
        currentEmail = email

        view?.findViewById<TextView>(R.id.tvNama)?.text = nama
        view?.findViewById<TextView>(R.id.tvEmail)?.text = email
    }

    override fun onResume() {
        super.onResume()

        updateProfile(
            currentNama,
            currentEmail
        )
    }
}