package com.example.usersfragments.home

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.usersfragments.R
import com.example.usersfragments.data.User

import com.example.usersfragments.home.placeholder.PlaceholderContent.PlaceholderItem
import com.example.usersfragments.databinding.FragmentItemBinding

class UsersRecyclerViewAdapter : RecyclerView.Adapter<UsersRecyclerViewAdapter.ViewHolder>() {

    private var users: List<User> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(FragmentItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = users[position]
        with(holder.binding) {
            firstName.text = item.firstName
            lastName.text = item.lastName
            phoneNumber.text = item.number
        }
    }

    override fun getItemCount(): Int = users.size

    fun setData(countryList: List<User>) {
        users = countryList
        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: FragmentItemBinding) : RecyclerView.ViewHolder(binding.root)

}