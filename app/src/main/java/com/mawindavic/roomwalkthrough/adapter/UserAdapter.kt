package com.mawindavic.roomwalkthrough.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mawindavic.roomwalkthrough.R
import com.mawindavic.roomwalkthrough.data.User
import com.mawindavic.roomwalkthrough.data.UserAction
import com.mawindavic.roomwalkthrough.databinding.UserItemBinding

class UserAdapter(private val callback: (user: User, action: UserAction) -> Unit) :
    ListAdapter<User, UserViewHolder>(UserComparator) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = getItem(position)
        holder.bind(user, callback)
    }
}

object UserComparator : DiffUtil.ItemCallback<User>() {
    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean = oldItem == newItem
}

class UserViewHolder private constructor(private val binding: UserItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(user: User, callback: (user: User, action: UserAction) -> Unit) {
        binding.user = user

        binding.delBtn.setOnClickListener {
            callback(user, UserAction.REMOVE)
        }

        binding.textView.setOnClickListener {
            callback(user, UserAction.EDIT)
        }
        binding.executePendingBindings()
    }

    companion object {
        fun create(parent: ViewGroup): UserViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val itemBinding: UserItemBinding =
                DataBindingUtil.inflate(layoutInflater, R.layout.user_item, parent, false)
            return UserViewHolder(itemBinding)
        }
    }
}