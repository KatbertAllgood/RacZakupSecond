package com.example.raczakupsecond.screens.shop.categories

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.data.utils.ApplicationPreferences
import com.example.domain.models.auth.CurrentUserResponseDomain
import com.example.domain.models.auth.LogoutResponseDomain
import com.example.domain.models.geo.RequestCoordinatesDomain
import com.example.domain.models.geo.RequestQueryDomain
import com.example.domain.models.geo.ResponseGeoCoordinatesDomain
import com.example.domain.models.geo.ResponseGeoDomain
import com.example.domain.models.packs.HealthySetParamsRequestDomain
import com.example.domain.models.packs.HealthySetParamsResponseDomain
import com.example.domain.usecase.currentuser.CurrentUserUseCase
import com.example.domain.usecase.geo.ResolveCoordinatesUseCase
import com.example.domain.usecase.geo.ResolveQueryUseCase
import com.example.domain.usecase.logout.LogoutUseCase
import com.example.domain.usecase.packs.CreateHealthySetParamsUseCase
import com.example.domain.usecase.sharedpreferences.GetStringPreferenceUseCase
import com.example.raczakupsecond.R
import com.example.raczakupsecond.app.App
import com.example.raczakupsecond.databinding.FragmentCategoriesShopBinding
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class CategoriesShopFragment : Fragment(R.layout.fragment_categories_shop) {
    private lateinit var binding : FragmentCategoriesShopBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCategoriesShopBinding.inflate(layoutInflater)
        binding.toolbarCategoryFragment.setTitle("Категории")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.constLayoutCategoryMilk.setOnClickListener {

            findNavController().navigate(R.id.action_categoriesShopFragment_to_productsListFragment)

        }

    }

}