package com.example.raczakupsecond.screens.auth.checkcode

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.raczakupsecond.R
import com.example.raczakupsecond.databinding.FragmentCheckCodeBinding

class CheckCodeFragment : Fragment(R.layout.fragment_check_code) {
    private lateinit var binding : FragmentCheckCodeBinding
    private val viewModel : CheckCodeFragmentVM by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCheckCodeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val phoneNumber: String = requireArguments().getString(PHONE_NUMBER)?: ""
        val code: MutableList<String> = MutableList(4) {""}

        with(binding) {

            toolbarAuth.onClick {
                findNavController().popBackStack()
            }

            viewModel.getResponseSuccess().observe(viewLifecycleOwner) {
                when (it) {
                    true -> {

                    }
                    false -> {
                        Toast.makeText(
                            activity,
                            "Неверный код",
                            Toast.LENGTH_SHORT
                        ).show()
                        listOf(
                            editTextCodeNum1,
                            editTextCodeNum2,
                            editTextCodeNum3,
                            editTextCodeNum4
                        ).forEach{
                                editText ->
                            editText.text.clear()
                        }
                        for (i in 0 until code.size) {
                            code [i] = ""
                        }
                        editTextCodeNum1.requestFocus()
                    }
                }
            }

            editTextCodeNum1.requestFocus()
            editTextCodeNum1.addTextChangedListener(object: TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun afterTextChanged(p0: Editable?) {
                    if (p0.toString() != "") {
                        code[0] = p0.toString()
                        editTextCodeNum2.requestFocus()
                    }
                }

            })

            editTextCodeNum2.addTextChangedListener(object: TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun afterTextChanged(p0: Editable?) {
                    if (p0.toString() != "") {
                        code[1] = p0.toString()
                        editTextCodeNum3.requestFocus()
                    }
                }

            })

            editTextCodeNum3.addTextChangedListener(object: TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun afterTextChanged(p0: Editable?) {
                    if (p0.toString() != "") {
                        code[2] = p0.toString()
                        editTextCodeNum4.requestFocus()
                    }
                }

            })

            editTextCodeNum4.addTextChangedListener(object: TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun afterTextChanged(p0: Editable?) {
                    if (p0.toString() != "") {
                        code[3] = p0.toString()
                        Log.d("CODE_PHONE", "${code.joinToString( separator = "" )} --- $phoneNumber")
                        viewModel.sendCode(
                            phoneNumber,
                            code.joinToString( separator = "" )
                        )
                        findNavController().navigate(R.id.navigation_shop)
                    }
                }

            })

        }
    }

    companion object {
        const val PHONE_NUMBER = "phoneNum"
    }

}