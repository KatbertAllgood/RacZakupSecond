package com.example.raczakupsecond.screens.profile.profilepage.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.models.families.FamilyDomain
import com.example.domain.utils.Constants
import com.example.raczakupsecond.R
import com.example.raczakupsecond.databinding.ProfileFamilyItemBinding
import com.example.raczakupsecond.utils.Utils
import java.util.*

class ProfileFamilyGroupAdapter(
    private val familiesList: List<FamilyDomain>
) : RecyclerView.Adapter<ProfileFamilyGroupAdapter.FamilyGroupHolder>() {

    var onItemClick : ((FamilyDomain) -> Unit)? = null

    inner class FamilyGroupHolder(item: View) : RecyclerView.ViewHolder(item) {
        private val binding = ProfileFamilyItemBinding.bind(item)
        @SuppressLint("SetTextI18n")
        fun bind(family: FamilyDomain, position: Int) = with(binding) {

            tvFamilyItemName.text = family.name
            tvFamilyItemGroupNum.text = "Группа ${position + 1}."

            when(familiesList[position].members.size) {
                0 -> { }
                1 -> {
                    linearItemHolder1.visibility = View.VISIBLE

                    ivFamilyMember1
                        .setImageResource(
                        genderCheckAndAge(
                            familiesList[position].members[0].gender,
                            familiesList[position].members[0].birthday
                        )
                    )

                    tvFamilyGridMemberName1.text = familiesList[position].members[0].name
                }
                2 -> {
                    listOf(
                        linearItemHolder1,
                        linearItemHolder2
                    ).forEachIndexed { _, item ->
                        item.visibility = View.VISIBLE
                    }

                    listOf(
                        ivFamilyMember1,
                        ivFamilyMember2
                    ).forEachIndexed { index, item ->
                        item.setImageResource(
                            genderCheckAndAge(
                                familiesList[position].members[index].gender,
                                familiesList[position].members[index].birthday
                            )
                        )
                    }

                    listOf(
                        tvFamilyGridMemberName1,
                        tvFamilyGridMemberName2
                    ).forEachIndexed{ index, item ->
                            item.text = familiesList[position].members[index].name
                    }
                }
                3 -> {
                    listOf(
                        linearItemHolder1,
                        linearItemHolder2,
                        linearItemHolder3
                    ).forEachIndexed { _, item ->
                        item.visibility = View.VISIBLE
                    }

                    listOf(
                        ivFamilyMember1,
                        ivFamilyMember2,
                        ivFamilyMember3
                    ).forEachIndexed { index, item ->
                        item.setImageResource(
                            genderCheckAndAge(
                                familiesList[position].members[index].gender,
                                familiesList[position].members[index].birthday
                            )
                        )
                    }

                    listOf(
                        tvFamilyGridMemberName1,
                        tvFamilyGridMemberName2,
                        tvFamilyGridMemberName3
                    ).forEachIndexed{ index, item ->
                        item.text = familiesList[position].members[index].name
                    }
                }
                4 -> {
                    listOf(
                        linearItemHolder1,
                        linearItemHolder2,
                        linearItemHolder3,
                        linearItemHolder4
                    ).forEachIndexed { _, item ->
                        item.visibility = View.VISIBLE
                    }

                    listOf(
                        ivFamilyMember1,
                        ivFamilyMember2,
                        ivFamilyMember3,
                        ivFamilyMember4
                    ).forEachIndexed { index, item ->
                        item.setImageResource(
                            genderCheckAndAge(
                                familiesList[position].members[index].gender,
                                familiesList[position].members[index].birthday
                            )
                        )
                    }

                    listOf(
                        tvFamilyGridMemberName1,
                        tvFamilyGridMemberName2,
                        tvFamilyGridMemberName3,
                        tvFamilyGridMemberName4
                    ).forEachIndexed{ index, item ->
                        item.text = familiesList[position].members[index].name
                    }
                }
                else -> {
                    listOf(
                        linearItemHolder1,
                        linearItemHolder2,
                        linearItemHolder3,
                        constraintItemHolderOverCount
                    ).forEachIndexed { _, item ->
                        item.visibility = View.VISIBLE
                    }

                    listOf(
                        ivFamilyMember1,
                        ivFamilyMember2,
                        ivFamilyMember3
                    ).forEachIndexed { index, item ->
                        item.setImageResource(
                            genderCheckAndAge(
                                familiesList[position].members[index].gender,
                                familiesList[position].members[index].birthday
                            )
                        )
                    }

                    listOf(
                        tvFamilyGridMemberName1,
                        tvFamilyGridMemberName2,
                        tvFamilyGridMemberName3
                    ).forEachIndexed{ index, item ->
                        item.text = familiesList[position].members[index].name
                    }

                    tvFamilyGridMemberOverCount.text =
                        (familiesList[position].members.size - 3).toString()
                }
            }

        }
    }

    private fun genderCheckAndAge(
        gender: String,
        birthDay: String
    ): Int {
        return if (Utils().calculateAge(birthDay) < 16) {
            when (gender) {
                Constants.MALE -> R.drawable.ic_profile_boy
                Constants.FEMALE -> R.drawable.ic_profile_girl
                else -> R.drawable.ic_profile_boy
            }
        } else if (Utils().calculateAge(birthDay) >= 60) {
            when (gender) {
                Constants.MALE -> R.drawable.ic_profile_grandfather
                Constants.FEMALE -> R.drawable.ic_profile_grandmother
                else -> R.drawable.ic_profile_grandfather
            }
        } else {
            when (gender) {
                Constants.MALE -> R.drawable.ic_profile_man
                Constants.FEMALE -> R.drawable.ic_profile_woman
                else -> R.drawable.ic_profile_man
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FamilyGroupHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(
                R.layout.profile_family_item,
                parent,
                false
            )
        return FamilyGroupHolder(view)
    }

    override fun getItemCount(): Int {
        return familiesList.size
    }

    override fun onBindViewHolder(holder: FamilyGroupHolder, position: Int) {
        holder.bind(familiesList[position], position)

        val family = familiesList[position]
        holder.itemView.setOnClickListener {
            onItemClick?.invoke(family)
        }
    }

}