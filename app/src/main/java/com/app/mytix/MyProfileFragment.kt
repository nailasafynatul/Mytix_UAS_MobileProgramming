package com.app.mytix

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ScrollView
import android.widget.Toast
import androidx.fragment.app.Fragment

class MyProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view = inflater.inflate(
            R.layout.fragment_my_profile,
            container,
            false
        )

        val btnBack =
            view.findViewById<ImageButton>(R.id.btnBack)

        val btnSimpan =
            view.findViewById<Button>(R.id.btnSimpan)

        val etUsername =
            view.findViewById<EditText>(R.id.etUsername)

        val etNama =
            view.findViewById<EditText>(R.id.etNama)

        val etEmail =
            view.findViewById<EditText>(R.id.etEmail)

        etUsername.setText(ProfileFragment.currentUsername)
        etNama.setText(ProfileFragment.currentNama)
        etEmail.setText(ProfileFragment.currentEmail)

        btnBack.setOnClickListener {

            parentFragmentManager.popBackStack()

            val profileFragment =
                parentFragment as? ProfileFragment

            profileFragment?.view
                ?.findViewById<ScrollView>(R.id.profileScroll)
                ?.visibility = View.VISIBLE

            profileFragment?.view
                ?.findViewById<View>(R.id.profileFragmentContainer)
                ?.visibility = View.GONE
        }

        btnSimpan.setOnClickListener {

            val username =
                etUsername.text.toString().trim()

            val nama =
                etNama.text.toString().trim()

            val email =
                etEmail.text.toString().trim()

            if (
                username.isEmpty() ||
                nama.isEmpty() ||
                email.isEmpty()
            ) {

                Toast.makeText(
                    requireContext(),
                    "Semua data harus diisi",
                    Toast.LENGTH_SHORT
                ).show()

            } else {

                ProfileFragment.currentUsername =
                    username

                ProfileFragment.currentNama =
                    nama

                ProfileFragment.currentEmail =
                    email

                val profileFragment =
                    parentFragment as? ProfileFragment

                profileFragment?.updateProfile(
                    nama,
                    email
                )

                Toast.makeText(
                    requireContext(),
                    "Profile berhasil diperbarui",
                    Toast.LENGTH_SHORT
                ).show()

                parentFragmentManager.popBackStack()

                profileFragment?.view
                    ?.findViewById<ScrollView>(R.id.profileScroll)
                    ?.visibility = View.VISIBLE

                profileFragment?.view
                    ?.findViewById<View>(R.id.profileFragmentContainer)
                    ?.visibility = View.GONE
            }
        }

        return view
    }
}