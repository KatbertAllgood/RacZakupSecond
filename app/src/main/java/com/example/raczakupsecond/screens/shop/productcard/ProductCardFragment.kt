package com.example.raczakupsecond.screens.shop.productcard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.raczakupsecond.R
import com.example.raczakupsecond.databinding.FragmentProductCardBinding

class ProductCardFragment : Fragment(R.layout.fragment_product_card) {
    lateinit var binding : FragmentProductCardBinding
    private val viewModel : ProductCardFragmentVM by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProductCardBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

}