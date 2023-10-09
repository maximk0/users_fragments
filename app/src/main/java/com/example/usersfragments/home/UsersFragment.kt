package com.example.usersfragments.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.usersfragments.data.User
import com.example.usersfragments.databinding.FragmentItemListBinding


class UsersFragment : Fragment() {

    private var _binding: FragmentItemListBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentItemListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = UsersRecyclerViewAdapter()
        binding.recyclerview.adapter = adapter

        adapter.setData(userList())
    }

    private fun userList(): List<User> {
        val contacts = mutableListOf<User>()
        for (i in 1..100) {
            contacts.add(User(i, "Имя$i", "Фамилия$i", "8-800-$i$i"))
        }
        return contacts
    }

    companion object {
        @JvmStatic
        fun newInstance() = UsersFragment()
    }
}