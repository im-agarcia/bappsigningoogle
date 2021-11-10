package com.example.smoketest.fragments.menu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.smoketest.R
import com.example.smoketest.databinding.FragmentFriendSearchBinding


class FriendSearchFragment : Fragment() {

    private lateinit var binding : FragmentFriendSearchBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFriendSearchBinding.inflate(inflater)
        var view = binding.root
        return view
    }


}