package com.yeonnex.chatapp.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yeonnex.chatapp.AsyncDownThread
import com.yeonnex.chatapp.R
import com.yeonnex.chatapp.databinding.MainFragmentBinding

class MainFragment : Fragment() {
    private var _binding: MainFragment? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainFragmentBinding.inflate(layoutInflater, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        binding.btnSend.setOnClickListener {
            val thread = AsyncDownThread("http://hello", getChatMsgHandler)
            thread.start()
            binding.message.text = "hello"
        }
    }

    val getChatMsgHandler = Handler {
        val json = it.obj as String
        binding.message.text = json
        true
    }

}