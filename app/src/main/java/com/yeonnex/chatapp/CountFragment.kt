package com.yeonnex.chatapp

import android.os.Bundle
import android.os.Handler
import android.os.Message
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yeonnex.chatapp.databinding.FragmentCountBinding


class CountFragment : Fragment() {
    private var _binding: FragmentCountBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = CountFragment()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCountBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
    private var count = 0

    val mHandler: Handler = object : Handler(){
        override fun handleMessage(msg: Message) {
            binding.tvCount.text = (count++).toString()
            @Handler.sendEmptyMessageDelayed(0, 1000L)
        }
    }

    override fun onStart(){
        super.onStart()
        mHandler.sendEmptyMessage(0)
    }
    override fun onStop(){
        super.onStop()
        mHandler.removeMessages(0)
    }
}