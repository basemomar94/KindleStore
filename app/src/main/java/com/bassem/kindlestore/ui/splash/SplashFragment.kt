package com.bassem.kindlestore.ui.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bassem.kindlestore.R

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
        checkLoginStatus()

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
}