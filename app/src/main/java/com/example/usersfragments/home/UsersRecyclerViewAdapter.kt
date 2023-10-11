package com.example.usersfragments.home

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.usersfragments.data.User
import com.example.usersfragments.databinding.UserBinding


class UsersRecyclerViewAdapter(
    private val onClickUser: (userId: Int) -> Unit,
) : RecyclerView.Adapter<UsersRecyclerViewAdapter.ViewHolder>() {

    private var users: List<User> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(UserBinding.inflate(LayoutInflater.from(parent.context), parent, false))


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = users[position]
        with(holder.binding) {
            firstName.text = item.firstName
            lastName.text = item.lastName

            root.setOnClickListener {
                onClickUser(item.id)
            }
        }
    }

    override fun getItemCount(): Int = users.size

    fun setData(countryList: List<User>) {
        users = countryList
        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: UserBinding) : RecyclerView.ViewHolder(binding.root)

}