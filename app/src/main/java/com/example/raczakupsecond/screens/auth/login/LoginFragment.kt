package com.example.raczakupsecond.screens.auth.login

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.raczakupsecond.R
import com.example.raczakupsecond.databinding.FragmentLoginBinding
import com.example.raczakupsecond.screens.auth.checkcode.CheckCodeFragment

class LoginFragment : Fragment(R.layout.fragment_login) {
    private lateinit var binding : FragmentLoginBinding
    private val viewModel: LoginFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val nums: List<Char> = listOf(
            '0',
            '1',
            '2',
            '3',
            '4',
            '5',
            '6',
            '7',
            '8',
            '9',
        )

        with(binding){

            editTextLoginNumber.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun afterTextChanged(p0: Editable?) {
                    if ((p0.toString().length) != 0) {
                        for (i in p0.toString()) {
                            if (i !in nums) {
                                editTextLoginNumber.error = "Некорректный символ \'$i\'"
                            }
                        }
                    }
                }

            })

            buttonLogin.setOnClickListener {
                val phone = editTextLoginNumber.text.toString()
                var access = true
                for (i in phone) {
                    if (i !in nums) {
                        editTextLoginNumber.error = "Некорректный символ \'$i\'"
                        access = false
                    }
                }
                if (phone == "") {
                    editTextLoginNumber.error = "Введите номер телефона"
                } else if(phone.length != 10) {
                    editTextLoginNumber.error = "Номер введен некорректно"
                } else if (access) {
                    viewModel.login("+7$phone")
                    findNavController().navigate(
                        R.id.action_loginFragment_to_checkCodeFragment,
                        bundleOf(CheckCodeFragment.PHONE_NUMBER to "+7$phone")
                    )
                }
            }
        }
    }
}