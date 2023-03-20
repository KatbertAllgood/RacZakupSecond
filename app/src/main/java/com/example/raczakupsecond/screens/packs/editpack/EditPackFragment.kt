package com.example.raczakupsecond.screens.packs.editpack

import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.raczakupsecond.R
import com.example.raczakupsecond.databinding.FragmentEditPackBinding

class EditPackFragment : Fragment() {
    lateinit var binding : FragmentEditPackBinding
    private val viewModel : EditPackFragmentVM by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEditPackBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //region подчеркивание кнопки "заказать для себя"

        val spannableOrderForMyself = SpannableString(getString(R.string.for_me))
        spannableOrderForMyself.setSpan(UnderlineSpan(), 0, spannableOrderForMyself.length, 0)
        binding.tvButtonOrderForMyself.text = spannableOrderForMyself

        //endregion

    }

    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
    }

}