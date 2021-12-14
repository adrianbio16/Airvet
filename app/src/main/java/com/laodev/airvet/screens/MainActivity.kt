package com.laodev.airvet.screens

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.facebook.shimmer.ShimmerFrameLayout
import com.google.gson.Gson
import com.laodev.airvet.R
import com.laodev.airvet.adpater.UserAdapter
import com.laodev.airvet.models.UserModel
import com.laodev.airvet.utils.ApiUtil
import com.laodev.airvet.utils.ApiUtil.APIManagerCallback
import org.json.JSONObject
import java.util.*


class MainActivity : AppCompatActivity() {

    private lateinit var rclUser: RecyclerView
    private lateinit var shimmer: ShimmerFrameLayout
    private val users: MutableList<UserModel> = mutableListOf()
    private  lateinit var adapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initUIView()
        initData()
    }

    private fun initUIView() {
        shimmer = findViewById(R.id.shimer)
        rclUser = findViewById(R.id.rclUser)
        rclUser.layoutManager = LinearLayoutManager(this)
        rclUser.itemAnimator = DefaultItemAnimator();
        adapter = UserAdapter(users, this, object : UserAdapter.UserAdapterListener {
            override fun onClickCell(user: UserModel) {
                val gson = Gson()
                val strUserData: String = gson.toJson(user)
                val intent = Intent(this@MainActivity, DetailActivity::class.java)
                intent.putExtra("userdata", strUserData)
                startActivity(intent)
            }
        })
        rclUser.adapter = adapter
    }

    private fun initData() {
        rclUser.visibility = View.GONE
        shimmer.visibility = View.VISIBLE
        shimmer.startShimmer()
        users.clear()
        val params: MutableMap<String, String> = HashMap()
        params.put("results", "50")
        ApiUtil.onAPIConnectionResponse(
            ApiUtil.baseURL,
            params,
            ApiUtil.APIMethod.GET,
            object : APIManagerCallback {
                override fun onEventCallBack(obj: JSONObject?) {
                    val responseResult = obj!!.getJSONArray("results")
                    for (i in 0..(responseResult.length() - 1)) {
                        val user = UserModel(responseResult.getJSONObject(i))
                        users.add(user)
                    }
                    rclUser.visibility = View.VISIBLE
                    shimmer.visibility = View.GONE
                    shimmer.stopShimmer()
                    adapter.notifyDataSetChanged()
                }

                override fun onEventInternetError(e: Exception?) {

                }

                override fun onEventServerError(e: Exception?) {

                }
            })
    }
}