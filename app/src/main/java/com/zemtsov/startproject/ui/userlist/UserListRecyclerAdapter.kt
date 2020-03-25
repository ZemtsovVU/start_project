package com.zemtsov.startproject.ui.userlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import com.zemtsov.startproject.data.entity.User
import com.zemtsov.startproject.databinding.ItemUserBinding
import com.zemtsov.startproject.ui.base.BaseRecyclerAdapter
import com.zemtsov.startproject.ui.base.OnItemClickListener

/**
 * Developed by Viktor Zemtsov (zemtsovvu@gmail.com)
 * 2020
 *
 * @author Viktor Zemtsov
 */
class UserListRecyclerAdapter :
    BaseRecyclerAdapter<User, UserListRecyclerAdapter.UserViewHolder>() {

    private var _viewBinding: ItemUserBinding? = null
    private val viewBinding get() = _viewBinding!!

    var onItemClickListener: OnItemClickListener<User>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        _viewBinding = ItemUserBinding.inflate(inflater, parent, false)
        return UserViewHolder(viewBinding.root)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    override fun getItemCount(): Int = getItems().size

    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: User?) {
            Glide.with(itemView)
                .load(item?.avatarUrl)
                .apply(RequestOptions.bitmapTransform(CircleCrop()))
                .into(viewBinding.imageAvatar)

            viewBinding.textName.text = item?.login

            itemView.setOnClickListener {
                onItemClickListener?.onItemClick(itemView, adapterPosition, item)
            }
        }
    }
}