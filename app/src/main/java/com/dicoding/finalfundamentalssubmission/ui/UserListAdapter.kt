package com.dicoding.finalfundamentalssubmission.ui

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.Glide
import com.dicoding.finalfundamentalssubmission.databinding.UserListRvBinding
import com.dicoding.finalfundamentalssubmission.remote.response.ItemsItem

class UserListAdapter : ListAdapter<ItemsItem, UserListAdapter.MyViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = UserListRvBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val users = getItem(position)
        holder.bind(users)

        holder.itemView.setOnClickListener {
            val username = users.login
            val avatar = users.avatarUrl
            val intentDetailUser = Intent(holder.itemView.context, DetailUserActivity::class.java)
            intentDetailUser.putExtra("username",username)
            intentDetailUser.putExtra("avatar",avatar)
            holder.itemView.context.startActivity(intentDetailUser)
        }
    }

    class MyViewHolder(private val binding: UserListRvBinding) : RecyclerView.ViewHolder(binding.root) {
        private val avatarImageView: ImageView = binding.IvAvatar
        fun bind(users: ItemsItem){
            binding.tvItem.text = "${users.login}\n"
            Glide.with(itemView)
                .load(users.avatarUrl)
                .into(avatarImageView)
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ItemsItem>() {
            override fun areItemsTheSame(oldItem: ItemsItem, newItem: ItemsItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: ItemsItem, newItem: ItemsItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}