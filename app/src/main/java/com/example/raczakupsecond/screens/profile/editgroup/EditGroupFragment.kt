package com.example.raczakupsecond.screens.profile.editgroup

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.domain.models.families.FamilyDomain
import com.example.domain.models.families.MemberDomain
import com.example.domain.utils.Constants
import com.example.raczakupsecond.R
import com.example.raczakupsecond.databinding.FragmentEditGroupBinding
import com.example.raczakupsecond.screens.profile.editgroup.adapter.FamilyMembersAdapter
import com.example.raczakupsecond.screens.profile.editmember.EditMemberFragment

class EditGroupFragment : Fragment(R.layout.fragment_edit_group) {
    lateinit var binding : FragmentEditGroupBinding
    private val viewModel : EditGroupFragmentVM by viewModels()

    private lateinit var mode: String

    private lateinit var familyId: String

    private lateinit var updatingFamily: FamilyDomain
    private lateinit var members: List<MemberDomain>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEditGroupBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mode = requireArguments().getString(Constants.MODE).toString()

        members = listOf()

        if (mode == Constants.CREATE_MODE) {

            updatingFamily = FamilyDomain()

            with(binding) {
                toolbarEditGroup.setTitle(getString(R.string.add_group_toolbar))
                buttonEditGroupDelete.visibility = View.GONE
                buttonEditGroupConfirm.text = getString(R.string.add_group)
            }

        } else if (mode == Constants.EDIT_MODE) {

//            Log.d("MODE", "EDIT")

//            val params = binding.buttonEditGroupConfirm.layoutParams as ViewGroup.MarginLayoutParams
//            params.setMargins(0, 24, 0, 20)
//            binding.buttonEditGroupConfirm.layoutParams = params

            binding.toolbarEditGroup.setTitle(getString(R.string.edit_group_toolbar))
            familyId = requireArguments().getInt(FAMILY_ID).toString()
            Log.d("family_id = ", familyId)


        }

        val navController = findNavController()
        navController
            .currentBackStackEntry
            ?.savedStateHandle
            ?.getLiveData<MemberDomain>("newMember")
            ?.observe(viewLifecycleOwner) {
                if (it.name != "") {
                    viewModel.addMember(it)
                }
            }
        navController
            .currentBackStackEntry
            ?.savedStateHandle
            ?.getLiveData<String>("deleteMember")
            ?.observe(viewLifecycleOwner) {
                if (it == "delete") {
                    viewModel.removeLastMember()
                }
            }

        with(binding) {
            editTextEditGroupTitle.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun afterTextChanged(p0: Editable?) {
                    updatingFamily.name = p0.toString()
                }

            })

            editGroupAddMember.setOnClickListener {
                if (mode == Constants.CREATE_MODE) {
                    findNavController().navigate(
                        R.id.action_editGroupFragment_to_editMemberFragment,
                        bundleOf(Constants.MODE to Constants.NESTED_CREATE_MODE)
                    )
                } else if (mode == Constants.EDIT_MODE) {
                    findNavController().navigate(
                        R.id.action_editGroupFragment_to_editMemberFragment,
                        bundleOf(
                            Constants.MODE to Constants.CREATE_MODE,
                            FAMILY_ID to familyId
                        )
                    )
                }
            }

            buttonEditGroupConfirm.setOnClickListener {
                if ((editTextEditGroupTitle.text.isEmpty()) ||
                    (editTextEditGroupTitle.text.toString() == "")) {
                    editTextEditGroupTitle.error = "Введите название группы"
                } else if (members.isEmpty() && mode == Constants.CREATE_MODE) {

                    val toast : Toast = Toast.makeText(
                        activity,
                        "Добавьте участников",
                        Toast.LENGTH_SHORT
                    )
                    toast.setGravity(Gravity.BOTTOM, 0, 200)
                    toast.show()

                } else {
                    if (mode == Constants.EDIT_MODE) {
                        viewModel.updateFamily(
                            familyId,
                            updatingFamily
                        )
                    } else if (mode == Constants.CREATE_MODE) {
                        viewModel.createFamily(
                            FamilyDomain(
                                name = editTextEditGroupTitle.text.toString(),
                                members = members
                            )
                        )
                    }
                    findNavController().popBackStack()
                }
            }

            buttonEditGroupDelete.setOnClickListener {

                val builder = AlertDialog.Builder(requireContext())
                val view = layoutInflater.inflate(R.layout.dialog_confirm_delete, null)
                builder
                    .setView(view)
                    .setCancelable(false)

                val b = builder.create()


                val confirmButton = view.findViewById<Button>(R.id.button_delete_dialog_confirm)
                val cancelButton = view.findViewById<Button>(R.id.button_delete_dialog_cancel)

                confirmButton.setOnClickListener {
                    viewModel.deleteFamily(familyId)
                    b.dismiss()
                    findNavController().popBackStack()
                }

                cancelButton.setOnClickListener {
//                    Log.d("CANCEL", "WORKS")
                    b.dismiss()
                }

                b.show()
            }

        }

    }

    override fun onResume() {
        super.onResume()
        if (mode == Constants.EDIT_MODE) {
            viewModel.getFamily(familyId)
        }

        viewModel.getFamilyLiveData().observe(viewLifecycleOwner) {

            updatingFamily = it

            binding.apply {
                editTextEditGroupTitle.setText(it.name)

                val membersAdapter = FamilyMembersAdapter(it.members)
                rcViewMembers.layoutManager = GridLayoutManager(requireContext(), 4)
                rcViewMembers.isNestedScrollingEnabled = false

                membersAdapter.onItemClick = { item ->
                    findNavController().navigate(
                        R.id.action_editGroupFragment_to_editMemberFragment,
                        bundleOf(
                            EditMemberFragment.MEMBER_ID to item.id,
                            EditMemberFragment.FAMILY_ID to familyId,
                            Constants.MODE to Constants.EDIT_MODE
                        )
                    )
                }
                rcViewMembers.adapter = membersAdapter
            }
        }

        viewModel.getMembersLiveData().observe(viewLifecycleOwner) {
            members = it

            binding.apply {

                val membersAdapter = FamilyMembersAdapter(members)
                rcViewMembers.layoutManager = GridLayoutManager(requireContext(), 4)
                rcViewMembers.isNestedScrollingEnabled = false

                membersAdapter.onItemClick = { item ->
                    findNavController().navigate(
                        R.id.action_editGroupFragment_to_editMemberFragment,
                        bundleOf(
                            Constants.MODE to Constants.CREATED_NESTED_CREATE_MODE,
                            "name" to item.name,
                            "gender" to item.gender,
                            "height" to item.height,
                            "weight" to item.weight,
                            "birthday" to item.birthday,
                            "age" to item.age
                        )
                    )
                }
                membersAdapter.itemPosition = { position ->
                    viewModel.removeMember(position)
                }

                rcViewMembers.adapter = membersAdapter
            }

        }


    }



    companion object {
        const val FAMILY_ID = "family_id"
    }

}