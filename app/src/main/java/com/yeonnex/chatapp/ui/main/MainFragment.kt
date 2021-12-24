package com.yeonnex.chatapp.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.os.Handler
import android.os.Message
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yeonnex.chatapp.AsyncDownThread
import com.yeonnex.chatapp.ChatItem
import com.yeonnex.chatapp.ChatRecyclerAdapter
import com.yeonnex.chatapp.databinding.MainFragmentBinding
import org.json.JSONArray
import org.json.JSONException

class MainFragment : Fragment() {

    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    private var layoutManager : RecyclerView.LayoutManager? = null
    private var adapter : RecyclerView.Adapter<ChatRecyclerAdapter.ViewHolder>? = null
    private val items = mutableListOf<ChatItem>()
    private var nickname = "tester"
    private var lastChatID = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        val linearLayoutManager = LinearLayoutManager(context,
            LinearLayoutManager.VERTICAL, false) // reverse 레이아웃
        linearLayoutManager.stackFromEnd = true

        layoutManager = LinearLayoutManager(context)
        adapter = ChatRecyclerAdapter(context, items)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = adapter


        binding.btnSend.setOnClickListener {
            val chat = binding.etMsg.text.toString()
            val url = "http://wik.iptime.org:8080/cmsgs/$nickname/$chat"
            AsyncDownThread(url, null).start()

            binding.etMsg.setText("")
            mHandler.removeMessages(0) // 초기화
            mHandler.sendEmptyMessage(0)
        }
    }

    val getChatMsgHandler = Handler {
        val json = it.obj as String
        try {
            val ja = JSONArray(json)
            for (i in 0 until ja.length()) {
                ja.getJSONObject(i).let { jo ->
                    lastChatID = jo.getInt("id") + 1
                    val nickname = jo.getString("user_nickname")
                    val usermsg = jo.getString("user_msg")
                    if (this.nickname == nickname)
                        items.add(ChatItem("", usermsg))
                    else
                        items.add(ChatItem("$nickname:\n$usermsg"))
                }
            }
            adapter?.let{ adp ->
                adp.notifyDataSetChanged()
                binding.recyclerView.scrollToPosition(adp.itemCount - 1)
            }

        } catch (e: JSONException) { e.printStackTrace() }

        true
    }

    val mHandler: Handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            val url = "http://wik.iptime.org:8080/cmsgs/list/$lastChatID.json"
            AsyncDownThread(url, getChatMsgHandler).start()

            this.sendEmptyMessageDelayed(0, 3000)
        }
    }

    override fun onStart() {
        super.onStart()
        mHandler.sendEmptyMessage(0)
    }

    override fun onStop() {
        super.onStop()
        mHandler.removeMessages(0)
    }


}
