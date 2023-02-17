package com.example.raczakupsecond.screens.start.toauth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.raczakupsecond.R
import com.example.raczakupsecond.databinding.FragmentToAuthBinding

class ToAuthFragment : Fragment(R.layout.fragment_to_auth) {
    lateinit var binding : FragmentToAuthBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentToAuthBinding.inflate(layoutInflater)
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