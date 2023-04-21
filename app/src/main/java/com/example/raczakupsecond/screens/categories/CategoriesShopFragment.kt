package com.example.raczakupsecond.screens.categories

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

        val networkRepository = App.getNetworkRepository()
        val createHealthySetParamsUseCase = CreateHealthySetParamsUseCase(networkRepository)
        val resolveCoordinatesUseCase = ResolveCoordinatesUseCase(networkRepository)
        val resolveQueryUseCase = ResolveQueryUseCase(networkRepository)

        binding.constLayoutCategoryFrozen.setOnClickListener {

            createHealthySetParamsUseCase.invoke(
                HealthySetParamsRequestDomain(0, 0, "тест", 0)
            )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : DisposableSingleObserver<HealthySetParamsResponseDomain>() {
                    override fun onSuccess(t: HealthySetParamsResponseDomain) {
                        Log.d("HEALTHY_SET", t.toString())
                    }

                    override fun onError(e: Throwable) {
                        Log.d("HEALTHY_SET_ERROR", e.message.toString())
                    }

                })

            resolveCoordinatesUseCase.invoke(
                RequestCoordinatesDomain(55.8646, 37.3954)
            )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : DisposableSingleObserver<List<ResponseGeoCoordinatesDomain>>() {
                    override fun onSuccess(t: List<ResponseGeoCoordinatesDomain>) {
                        Log.d("COORDINATES", t.toString())
                    }

                    override fun onError(e: Throwable) {
                        Log.d("ERROR_COORDINATES", e.message.toString())
                    }

                })

            resolveQueryUseCase.invoke(
                RequestQueryDomain("летчика грицевца 8")
            )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : DisposableSingleObserver<ResponseGeoDomain>() {
                    override fun onSuccess(t: ResponseGeoDomain) {
                        Log.d("QUERY", t.toString())
                    }

                    override fun onError(e: Throwable) {
                        Log.d("ERROR_COORDINATES", e.message.toString())
                    }

                })
        }

        binding.constLayoutCategoryMeat.setOnClickListener {

            resolveCoordinatesUseCase.invoke(
                RequestCoordinatesDomain(55.8646, 37.3954)
            )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : DisposableSingleObserver<List<ResponseGeoCoordinatesDomain>>() {
                    override fun onSuccess(t: List<ResponseGeoCoordinatesDomain>) {
                        Log.d("COORDINATES_TEST", t[0].data.toString())
                    }

                    override fun onError(e: Throwable) {
                        Log.d("ERROR_COORDINATES", e.message.toString())
                    }

                })

        }

    }

}