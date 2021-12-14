package com.laodev.airvet.adpater

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.laodev.airvet.R
import com.laodev.airvet.models.UserModel
import com.laodev.airvet.utils.AppUtil

class UserAdapter(private val userList: MutableList<UserModel>, private val context: Context, private val listener: UserAdapterListener) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_user, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = userList.get(position)
        holder.txtUserName.setText(user.name.getFullName())
        holder.txtUserCountry.setText(user.location.country)
        AppUtil.loadImageByUrl(context, holder.userAvatar, user.userAvvatar.thumbnail)
        holder.itemView.setOnClickListener(View.OnClickListener {
            listener.onClickCell(user)
        })
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var userAvatar: ImageView
        var txtUserName: TextView
        var txtUserCountry: TextView

        init {
            userAvatar = itemView.findViewById(R.id.imgUser)
            txtUserName = itemView.findViewById(R.id.txtUserName)
            txtUserCountry = itemView.findViewById(R.id.txtUserCountry)
        }
    }

    interface UserAdapterListener {
        fun onClickCell(user: UserModel)
    }
}