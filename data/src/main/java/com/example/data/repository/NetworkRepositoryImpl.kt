package com.example.data.repository

import com.example.data.mapper.*
import com.example.data.network.api.NetworkService
import com.example.domain.models.ServerResponseDomain
import com.example.domain.models.auth.*
import com.example.domain.models.families.*
import com.example.domain.repository.NetworkRepository
import io.reactivex.Single

class NetworkRepositoryImpl : NetworkRepository{

    override fun checkPhone(phone: PhoneDomain): Single<PhoneResponseDomain> {
        val phoneData = PhoneToData(phone).toData()
        return NetworkService.retrofitService.checkPhone(phoneData).map {
            return@map PhoneResponseToDomain(it).toDomain()
        }
    }

    override fun checkCode(code: CodeDomain): Single<CodeResponseDomain> {
        val codeData = CodeToData(code).toData()
        return NetworkService.retrofitService.checkCode(codeData).map {
            return@map CodeResponseToDomain(it).toDomain()
        }
    }

    override fun refresh(): Single<RefreshResponseDomain> {
        return NetworkService.retrofitService.refresh().map {
            return@map RefreshResponseToDomain(it).toDomain()
        }
    }

    override fun currentUser(): Single<CurrentUserResponseDomain> {
        return NetworkService.retrofitService.currentUser().map {
            return@map CurrentUserResponseToDomain(it).toDomain()
        }
    }

    override fun logout(): Single<LogoutResponseDomain> {
        return NetworkService.retrofitService.logout().map {
            return@map LogoutResponseToDomain(it).toDomain()
        }
    }

    override fun getFamilies(): Single<AllFamiliesDomain> {
        return NetworkService.retrofitService.getFamilies().map {
            return@map AllFamiliesToDomain(it).toDomain()
        }
    }

    override fun getFamily(id: String): Single<NewFamilyDomain> {
        return NetworkService.retrofitService.getFamily(id).map {
            return@map NewFamilyToDomain(it).toDomain()
        }
    }

    override fun getFamilyMembers(familyId: String): Single<List<NewMemberDomain>> {
        return NetworkService.retrofitService.getFamilyMembers(familyId).map {
            val mapResult: MutableList<NewMemberDomain> = mutableListOf()
            for (i in it) {
                mapResult.add(NewMemberToDomain(i).toDomain())
            }
            return@map mapResult
        }
    }

    override fun updateFamily(
        familyId: String,
        updatedFamily: NewFamilyUpdateDomain
    ): Single<NewFamilyDomain> {
        return NetworkService.retrofitService.updateFamily(
            familyId,
            NewFamilyUpdateToData(updatedFamily).toData()
        ).map {
            return@map NewFamilyToDomain(it).toDomain()
        }
    }

    override fun updateMember(
        familyId: String,
        memberId: String,
        updatedMember: NewMemberUpdateDomain
    ): Single<NewMemberDomain> {
        return NetworkService.retrofitService.updateMember(
            familyId,
            memberId,
            NewMemberUpdateToData(updatedMember).toData()
        ).map {
            return@map NewMemberToDomain(it).toDomain()
        }
    }

    override fun deleteFamily(familyId: String) : Single<ServerResponseDomain> {
        return NetworkService.retrofitService.deleteFamily(familyId).map {
            return@map ServerResponseToDomain(it).toDomain()
        }
    }

    override fun deleteMember(familyId: String, memberId: String) : Single<ServerResponseDomain> {
        return NetworkService.retrofitService.deleteMember(
            familyId,
            memberId
        ).map {
            return@map ServerResponseToDomain(it).toDomain()
        }
    }

    override fun createFamily(family: NewFamilyUpdateDomain): Single<NewFamilyDomain> {
        return NetworkService.retrofitService.createFamily(
            NewFamilyUpdateToData(family).toData()
        ).map {
            return@map NewFamilyToDomain(it).toDomain()
        }
    }

    override fun createMember(
        familyId: String,
        newFamilyMember: NewMemberUpdateDomain
    ): Single<NewMemberDomain> {
        return NetworkService.retrofitService.createMember(
            familyId,
            NewMemberUpdateToData(newFamilyMember).toData()
        ).map {
            return@map NewMemberToDomain(it).toDomain()
        }
    }

}

//    override fun getFamilies(): Single<List<FamilyDomain>> {
//        return NetworkService.retrofitService.getFamilies().map {
//            val mapResult: MutableList<FamilyDomain> = mutableListOf()
//            for (i in it) {
//                mapResult.add(FamilyToDomain(i).toDomain())
//            }
//            return@map mapResult
//        }
//    }
//
//    override fun getFamily(id: String): Single<FamilyDomain> {
//        return NetworkService.retrofitService.getFamily(id).map {
//            return@map FamilyToDomain(it).toDomain()
//        }
//    }
//
//    override fun getFamilyMember(familyId: String, memberId: String): Single<MemberDomain> {
//        return NetworkService.retrofitService.getFamilyMember(familyId, memberId).map {
//            return@map MemberToDomain(it).toDomain()
//        }
//    }
//
//    override fun updateFamily(
//        familyId: String,
//        updatedFamily: FamilyDomain
//    ): Single<ServerResponseDomain> {
//        return NetworkService.retrofitService.updateFamily(
//            familyId,
//            FamilyToData(updatedFamily).toData()
//        ).map {
//            return@map ServerResponseToDomain(it).toDomain()
//        }
//    }
//
//    override fun updateMember(
//        familyId: String,
//        memberId: String,
//        updatedMember: MemberDomain
//    ): Single<ServerResponseDomain> {
//        return NetworkService.retrofitService.updateMember(
//            familyId,
//            memberId,
//            MemberToData(updatedMember).toData()
//        ).map {
//            return@map ServerResponseToDomain(it).toDomain()
//        }
//    }
//
//    override fun deleteFamily(familyId: String): Single<ServerResponseDomain> {
//        return NetworkService.retrofitService.deleteFamily(familyId).map {
//            return@map ServerResponseToDomain(it).toDomain()
//        }
//    }
//
//    override fun deleteMember(familyId: String, memberId: String): Single<ServerResponseDomain> {
//        return NetworkService.retrofitService.deleteMember(
//            familyId,
//            memberId
//        ).map {
//            return@map ServerResponseToDomain(it).toDomain()
//        }
//    }
//
//    override fun createFamily(family: FamilyDomain): Single<FamilyDomain> {
//        return NetworkService.retrofitService.createFamily(
//            FamilyToData(family).toData()
//        ).map {
//            return@map FamilyToDomain(it).toDomain()
//        }
//    }
//
//    override fun createMember(
//        familyId: String,
//        newFamilyMember: MemberDomain
//    ): Single<MemberDomain> {
//        return NetworkService.retrofitService.createMember(
//            familyId,
//            MemberToData(newFamilyMember).toData()
//        ).map {
//            MemberToDomain(it).toDomain()
//        }
//    }