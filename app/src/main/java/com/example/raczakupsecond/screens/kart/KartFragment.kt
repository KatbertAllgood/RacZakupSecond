package com.example.raczakupsecond.screens.kart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Box
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.tooling.preview.Preview
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.raczakupsecond.R
import com.example.raczakupsecond.databinding.FragmentKartBinding

class KartFragment : Fragment(R.layout.fragment_kart) {
    private lateinit var binding : FragmentKartBinding
    private val viewModel : KartFragmentVM by viewModels()

    private val TAG = KartFragment::class.simpleName

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
        setContent {
            Kart()
        }
    }

}

@Preview
@Composable
fun Preview() {
    Kart()
}

@Composable
fun Kart() {
    Box {
        Text(text = "COMPOSE TEST")
    }
}