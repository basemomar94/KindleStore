package com.bassem.kindlestore.ui.splash

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bassem.kindlestore.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class SplashFragment : Fragment(R.layout.fragment_splash) {
    var viewModel: SplashViewModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[SplashViewModel::class.java]
        visibilityBottomBar(false)
       splashDelay()
    }


    private fun checkLoginStatus() {
        viewModel?.isSign()
        viewModel?.isSign?.observe(viewLifecycleOwner) {
            if (it) {
                findNavController().navigate(R.id.action_splashFragment_to_homeFragment)

            } else {
                findNavController().navigate(R.id.action_splashFragment_to_signupFragment)

            }

        }
    }

    private fun splashDelay() {
        Handler().postDelayed({
            checkLoginStatus()
        }, 3000)
    }

    fun visibilityBottomBar(isvisible: Boolean) {
        requireActivity().findViewById<BottomNavigationView>(R.id.bottomAppBar).apply {
            visibility = if (isvisible) {
                View.VISIBLE

            } else {
                View.GONE

            }

        }
    }
}