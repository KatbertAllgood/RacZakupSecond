package com.example.raczakupsecond.screens.profile.profilepage

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.data.utils.ApplicationPreferences
import com.example.domain.utils.Constants
import com.example.raczakupsecond.R
import com.example.raczakupsecond.databinding.FragmentProfileBinding
import com.example.raczakupsecond.screens.profile.editgroup.EditGroupFragment
import com.example.raczakupsecond.screens.profile.profilepage.adapters.ProfileFamilyGroupAdapter

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

        val phoneNumber: String = ApplicationPreferences.getPhoneNubmer ?: ""
//        binding.tvContactsPhone.text = "+7(${phoneNumber[2]}${phoneNumber[3]}${phoneNumber[4]})" +
//                "${phoneNumber[5]}${phoneNumber[6]}${phoneNumber[7]}-${phoneNumber[8]}${phoneNumber[9]}-" +
//                "${phoneNumber[10]}${phoneNumber[11]}"

//        val numList: MutableList<Char> = phoneNumber.toMutableList()
//        Log.d("PHONE", numList.joinToString())

        binding.tvContactsPhone.text = getString(R.string.phone_num,
            phoneNumber[2],
            phoneNumber[3],
            phoneNumber[4],
            phoneNumber[5],
            phoneNumber[6],
            phoneNumber[7],
            phoneNumber[8],
            phoneNumber[9],
            phoneNumber[10],
            phoneNumber[11],
        )

        binding.buttonProfileFragmentAddGroup.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_editGroupFragment,
                bundleOf(Constants.MODE to Constants.CREATE_MODE))
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getFamilies()

        viewModel.getAllFamilies().observe(viewLifecycleOwner) {
            val familyGroupAdapter = ProfileFamilyGroupAdapter(it)

            binding.apply{
                rcViewGroups.layoutManager = LinearLayoutManager(requireContext())
                rcViewGroups.isNestedScrollingEnabled = false

                familyGroupAdapter.onItemClick = {item ->
                    Log.d("ITEM_ID ", item.id.toString())
                    findNavController().navigate(R.id.action_profileFragment_to_editGroupFragment,
                        bundleOf(
                            Constants.MODE to Constants.EDIT_MODE,
                            EditGroupFragment.FAMILY_ID to item.id))
                }

                rcViewGroups.adapter = familyGroupAdapter
            }
        }

    }
}