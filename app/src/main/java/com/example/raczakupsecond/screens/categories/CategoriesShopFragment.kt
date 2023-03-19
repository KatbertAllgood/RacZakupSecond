package com.example.raczakupsecond.screens.categories

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.data.utils.ApplicationPreferences
import com.example.domain.models.auth.CurrentUserResponseDomain
import com.example.domain.models.auth.LogoutResponseDomain
import com.example.domain.usecase.currentuser.CurrentUserUseCase
import com.example.domain.usecase.logout.LogoutUseCase
import com.example.domain.usecase.sharedpreferences.GetStringPreferenceUseCase
import com.example.raczakupsecond.R
import com.example.raczakupsecond.app.App
import com.example.raczakupsecond.databinding.FragmentCategoriesShopBinding
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class CategoriesShopFragment : Fragment(R.layout.fragment_categories_shop) {
    private lateinit var binding : FragmentCategoriesShopBinding

    private val networkRepository = App.getNetworkRepository()
    private val currentUserUseCase = CurrentUserUseCase(networkRepository)
    private val logoutUseCase = LogoutUseCase(networkRepository)

    private val sharedPreferencesRepository = App.getSharedPreferencesRepository()
    private val getStringPreferenceUseCase = GetStringPreferenceUseCase(sharedPreferencesRepository)

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

        binding.constLayoutCategoryFrozen.setOnClickListener {

            logoutUseCase
                .invoke()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : DisposableSingleObserver<LogoutResponseDomain>() {
                    override fun onSuccess(t: LogoutResponseDomain) {
                        Log.d("LOGOUT: ", t.status)
                        ApplicationPreferences.getRefresh = t.refreshToken
                        ApplicationPreferences.getAccess = ""
                    }

                    override fun onError(e: Throwable) {
                        Log.d("LOGOUT: ", e.message.toString())
                    }

                })

        }

        binding.constLayoutCategoryMeat.setOnClickListener {

            currentUserUseCase
                .invoke()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : DisposableSingleObserver<CurrentUserResponseDomain>() {
                    override fun onSuccess(t: CurrentUserResponseDomain) {
                        Log.d("Current User: ", t.user.userId)
                    }

                    override fun onError(e: Throwable) {
                        Log.d("Current User: ", e.message.toString())
                    }

                })

        }

    }

}