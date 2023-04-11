package com.example.raczakupsecond.screens.profile.editgroup.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.models.families.NewMemberDomain
import com.example.domain.models.families.NewMemberUpdateDomain
import com.example.domain.utils.Constants
import com.example.raczakupsecond.R
import com.example.raczakupsecond.databinding.ItemFamilyMemberBinding
import com.example.raczakupsecond.utils.Utils

class EditGroupFamilyMembersAdapter(
    private val membersList: List<NewMemberDomain>
) : RecyclerView.Adapter<EditGroupFamilyMembersAdapter.FamilyMembersHolder>() {

    var onItemClick : ((NewMemberDomain) -> Unit)? = null
    var itemPosition : ((Int) -> Unit)? = null

    inner class FamilyMembersHolder(item: View) : RecyclerView.ViewHolder(item) {
        private val binding = ItemFamilyMemberBinding.bind(item)
        fun bind(member: NewMemberDomain, position: Int) = with(binding) {
            if (Utils().calculateAge(member.birthday) < 16) {
                when(member.gender) {
                    Constants.MALE -> ivFamilyMemberIcon.setImageResource(R.drawable.ic_profile_boy)
                    Constants.FEMALE -> ivFamilyMemberIcon.setImageResource(R.drawable.ic_profile_girl)
                }
            } else if (Utils().calculateAge(member.birthday) >= 60) {
                when(member.gender) {
                    Constants.MALE -> ivFamilyMemberIcon.setImageResource(R.drawable.ic_profile_grandfather)
                    Constants.FEMALE -> ivFamilyMemberIcon.setImageResource(R.drawable.ic_profile_grandmother)
                }
            } else {
                when(member.gender) {
                    Constants.MALE -> ivFamilyMemberIcon.setImageResource(R.drawable.ic_profile_man)
                    Constants.FEMALE -> ivFamilyMemberIcon.setImageResource(R.drawable.ic_profile_woman)
                }
            }
            tvFamilyMemberName.text = member.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FamilyMembersHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(
                R.layout.item_family_member,
                parent,
                false
            )
        return FamilyMembersHolder(view)
    }

    override fun getItemCount(): Int {
        return membersList.size
    }

    override fun onBindViewHolder(holder: FamilyMembersHolder, position: Int) {
        holder.bind(membersList[position], position)

        val member = membersList[position]
        holder.itemView.setOnClickListener {
            onItemClick?.invoke(member)
            itemPosition?.invoke(position)
        }

    }

}