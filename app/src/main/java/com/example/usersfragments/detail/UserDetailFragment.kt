package com.example.usersfragments.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.usersfragments.R
import com.example.usersfragments.data.User
import com.example.usersfragments.data.UserGenerator
import com.example.usersfragments.databinding.FragmentUserDatailBinding
import com.example.usersfragments.home.UsersFragment
import com.example.usersfragments.home.UsersRecyclerViewAdapter


class UserDetailFragment : Fragment(R.layout.fragment_user_datail) {

    private var _binding: FragmentUserDatailBinding? = null
    private val binding
        get() = _binding!!

    private var user: User? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserDatailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            val userId = it.getInt(ARG_USER_ID)
            user = UserGenerator.getUser(userId)
        }
        binding.firstName.text = user?.firstName
        binding.lastName.text = user?.lastName
        binding.phone.text = user?.number

        binding.buttonEdit.setOnClickListener {
            parentFragmentManager.beginTransaction().run{

            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(userId: Int) = UsersFragment().apply {
            arguments = Bundle().apply {
                putInt(ARG_USER_ID, userId)
            }
        }
        const val TAG = "UserDetailFragment"
        const val ARG_USER_ID = "userId"
    }
}