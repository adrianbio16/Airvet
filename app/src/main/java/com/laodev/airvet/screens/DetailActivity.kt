package com.laodev.airvet.screens

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.google.gson.Gson
import com.laodev.airvet.R
import com.laodev.airvet.models.UserModel
import com.laodev.airvet.utils.AppUtil

class DetailActivity : AppCompatActivity() {

    private lateinit var imgUser: ImageView
    private lateinit var txtUserName: TextView
    private lateinit var txtUserCountry: TextView
    private lateinit var txtUserLocation: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val userData: String = intent.getStringExtra("userdata")!!
        val gson = Gson()
        val user: UserModel = gson.fromJson(userData, UserModel::class.java)

        imgUser = findViewById(R.id.imgUser)
        txtUserName = findViewById(R.id.txtUserName)
        txtUserCountry = findViewById(R.id.txtUserCountry)
        txtUserLocation = findViewById(R.id.txtUserLocation)

        initView(user)
    }

    private fun initView(user: UserModel) {
        AppUtil.loadImageByUrl(this, imgUser, user.userAvvatar.thumbnail)
        txtUserName.setText(user.name.getFullName())
        txtUserCountry.setText(user.location.country)
        txtUserLocation.setText(user.location.getLocation())
    }
}