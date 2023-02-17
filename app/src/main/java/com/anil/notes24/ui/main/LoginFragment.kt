package com.anil.notes24.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.anil.notes24.R
import com.anil.notes24.databinding.FragmentLoginBinding
import com.anil.notes24.home.MainActivity
import com.anil.notes24.network.Result
import timber.log.Timber

class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private val viewModel: LoginViewModel by viewModels()

    companion object {
        fun newInstance() = LoginFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.loggedInUser.observe(viewLifecycleOwner) { user ->
            Timber.e("user $user")
            if (user != null) {
                val intent = Intent(activity, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                activity?.finish()
            }
        }
        viewModel.user.observe(viewLifecycleOwner) { result ->
            when (result) {
                is Result.Loading -> {
                    binding.progressBar.isVisible = true
                }
                is Result.Success -> {
                    binding.progressBar.isVisible = false

                }
                is Result.Error -> {
                    binding.progressBar.isVisible = false
                }
            }
        }

        binding.btnLogin.setOnClickListener {
            if (validateLoginForm()) {
                viewModel.login(
                    binding.inputEmail.text.toString(),
                    binding.inputPassword.text.toString()
                )
            }
        }
    }

    private fun validateLoginForm(): Boolean {
        binding.apply {
            val email = inputEmail.text.toString()
            val password = inputPassword.text.toString()

            if (email.isBlank()) {
                inputEmail.error = getString(R.string.enter_email_error)
                inputEmail.requestFocus()
                return false
            } else {
                inputEmail.error = null
            }
            if (password.isBlank()) {
                inputPassword.error = getString(R.string.enter_password_error)
                inputPassword.requestFocus()
                return false
            } else {
                inputPassword.error = null
            }
        }
        return true
    }

}