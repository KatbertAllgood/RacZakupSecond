package com.example.raczakupsecond.screens.profile.editmember

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher

import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.NumberPicker
import android.widget.TextView
import androidx.core.content.ContentProviderCompat
import androidx.fragment.app.FragmentManager
import com.example.raczakupsecond.R
import com.example.raczakupsecond.databinding.FragmentEditMemberBinding
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.domain.models.families.MemberDomain
import com.example.domain.utils.Constants
import com.example.raczakupsecond.screens.profile.editgroup.EditGroupFragment

class EditMemberFragment : Fragment(R.layout.fragment_edit_member) {
    lateinit var binding : FragmentEditMemberBinding
    private val viewModel : EditMemberFragmentVM by viewModels()

    lateinit var mode: String

    lateinit var familyId : String
    lateinit var memberId: String
    lateinit var updatingMember : MemberDomain

    private var _name: String = ""
    private var _gender: String = ""
    private var _height: Int = 0
    private var _weight: Int = 0
    private var _birthday: String = ""
    private var _age: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEditMemberBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        mode = requireArguments().getString(Constants.MODE).toString()

        //region изменяемые статы мембера

        var birthDay: String = ""
        if ((mode == Constants.CREATE_MODE) || (mode == Constants.NESTED_CREATE_MODE)) {
            birthDay = viewModel.setCurrentDate()
        }
        var lvlOfActivity: String = ""
        var gender: String = Constants.MALE

        //endregion

        when (mode) {
            Constants.EDIT_MODE -> {
                familyId = requireArguments().getString(FAMILY_ID).toString()
                memberId = requireArguments().getInt(MEMBER_ID).toString()
                binding.toolbarEditMember.setTitle(getString(R.string.edit_member_toolbar))
            }
            Constants.CREATE_MODE -> {
                familyId = requireArguments().getString(FAMILY_ID).toString()
                binding.toolbarEditMember.setTitle(getString(R.string.create_member_toolbar))
                binding.buttonEditGroupDelete.visibility = View.GONE
                binding.buttonEditGroupConfirm.text = getString(R.string.add_member)

                binding.editTextEditedHeight.setText("185")
                binding.editTextEditedWeight.setText("100")
                viewModel.calculateImt(100, 185)
            }
            Constants.NESTED_CREATE_MODE -> {
                binding.buttonEditGroupDelete.visibility = View.GONE
                binding.buttonEditGroupConfirm.text = getString(R.string.add_member)
                binding.toolbarEditMember.setTitle(getString(R.string.create_member_toolbar))
                binding.editTextEditedHeight.setText("185")
                binding.editTextEditedWeight.setText("100")
                viewModel.calculateImt(100, 185)
            }
            Constants.CREATED_NESTED_CREATE_MODE -> {
                binding.toolbarEditMember.setTitle(getString(R.string.edit_member_toolbar))
                binding.editTextEditMemberName.setText(requireArguments().getString("name").toString())
                _name = requireArguments().getString("name").toString()
                binding.editTextEditedAge.setText(viewModel.calculateAge(requireArguments().getString("birthday").toString()).toString())
                birthDay = requireArguments().getString("birthday").toString()
                _birthday = birthDay
                binding.editTextEditedHeight.setText(requireArguments().getInt("height").toString())
                _height = requireArguments().getInt("height")
                binding.editTextEditedWeight.setText(requireArguments().getInt("weight").toString())
                _weight = requireArguments().getInt("weight")
                gender = requireArguments().getString("gender").toString()
                _gender = gender
                when (gender) {
                    Constants.MALE ->
                        binding.genderSelectionMale.setBackgroundResource(R.drawable.shape_green_selected)
                    Constants.FEMALE ->
                        binding.genderSelectionFemale.setBackgroundResource(R.drawable.shape_green_selected)
                }
                _age = requireArguments().getInt("age")
                viewModel.calculateImt(
                    requireArguments().getInt("weight"),
                    requireArguments().getInt("height")
                )
            }
        }

        viewModel.getMemberLiveData().observe(viewLifecycleOwner) {

            updatingMember = it

            viewModel.calculateImt(it.weight, it.height)

            binding.apply {
                editTextEditMemberName.setText(it.name)
                editTextEditedAge.setText(viewModel.calculateAge(it.birthday).toString())
                birthDay = it.birthday
                Log.d("RECIEVED_BIRTHDAY", birthDay)
                editTextEditedWeight.setText(it.weight.toString())
                editTextEditedHeight.setText(it.height.toString())

                gender = it.gender
                when (gender) {
                    Constants.MALE ->
                        genderSelectionMale.setBackgroundResource(R.drawable.shape_green_selected)
                    Constants.FEMALE ->
                        genderSelectionFemale.setBackgroundResource(R.drawable.shape_green_selected)
                }
            }
        }

        binding.apply {
            viewModel.updateProgressBars(
                listOf(
                    progressBar1,
                    progressBar2,
                    progressBar3,
                ),
                listOf(
                    progressBar1Full,
                    progressBar2Full,
                    progressBar3Full
                ),
                listOf(
                    progressBar1,
                    progressBar2,
                    progressBar3,
                    progressBar4
                )
            )
        }

        viewModel.getImtLiveData().observe(viewLifecycleOwner) {
//            updateProgressBars()
            binding.apply {

                viewModel.updateProgressBars(
                    listOf(
                        progressBar1,
                        progressBar2,
                        progressBar3,
                    ),
                    listOf(
                        progressBar1Full,
                        progressBar2Full,
                        progressBar3Full
                    ),
                    listOf(
                        progressBar1,
                        progressBar2,
                        progressBar3,
                        progressBar4
                    )
                )

                tvImtValue.text = it.toString()
                when {
                    it < 18.4 -> {
                        tvImtType.text = getString(R.string.imt_bottom_normal)
                        progressBar1.progress = (it * 10.0).toInt()
                    }
                    (it >= 18.4) && (it < 24.9) -> {
                        tvImtType.text = getString(R.string.imt_normal)

                        progressBar1.visibility = View.GONE
                        progressBar1Full.visibility = View.VISIBLE

//                        Log.d("PROGRESS", (it * 10.0).toInt().toString())
                        progressBar2.progress = (it * 10.0).toInt()
                    }
                    (it >= 24.9) && (it < 29.9) -> {
                        tvImtType.text = getString(R.string.imt_above_normal)

                        listOf(
                            progressBar1,
                            progressBar2
                        ).forEach {it.visibility = View.GONE}

                        listOf(
                            progressBar1Full,
                            progressBar2Full
                        ).forEach {it.visibility = View.VISIBLE}

                        progressBar3.progress = (it * 10.0).toInt()
                    }
                    it >= 29.9 -> {
                        tvImtType.text = getString(R.string.imt_above_predfat)
                        progressBar1.visibility = View.GONE
                        progressBar1Full.visibility = View.VISIBLE

                        listOf(
                            progressBar1,
                            progressBar2,
                            progressBar3
                        ).forEach {it.visibility = View.GONE}

                        listOf(
                            progressBar1Full,
                            progressBar2Full,
                            progressBar3Full
                        ).forEach {it.visibility = View.VISIBLE}

                        progressBar4.progress = (it * 10.0).toInt()
                    }
                }
            }
        }

        with(binding) {

            genderSelectionMale.setOnClickListener {
                it.setBackgroundResource(R.drawable.shape_green_selected)
                binding.genderSelectionFemale.background = null
                gender = Constants.MALE
            }

            genderSelectionFemale.setOnClickListener {
                it.setBackgroundResource(R.drawable.shape_green_selected)
                binding.genderSelectionMale.background = null
                gender = Constants.FEMALE
            }

            editMemberActivityLvlSitting.setOnClickListener {
                activityLvlBackgroundSitting.setImageResource(
                    R.drawable.shape_circle_activity_lvl_focudes_background
                )

                listOf(
                    activityLvlBackgroundActive,
                    activityLvlBackgroundSoactive
                ).forEach {
                    it.setImageResource(
                        R.drawable.shape_circle_activity_lvl_background
                    )
                }

                lvlOfActivity = "sitting"
            }

            editMemberActivityLvlActive.setOnClickListener {
                activityLvlBackgroundActive.setImageResource(
                    R.drawable.shape_circle_activity_lvl_focudes_background
                )

                listOf(
                    activityLvlBackgroundSitting,
                    activityLvlBackgroundSoactive
                ).forEach {
                    it.setImageResource(
                        R.drawable.shape_circle_activity_lvl_background
                    )
                }

                lvlOfActivity = "active"
            }

            editMemberActivityLvlSoactive.setOnClickListener {
                activityLvlBackgroundSoactive.setImageResource(
                    R.drawable.shape_circle_activity_lvl_focudes_background
                )

                listOf(
                    activityLvlBackgroundActive,
                    activityLvlBackgroundSitting
                ).forEach {
                    it.setImageResource(
                        R.drawable.shape_circle_activity_lvl_background
                    )
                }

                lvlOfActivity = "soActive"
            }

            editTextEditedAge.setOnClickListener {
                var year: Int = 0
                var month: Int = 0
                var day: Int = 0

                if ("." in birthDay) {
                    year = birthDay.split(".")[2].toInt()
                    month = birthDay.split(".")[1].toInt()
                    day = birthDay.split(".")[0].toInt()
                } else if ("-" in birthDay) {
                    year = birthDay.split("-")[0].toInt()
                    month = birthDay.split("-")[1].toInt()
                    day = birthDay.split("-")[2].toInt()
                }

                val datePickerDialog = DatePickerDialog(
                    requireContext(),
                    {
                        _, yeaR, monthOfYear, dayOfMonth ->
                        val dat = ("$yeaR-${monthOfYear + 1}-$dayOfMonth")
                        birthDay = dat
                        editTextEditedAge.setText(viewModel.calculateAge(dat).toString())
                    },
                    year,
                    month - 1,
                    day
                )

                datePickerDialog.show()
            }

            editTextEditedHeight.setOnClickListener {

                viewModel.showNumberPickerHeightDialog(
                    editTextEditedHeight,
                    layoutInflater.inflate(R.layout.dialog_number_picker_height, null),
                    requireContext()
                )
            }

            editTextEditedWeight.setOnClickListener {
                viewModel.showNumberPickerWeightDialog(
                    binding.editTextEditedWeight,
                    layoutInflater.inflate(R.layout.dialog_number_picker_weight, null),
                    requireContext()
                )
            }

            editTextEditedHeight.addTextChangedListener(object: TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun afterTextChanged(p0: Editable?) {
                    if ((editTextEditedWeight.text.isNotEmpty()) &&
                        (editTextEditedWeight.text.toString() != "") &&
                            p0.toString() != "") {
                        viewModel.calculateImt(
                            editTextEditedWeight.text.toString().toInt(),
                            p0.toString().toInt()
                        )
                    }
                }

            })

            editTextEditedWeight.addTextChangedListener(object: TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun afterTextChanged(p0: Editable?) {
                    if ((editTextEditedHeight.text.isNotEmpty()) &&
                        (editTextEditedHeight.text.toString() != "") &&
                        p0.toString() != "") {
                        viewModel.calculateImt(
                            p0.toString().toInt(),
                            editTextEditedHeight.text.toString().toInt()
                        )
                    }
                }

            })

            val navController = findNavController()

            buttonEditGroupConfirm.setOnClickListener {

                if (editTextEditMemberName.text.isEmpty() ||
                        editTextEditMemberName.text.toString() == "") {
                    editTextEditMemberName.error = "Введите имя"
                } else if (editTextEditedAge.text.isEmpty() ||
                        editTextEditedAge.text.toString() == "") {
                    editTextEditedAge.error = "Введите дату рождения"
                } else if (editTextEditedHeight.text.isEmpty() ||
                        editTextEditedHeight.text.toString() == "") {
                    editTextEditedHeight.error = "Введите рост"
                } else if (editTextEditedWeight.text.isEmpty() ||
                    editTextEditedWeight.text.toString() == "") {
                    editTextEditedWeight.error = "Введите вес"
                } else {

                    if ((gender == "") || (gender == null)) {
                        gender = Constants.MALE
                    }

                    when (mode) {
                        Constants.EDIT_MODE -> {

                            updatingMember.name = editTextEditMemberName.text.toString()
                            updatingMember.gender = gender
                            updatingMember.height = editTextEditedHeight.text.toString().toInt()
                            updatingMember.weight = editTextEditedWeight.text.toString().toInt()
                            updatingMember.birthday = birthDay
                            updatingMember.age = editTextEditedAge.text.toString().toInt()

                            Log.d("birthday", updatingMember.birthday)

                            viewModel.updateMember(
                                familyId,
                                memberId,
                                updatingMember
                            )
                        }
                        Constants.CREATE_MODE -> {
                            viewModel.createMember(
                                familyId,
                                MemberDomain(
                                    name = editTextEditMemberName.text.toString(),
                                    gender = gender,
                                    height = editTextEditedHeight.text.toString().toInt(),
                                    weight = editTextEditedWeight.text.toString().toInt(),
                                    birthday = birthDay,
                                    age = editTextEditedAge.text.toString().toInt()
                                )
                            )
                        }
                        Constants.NESTED_CREATE_MODE -> {
//                        val navController = findNavController()
                            navController.previousBackStackEntry?.savedStateHandle?.set(
                                "newMember",
                                MemberDomain(
                                    name = editTextEditMemberName.text.toString(),
                                    gender = gender,
                                    height = editTextEditedHeight.text.toString().toInt(),
                                    weight = editTextEditedWeight.text.toString().toInt(),
                                    birthday = birthDay,
                                    age = editTextEditedAge.text.toString().toInt()
                                )
                            )
                        }
                        Constants.CREATED_NESTED_CREATE_MODE -> {
//                        val navController = findNavController()
                            navController.previousBackStackEntry?.savedStateHandle?.set(
                                "newMember",
                                MemberDomain(
                                    name = editTextEditMemberName.text.toString(),
                                    gender = gender,
                                    height = editTextEditedHeight.text.toString().toInt(),
                                    weight = editTextEditedWeight.text.toString().toInt(),
                                    birthday = birthDay,
                                    age = editTextEditedAge.text.toString().toInt()
                                )
                            )
                        }
                    }

                    navController.popBackStack()
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
                view.findViewById<TextView>(R.id.tv_delete_dialog_message)
                    .text = getString(R.string.dialog_delete_member)

                confirmButton.setOnClickListener {

                    when (mode) {
                        Constants.EDIT_MODE -> {
                            viewModel.deleteMember(
                                familyId,
                                memberId
                            )
                        }
                        Constants.CREATED_NESTED_CREATE_MODE -> {
//                        val navController = findNavController()
                            navController.previousBackStackEntry?.savedStateHandle?.set("deleteMember", "delete")
                        }
                    }
                    b.dismiss()
                    navController.popBackStack()

                }

                cancelButton.setOnClickListener {
//                    Log.d("CANCEL", "WORKS")
                    b.dismiss()
                }

                b.show()
            }

        }

    }

    override fun onStart() {
        super.onStart()

        if (mode == Constants.CREATED_NESTED_CREATE_MODE) {

            findNavController().previousBackStackEntry?.savedStateHandle?.set(
                "newMember",
                MemberDomain(
                    name = _name,
                    gender = _gender,
                    height = _height,
                    weight = _weight,
                    birthday = _birthday,
                    age = _age
                )
            )

            findNavController().previousBackStackEntry?.savedStateHandle?.set(
                "deleteMember",
                ""
            )
        }

    }

    override fun onResume() {
        super.onResume()
        if (mode == Constants.EDIT_MODE) {
            viewModel.getFamilyMember(
                familyId,
                memberId
            )
        }

    }

    companion object {
        const val FAMILY_ID = "family_id"
        const val MEMBER_ID = "member_id"

    }

}