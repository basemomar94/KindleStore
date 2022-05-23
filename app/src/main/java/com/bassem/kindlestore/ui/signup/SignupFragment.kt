package com.bassem.kindlestore.ui.signup

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bassem.kindlestore.R
import com.bassem.kindlestore.databinding.FragmentSignupBinding
import com.facebook.CallbackManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth

class SignupFragment : Fragment(R.layout.fragment_signup) {
    var binding: FragmentSignupBinding? = null
    var callbackManager: CallbackManager? = null
    var auth: FirebaseAuth? = null
    var viewModel: SignupViewModel? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        callbackManager = CallbackManager.Factory.create()
        auth = FirebaseAuth.getInstance()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignupBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[SignupViewModel::class.java]
        visibilityBottomBar(false)
        continueWithFacebook()
        checkSuccessLogin()
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Pass the activity result back to the Facebook SDK
        callbackManager?.onActivityResult(requestCode, resultCode, data)
    }

    override fun onDetach() {
        super.onDetach()
        visibilityBottomBar(true)
    }

    private fun continueWithFacebook() {
        viewModel?.continueWithFacebook(
            binding?.buttonFacebookLogin!!,
            requireContext(),
            callbackManager!!
        )
    }

    private fun checkSuccessLogin() {
        viewModel?.successLogin?.observe(viewLifecycleOwner) {
            if (it) {
                Log.d("Success", it.toString())
                findNavController().navigate(R.id.action_signupFragment_to_Home)
            }

        }
    }

    private fun visibilityBottomBar(isvisible: Boolean) {
        requireActivity().findViewById<BottomNavigationView>(R.id.bottomAppBar).apply {
            visibility = if (isvisible) {
                View.VISIBLE

            } else {
                View.GONE

            }

        }
    }
}