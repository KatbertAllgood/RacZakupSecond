package com.example.raczakupsecond.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout
import com.example.raczakupsecond.databinding.ToolbarAuthBinding

class ToolbarViewAuth(
    context: Context,
    attrs: AttributeSet
) : RelativeLayout(context, attrs) {
    private val binding : ToolbarAuthBinding =
        ToolbarAuthBinding
            .inflate(LayoutInflater.from(context), this, false)

    init {
        addView(binding.root)
    }

    fun onClick(action: (View) -> Unit) {
        binding.imageViewToolbarButtonBack.setOnClickListener(action)
    }
}