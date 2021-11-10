package com.example.smoketest.fragments.menu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.smoketest.R
import com.example.smoketest.databinding.FragmentRoomSearchBinding


class RoomSearchFragment : Fragment() {

    private lateinit var binding: FragmentRoomSearchBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRoomSearchBinding.inflate(inflater)
        val view = binding.root
        return view
    }


}