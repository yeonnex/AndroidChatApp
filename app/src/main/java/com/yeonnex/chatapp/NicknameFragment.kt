package com.yeonnex.chatapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.yeonnex.chatapp.databinding.FragmentNicknameBinding
import com.yeonnex.chatapp.ui.main.MainViewModel


class NicknameFragment : Fragment() {
    private var _binding: FragmentNicknameBinding? = null
    private var binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_nickname, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        binding.btnOK.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.nickname)
        }

    }

    companion object {

        fun newInstance(param1: String, param2: String) =
            NicknameFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}