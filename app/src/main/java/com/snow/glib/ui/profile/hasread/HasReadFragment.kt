package com.snow.glib.ui.profile.hasread

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.snow.glib.R
import com.snow.glib.databinding.FragmentHasReadBinding

class HasReadFragment : Fragment() {

    private lateinit var binding: FragmentHasReadBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHasReadBinding.inflate(inflater, container, false)

        binding.backBtnHasRead.setOnClickListener{
            Navigation.findNavController(binding.root).navigate(R.id.profileFragment)
        }

        return binding.root
    }
}