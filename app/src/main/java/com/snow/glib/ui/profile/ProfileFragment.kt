package com.snow.glib.ui.profile

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.navigation.Navigation
import com.snow.glib.R
import com.snow.glib.databinding.FragmentProfileBinding
import com.snow.glib.ui.sign.SignInActivity

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(layoutInflater, container, false)
        binding.blockFavourite.setOnClickListener {
            Navigation.findNavController(binding.root).navigate(R.id.favouriteFragment)
        }
        binding.blockBooking.setOnClickListener {
            Navigation.findNavController(binding.root).navigate(R.id.bookingFragment)
        }
        binding.blockHasRead.setOnClickListener {
            Navigation.findNavController(binding.root).navigate(R.id.hasReadFragment)
        }
        binding.blockAdminCall.setOnClickListener {
            Navigation.findNavController(binding.root).navigate(R.id.callAdminFragment)
        }
        binding.blockMyComments.setOnClickListener {
            Navigation.findNavController(binding.root).navigate(R.id.myCommentsFragment)
        }

        binding.logOutBtn.setOnClickListener{
            val sh = requireActivity().getSharedPreferences("MySharedPref", Context.MODE_PRIVATE)
            val myEdit = sh.edit()
            myEdit.clear()
            myEdit.apply()

            val intent = Intent(context, SignInActivity::class.java)
            startActivity(intent)
            activity?.finish()
        }
        return binding.root
    }
}