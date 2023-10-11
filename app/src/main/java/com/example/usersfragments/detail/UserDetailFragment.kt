package com.example.usersfragments.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResultListener
import com.example.usersfragments.R
import com.example.usersfragments.data.User
import com.example.usersfragments.data.UserGenerator
import com.example.usersfragments.databinding.FragmentUserDatailBinding
import com.example.usersfragments.edit.EditFragment
import com.example.usersfragments.edit.EditFragment.Companion.ARG_USER_ID
import com.example.usersfragments.home.UsersFragment

const val ARG_SAVE_NAME = "saveName"
const val ARG_SAVE_LAST_NAME = "saveLastName"
const val ARG_SAVE_NUMBER = "saveNumber"

class UserDetailFragment : Fragment(R.layout.fragment_user_datail) {

    private var _binding: FragmentUserDatailBinding? = null
    private val binding
        get() = _binding!!

    private var userId: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserDatailBinding.inflate(inflater, container, false)

        var name: String? = null
        var lastName: String? = null
        var number: String? = null
        setFragmentResultListener(ARG_SAVE_NAME) { _, bundle ->
            name = bundle.getString(ARG_SAVE_NAME)
        }
        setFragmentResultListener(ARG_SAVE_LAST_NAME) { _, bundle ->
            lastName = bundle.getString(ARG_SAVE_LAST_NAME)
        }
        setFragmentResultListener(ARG_SAVE_NUMBER) { _, bundle ->
            number = bundle.getString(ARG_SAVE_NUMBER)
        }
        userId?.let{id ->
            UserGenerator.editUser(
                id,
                name ?: DEFAULT_STRING,
                lastName ?: DEFAULT_STRING,
                number ?: DEFAULT_STRING
            )
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            userId = it.getInt(ARG_USER_ID)
        }

        userId?.let {
            val user = UserGenerator.getUser(it)
            binding.firstName.text = user?.firstName
            binding.lastName.text = user?.lastName
            binding.phone.text = user?.number
        }

        binding.buttonEdit.setOnClickListener {
            parentFragmentManager.beginTransaction().run {
                val fragment = EditFragment.newInstance()
                setReorderingAllowed(true)
                replace(R.id.fragment_container_view, fragment, EditFragment.TAG)
                addToBackStack(EditFragment.TAG)
                commit()
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
        const val DEFAULT_STRING = ""
    }
}