package com.example.raczakupsecond.screens.start

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.raczakupsecond.R
import com.example.raczakupsecond.databinding.FragmentStartBinding

class StartFragment : Fragment(R.layout.fragment_start) {
    lateinit var binding : FragmentStartBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStartBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonLogin.setOnClickListener {
            findNavController().navigate(R.id.action_toAuthFragment_to_loginFragment)
        }

        binding.textViewButtonFastOrder.setOnClickListener {
            //нужно сделать
        }

    }

}