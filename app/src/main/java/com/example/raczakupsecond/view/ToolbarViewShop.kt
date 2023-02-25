package com.example.raczakupsecond.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import com.example.raczakupsecond.databinding.ToolbarShopBinding

class ToolbarViewShop(
    context: Context,
    attrs: AttributeSet
) : RelativeLayout(context, attrs) {
    private val binding : ToolbarShopBinding =
        ToolbarShopBinding
            .inflate(LayoutInflater.from(context), this, false)

    private val param = binding.textViewToolbarTitle.layoutParams as
            ViewGroup.MarginLayoutParams


    init {
        addView(binding.root)
    }

    fun setTitle(title: String) {
        binding.textViewToolbarTitle.text = title
    }

    fun onClick(action: (View) -> Unit) {
        param.setMargins(72, 0 , 0 , 0)
        binding.textViewToolbarTitle.layoutParams = param
        binding.imageViewToolbarButtonBack.visibility = View.VISIBLE
        binding.imageViewToolbarButtonBack.setOnClickListener(action)
    }
}