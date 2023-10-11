package com.example.usersfragments.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import com.example.usersfragments.R
import com.example.usersfragments.data.UserGenerator.userList
import com.example.usersfragments.databinding.FragmentUsersBinding
import com.example.usersfragments.detail.UserDetailFragment


class UsersFragment : Fragment(R.layout.fragment_users) {

    private var _binding: FragmentUsersBinding? = null
    private val binding
        get() = _binding!!
    private val adapter = UsersRecyclerViewAdapter { userId -> onClickUser(userId) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUsersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerview.adapter = adapter

        adapter.setData(userList)
    }

    private fun onClickUser(userId: Int) {
        parentFragmentManager.beginTransaction().run{
            val fragment = UserDetailFragment.newInstance(userId)
            setReorderingAllowed(true)
            replace(R.id.fragment_container_view, fragment, UserDetailFragment.TAG)
            addToBackStack(UserDetailFragment.TAG)
            commit()
            Log.d(TAG, "onClickUser: $userId")
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = UsersFragment()
        const val TAG = "UsersFragment"
    }
}