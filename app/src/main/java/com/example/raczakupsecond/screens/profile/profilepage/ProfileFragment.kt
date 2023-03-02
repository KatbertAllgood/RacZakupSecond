package com.example.raczakupsecond.screens.profile.profilepage

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.domain.usecase.families.GetFamiliesUseCase
import com.example.raczakupsecond.R
import com.example.raczakupsecond.app.App
import com.example.raczakupsecond.databinding.FragmentProfileBinding
import com.example.raczakupsecond.screens.auth.checkcode.CheckCodeFragmentVM
import com.example.raczakupsecond.screens.profile.profilepage.adapter.FamilyGroupAdapter

class ProfileFragment : Fragment(R.layout.fragment_profile) {
    lateinit var binding : FragmentProfileBinding
    private val viewModel : ProfileFragmentVM by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getAllFamilies().observe(viewLifecycleOwner) {
            val familyGroupAdapter = FamilyGroupAdapter(it)
            binding.apply{
                rcViewGroups.layoutManager = LinearLayoutManager(requireContext())
                rcViewGroups.adapter = familyGroupAdapter
            }
        }

    }
}