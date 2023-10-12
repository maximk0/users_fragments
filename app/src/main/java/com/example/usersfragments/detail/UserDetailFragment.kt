package com.example.usersfragments.detail

import android.os.Bundle
import android.util.Log
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

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            userId = it.getInt(ARG_USER_ID)
        }

        getFragmentResult(ARG_SAVE_NAME)
        getFragmentResult(ARG_SAVE_LAST_NAME)
        getFragmentResult(ARG_SAVE_NUMBER)

        userId?.let { setContent(it) }

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

    private fun setContent(userId: Int) {
            val user = UserGenerator.getUser(userId)
        user?.let {
            binding.firstName.text = it.firstName
            binding.lastName.text = it.lastName
            binding.phone.text = it.number
        }
    }

    private fun getFragmentResult(arg: String) {
        var result: String
        setFragmentResultListener(arg) { _, bundle ->
            result = bundle.getString(arg).toString()
            userId?.let { id ->
                when(arg) {
                    ARG_SAVE_NAME -> UserGenerator.editUserName(id, result)
                    ARG_SAVE_LAST_NAME -> UserGenerator.editUserLastName(id, result)
                    ARG_SAVE_NUMBER -> UserGenerator.editUserNumber(id, result)
                }
                setContent(id)
            }
            Log.d("TAG", "USER_DETAIL name: ${bundle.getString(ARG_SAVE_NAME)}, result: $result")
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(userId: Int) = UserDetailFragment().apply {
            arguments = Bundle().apply {
                putInt(ARG_USER_ID, userId)
            }
        }

        const val TAG = "UserDetailFragment"
        const val ARG_USER_ID = "userId"
    }
}