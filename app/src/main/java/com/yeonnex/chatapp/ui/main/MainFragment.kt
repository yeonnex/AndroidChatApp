package com.yeonnex.chatapp.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yeonnex.chatapp.AsyncDownThread
import com.yeonnex.chatapp.ChatRecyclerAdapter
import com.yeonnex.chatapp.R
import com.yeonnex.chatapp.databinding.MainFragmentBinding

class MainFragment : Fragment() {
    private var _binding: MainFragment? = null
    private val binding get() = _binding!!

    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter : RecyclerView.Adapter<ChatRecyclerAdapter.ViewHolder>? = null
    private val items = mutableListOf<ChatItem>()

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


        layoutManager = LinearLayoutManager(this)
        adapter = ChatRecyclerAdapter(context, items) // 프래그먼트 상태이기 때문
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = adapter


        val url = "http://wik.iptime.org:8080/cmsgs/list/0.json"
        binding.btnSend.setOnClickListener {
            val thread = AsyncDownThread(url, getChatMsgHandler)
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