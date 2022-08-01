package com.deanu.ktorandroidchat.presentation.username

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.deanu.ktorandroidchat.R
import com.deanu.ktorandroidchat.databinding.FragmentUsernameBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class UsernameFragment : Fragment() {

    private val viewModel by viewModels<UsernameViewModel>()
    private var _binding: FragmentUsernameBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUsernameBinding.inflate(layoutInflater)

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.usernameError.observe(viewLifecycleOwner) { isError ->
            if (isError) binding.tilUsername.error = getString(R.string.username_should_not_empty)
            else binding.tilUsername.error = null
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.onJoinChat.collectLatest { username ->
                val toChatFragment = UsernameFragmentDirections
                    .actionUsernameFragmentToChatFragment(username)
                binding.root.findNavController().navigate(toChatFragment)
            }
        }

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}