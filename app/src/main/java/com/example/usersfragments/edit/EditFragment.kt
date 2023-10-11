package com.example.usersfragments.edit

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import com.example.usersfragments.R
import com.example.usersfragments.data.User
import com.example.usersfragments.data.UserGenerator
import com.example.usersfragments.databinding.FragmentEditBinding
import com.example.usersfragments.databinding.FragmentUserDatailBinding
import com.example.usersfragments.detail.ARG_SAVE_LAST_NAME
import com.example.usersfragments.detail.ARG_SAVE_NAME
import com.example.usersfragments.detail.ARG_SAVE_NUMBER
import com.example.usersfragments.detail.UserDetailFragment
import com.example.usersfragments.home.UsersFragment
import com.example.usersfragments.home.UsersRecyclerViewAdapter


class EditFragment : Fragment(R.layout.fragment_edit) {

    private var _binding: FragmentEditBinding? = null
    private val binding
        get() = _binding!!

    private var user: User? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEditBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            val userId = it.getInt(ARG_USER_ID)
            user = UserGenerator.getUser(userId)
        }

        binding.btnSave.setOnClickListener {
            val newName = binding.etFirstName.text
            val newLastName = binding.etLastName.text
            val newPhone = binding.etPhoneNumber.text

            setFragmentResult(ARG_SAVE_NAME, bundleOf(ARG_SAVE_NAME to newName))
            setFragmentResult(ARG_SAVE_LAST_NAME, bundleOf(ARG_SAVE_LAST_NAME to newLastName))
            setFragmentResult(ARG_SAVE_NUMBER, bundleOf(ARG_SAVE_NUMBER to newPhone))

            parentFragmentManager.beginTransaction().run{
               parentFragmentManager.popBackStack(UserDetailFragment.TAG, 0)
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = UsersFragment()

        const val TAG = "UserDetailFragment"
        const val ARG_USER_ID = "userId"
    }
}