package com.deanu.ktorandroidchat.presentation.chat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.deanu.ktorandroidchat.databinding.FragmentChatBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ChatFragment : Fragment() {
    private var _binding: FragmentChatBinding? = null
    private val binding get() = _binding!!
    private val args: ChatFragmentArgs by navArgs()
    private val viewModel by viewModels<ChatViewModel>()
    private lateinit var username: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChatBinding.inflate(layoutInflater)
        username = args.username

        val adapter = ChatAdapter()
        binding.rvChatList.adapter = adapter

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.state.collect { chatState ->
                adapter.submitList(chatState.messages)
                binding.rvChatList.smoothScrollToPosition(0)
            }
        }

        binding.btnSend.setOnClickListener {
            viewModel.onMessageChange(binding.edtChatMessage.text.toString())
            viewModel.sendMessage()
        }

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        viewModel.connectToChat(username)
    }

    override fun onStop() {
        super.onStop()
        viewModel.disconnect()
    }
}