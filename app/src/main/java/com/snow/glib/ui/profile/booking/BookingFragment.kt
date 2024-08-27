package com.snow.glib.ui.profile.booking

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.snow.glib.R
import com.snow.glib.databinding.FragmentBookingBinding

class BookingFragment : Fragment() {

    private lateinit var binding: FragmentBookingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBookingBinding.inflate(inflater, container, false)
        binding.backBtnBooking.setOnClickListener{
            Navigation.findNavController(binding.root).navigate(R.id.profileFragment)
        }

        return binding.root
    }
}