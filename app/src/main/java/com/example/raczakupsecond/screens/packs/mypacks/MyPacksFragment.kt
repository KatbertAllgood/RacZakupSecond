package com.example.raczakupsecond.screens.packs.mypacks

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.raczakupsecond.R
import com.example.raczakupsecond.databinding.FragmentMyPacksBinding

class MyPacksFragment : Fragment(R.layout.fragment_my_packs) {
    lateinit var binding : FragmentMyPacksBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMyPacksBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonAddNewPack.setOnClickListener {
            findNavController().navigate(R.id.action_myPacksFragment_to_editPackFragment)
        }
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
    }

}