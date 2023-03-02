//package com.example.raczakupsecond.screens.profile.editprofile.adapter
//
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.recyclerview.widget.RecyclerView
//import com.example.data.models.families.MemberData
//import com.example.domain.utils.Constants
//import com.example.raczakupsecond.R
//import com.example.raczakupsecond.databinding.ProfileFamilyMemberItemBinding
//
////(item: View) : RecyclerView.ViewHolder(item)
//
//class FamilyMembersAdapter(
//    private val membersList: List<MemberData>
//) : RecyclerView.Adapter<FamilyMembersAdapter.FamilyMembersHolder>() {
//
//    class FamilyMembersHolder(item: View) : RecyclerView.ViewHolder(item) {
//        private val binding = ProfileFamilyMemberItemBinding.bind(item)
//        fun bind(member: MemberData) = with(binding) {
//            when(member.gender) {
//                Constants.MALE -> ivFamilyMemberIcon.setImageResource(R.drawable.ic_profile_man)
//                Constants.FEMALE -> ivFamilyMemberIcon.setImageResource(R.drawable.ic_profile_woman)
//            }
//            tvFamilyMemberName.text = member.name
//
//            //HANDLER
//
//        }
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FamilyMembersHolder {
//        val view = LayoutInflater.from(parent.context)
//            .inflate(
//                R.layout.profile_family_member_item,
//                parent,
//                false
//            )
//        return FamilyMembersHolder(view)
//    }
//
//    override fun getItemCount(): Int {
//        return membersList.size
//    }
//
//    override fun onBindViewHolder(holder: FamilyMembersHolder, position: Int) {
//        holder.bind(membersList[position])
//    }
//
//}