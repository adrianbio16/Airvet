package com.laodev.airvet.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.laodev.airvet.R


class AppUtil {

    companion object {
        fun loadImageByUrl(
            context: Context?,
            view: ImageView?,
            url: String?,
        ) {
            Glide.with(context!!).asBitmap().fitCenter().load(url)
                .placeholder(R.drawable.ic_user)
                .error(R.drawable.ic_user)
                .into(view!!)
        }
    }
}