package com.example.raczakupsecond.screens.packs.checkpack

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.domain.models.packs.HealthySetParamsDomain
import com.example.raczakupsecond.R
import com.example.raczakupsecond.databinding.FragmentCheckPackBinding
import com.example.raczakupsecond.screens.packs.checkpack.adapters.CheckPackFamilyMembersAdapter

class CheckPackFragment : Fragment(R.layout.fragment_check_pack) {
    lateinit var binding : FragmentCheckPackBinding
    private val viewModel : CheckPackFragmentVM by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCheckPackBinding.inflate(inflater)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        viewModel.getFamily(requireArguments().getInt("familyId").toString())
//        viewModel.changeDaysLiveData(requireArguments().getInt("days"))

        viewModel.apply {
            getFamily(requireArguments().getInt("familyId").toString())
            changeDaysLiveData(requireArguments().getInt("days"))
            changeBudgetLiveData(requireArguments().getInt("budget"))
            //TODO(shop, address)
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    override fun onResume() {
        super.onResume()

        viewModel.getFamilyNameLiveData().observe(viewLifecycleOwner) {

            binding.tvCheckPackGroupName.text = it.toString()
            Log.d("NAME_FAMILY", it.toString())

        }

        viewModel.getMembersListLiveData().observe(viewLifecycleOwner) {

            val familyMembersAdapter = CheckPackFamilyMembersAdapter(it)

            binding.apply {

                rcViewCheckPackMembers.layoutManager = GridLayoutManager(requireContext(), 4)
                rcViewCheckPackMembers.isNestedScrollingEnabled = false

                rcViewCheckPackMembers.adapter = familyMembersAdapter
            }

        }

        viewModel.getDaysLiveData().observe(viewLifecycleOwner) {

            binding.tvCheckPackDays.text = it.toString()
        }

        viewModel.getBudgetLiveData().observe(viewLifecycleOwner) {

            when (it) {
                0 -> {
                    binding.apply {
                        tvCheckPackBudgetRubs.text = getString(R.string.eco_rub)
                        tvCheckPackBudgetTitle.text = getString(R.string.budget_eco)
                    }
                }
                1 -> {
                    binding.apply {
                        tvCheckPackBudgetRubs.text = getString(R.string.standard_rub)
                        tvCheckPackBudgetTitle.text = getString(R.string.budget_standard)
                    }
                }
                2 -> {
                    binding.apply {
                        tvCheckPackBudgetRubs.text = getString(R.string.premium_rub)
                        tvCheckPackBudgetTitle.text = getString(R.string.budget_premium)
                    }
                }
            }
        }

    }

}