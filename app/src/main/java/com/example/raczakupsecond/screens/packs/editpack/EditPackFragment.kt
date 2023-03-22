package com.example.raczakupsecond.screens.packs.editpack

import android.graphics.Typeface
import android.net.Uri
import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.raczakupsecond.R
import com.example.raczakupsecond.databinding.FragmentEditPackBinding
import com.example.raczakupsecond.screens.packs.editpack.adapters.EditPackFamilyGroupAdapter

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

        start()

        //region Получение шрифта roboto_bold

        val fontRobotoBold = ResourcesCompat.getFont(
            requireContext(),
            R.font.roboto_bold
        )

        //endregion

        //region подчеркивание кнопки "заказать для себя"

        val spannableOrderForMyself = SpannableString(getString(R.string.for_me))
        spannableOrderForMyself.setSpan(UnderlineSpan(), 0, spannableOrderForMyself.length, 0)
        binding.tvButtonOrderForMyself.text = spannableOrderForMyself

        //endregion

        //region На сколько человек закупка?

        binding.ivButtonAddNewFamily.setOnClickListener {
            routeToEditGroupFragment()
        }

        binding.tvButtonAddNewFamilyWhenEmpty.setOnClickListener {
            routeToEditGroupFragment()
        }

        binding.buttonHowMuchPeopleNext.setOnClickListener {

            binding.apply {
                viewModel.nextStep(
                    it,
                    ivPointHowMuchPeople,
                    ivPointHowMuchDays,
                    clHowMuchPeople,
                    clHowMuchDays,
                    tvNumberOne,
                    tvNumberTwo,
                    tvHowMuchPeople,
                    fontRobotoBold,
                    requireContext()
                )
            }

        }

        //endregion

        //region Количество дней

        // TODO() { ТУТ ПЕРЕДАВАТЬ days }

        binding.tvButtonEditPackDayCountOne.setOnClickListener {

            binding.buttonHowMuchDaysNext.visibility = View.VISIBLE

            it.setBackgroundResource(R.drawable.shape_rectangle_fafafa_rounded_5dp_selected)

            listOf(
                binding.tvButtonEditPackDayCountThree,
                binding.tvButtonEditPackDayCountFive,
                binding.tvButtonEditPackDayCountSeven
            ).forEach {
                it.setBackgroundResource(R.drawable.shape_rectangle_fafafa_rounded_5dp)
            }
        }

        binding.tvButtonEditPackDayCountThree.setOnClickListener {

            binding.buttonHowMuchDaysNext.visibility = View.VISIBLE

            it.setBackgroundResource(R.drawable.shape_rectangle_fafafa_rounded_5dp_selected)

            listOf(
                binding.tvButtonEditPackDayCountOne,
                binding.tvButtonEditPackDayCountFive,
                binding.tvButtonEditPackDayCountSeven
            ).forEach {
                it.setBackgroundResource(R.drawable.shape_rectangle_fafafa_rounded_5dp)
            }
        }

        binding.tvButtonEditPackDayCountFive.setOnClickListener {

            binding.buttonHowMuchDaysNext.visibility = View.VISIBLE

            it.setBackgroundResource(R.drawable.shape_rectangle_fafafa_rounded_5dp_selected)

            listOf(
                binding.tvButtonEditPackDayCountThree,
                binding.tvButtonEditPackDayCountOne,
                binding.tvButtonEditPackDayCountSeven
            ).forEach {
                it.setBackgroundResource(R.drawable.shape_rectangle_fafafa_rounded_5dp)
            }
        }

        binding.tvButtonEditPackDayCountSeven.setOnClickListener {

            binding.buttonHowMuchDaysNext.visibility = View.VISIBLE

            it.setBackgroundResource(R.drawable.shape_rectangle_fafafa_rounded_5dp_selected)

            listOf(
                binding.tvButtonEditPackDayCountThree,
                binding.tvButtonEditPackDayCountFive,
                binding.tvButtonEditPackDayCountOne
            ).forEach {
                it.setBackgroundResource(R.drawable.shape_rectangle_fafafa_rounded_5dp)
            }
        }

        binding.buttonHowMuchDaysNext.setOnClickListener {

            binding.apply {
                viewModel.nextStep(
                    it,
                    ivPointHowMuchDays,
                    ivPointBudget,
                    clHowMuchDays,
                    clBudget,
                    tvNumberTwo,
                    tvNumberThree,
                    tvHowMuchDays,
                    fontRobotoBold,
                    requireContext()
                )
            }

        }

        //endregion

        //region Бюджет закупки

        // TODO() { ТУТ ПЕРЕДАВАТЬ budget }

        binding.llBudgetEco.setOnClickListener {

            binding.buttonBudgetNext.visibility = View.VISIBLE

            it.setBackgroundResource(R.drawable.shape_rectangle_fafafa_rounded_5dp_selected)

            listOf(
                binding.llBudgetStandard,
                binding.llBudgetPremium
            ).forEach { it.setBackgroundResource(R.drawable.shape_rectangle_fafafa_rounded_5dp) }
        }

        binding.llBudgetStandard.setOnClickListener {

            binding.buttonBudgetNext.visibility = View.VISIBLE

            it.setBackgroundResource(R.drawable.shape_rectangle_fafafa_rounded_5dp_selected)

            listOf(
                binding.llBudgetEco,
                binding.llBudgetPremium
            ).forEach { it.setBackgroundResource(R.drawable.shape_rectangle_fafafa_rounded_5dp) }
        }

        binding.llBudgetPremium.setOnClickListener {

            binding.buttonBudgetNext.visibility = View.VISIBLE

            it.setBackgroundResource(R.drawable.shape_rectangle_fafafa_rounded_5dp_selected)

            listOf(
                binding.llBudgetStandard,
                binding.llBudgetEco
            ).forEach { it.setBackgroundResource(R.drawable.shape_rectangle_fafafa_rounded_5dp) }
        }

        binding.buttonBudgetNext.setOnClickListener {

            binding.apply {
                viewModel.nextStep(
                    it,
                    ivPointBudget,
                    ivPointAddress,
                    clBudget,
                    clAddress,
                    tvNumberThree,
                    tvNumberFour,
                    tvBudget,
                    fontRobotoBold,
                    requireContext()
                )
            }

        }

        //endregion

        //region Адрес доставки

        binding.buttonAddressNext.setOnClickListener {

            binding.apply {
                viewModel.nextStep(
                    it,
                    ivPointAddress,
                    ivPointShop,
                    clAddress,
                    clShop,
                    tvNumberFour,
                    tvNumberFive,
                    tvShop,
                    fontRobotoBold,
                    requireContext()
                )
            }

        }

        //endregion

    }

    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()

        //region На сколько человек закупка?

        viewModel.getFamilies()

        viewModel.getAllFamiliesLiveData().observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                binding.apply {
                    tvButtonAddNewFamilyWhenEmpty.visibility = View.GONE
                    rcViewEditPackFragmentGroups.visibility = View.VISIBLE
                }

                val familyGroupAdapter = EditPackFamilyGroupAdapter(it)

                binding.apply{
                    rcViewEditPackFragmentGroups.layoutManager = LinearLayoutManager(requireContext())
                    rcViewEditPackFragmentGroups.isNestedScrollingEnabled = false

                    familyGroupAdapter.onItemClick = {item ->
                        Log.d("SELECTED_FAMILY_ID", item.id.toString())
                        buttonHowMuchPeopleNext.visibility = View.VISIBLE
                        // TODO() { ТУТ ПЕРЕДАВАТЬ familyId }
                    }

                    rcViewEditPackFragmentGroups.adapter = familyGroupAdapter
                }

            } else {
                binding.apply {
                    tvButtonAddNewFamilyWhenEmpty.visibility = View.VISIBLE
                    rcViewEditPackFragmentGroups.visibility = View.GONE
                }
            }
        }

        //endregion

    }

    private fun start() = with(binding){
        listOf(
            buttonHowMuchPeopleNext,
            clHowMuchDays,
            buttonHowMuchDaysNext,
            clBudget,
            buttonBudgetNext,
            clAddress,
            buttonAddressNext,
            clShop,
            buttonShopNext
        ).forEach {
            it.visibility = View.GONE
        }
    }

    private fun routeToEditGroupFragment() {
        val deeplinkEditMember = Uri.parse("raczakup://internal_navigation_editGroupFragment")
        findNavController().navigate(deeplinkEditMember)
    }
}