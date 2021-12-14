package com.laodev.airvet.utils

import com.zhy.http.okhttp.OkHttpUtils
import com.zhy.http.okhttp.callback.StringCallback
import okhttp3.Call
import org.json.JSONException
import org.json.JSONObject


class ApiUtil {

    enum class APIMethod {
        GET, POST
    }

    companion object {
        public var baseURL: String = "https://randomuser.me/api"

        fun onAPIConnectionResponse(url: String, params: MutableMap<String, String>, method: APIMethod, callback: APIManagerCallback) {
            if (method == APIMethod.GET) {
                OkHttpUtils.get().url(url)
                        .params(params)
                        .build()
                        .execute(object : StringCallback() {
                            override fun onError(call: Call, e: Exception, id: Int) {
                                callback.onEventInternetError(e)
                            }

                            override fun onResponse(response: String, id: Int) {
                                try {
                                    val obj = JSONObject(response)
                                    callback.onEventCallBack(obj)
                                } catch (e: JSONException) {
                                    callback.onEventServerError(e)
                                    e.printStackTrace()
                                }
                            }
                        })
            } else {
                OkHttpUtils.post().url(url)
                        .params(params)
                        .build()
                        .execute(object : StringCallback() {
                            override fun onError(call: Call, e: Exception, id: Int) {
                                callback.onEventInternetError(e)
                            }

                            override fun onResponse(response: String, id: Int) {
                                try {
                                    val obj = JSONObject(response)
                                    callback.onEventCallBack(obj)
                                } catch (e: JSONException) {
                                    callback.onEventServerError(e)
                                    e.printStackTrace()
                                }
                            }
                        })
            }
        }
    }

    interface APIManagerCallback {
        fun onEventCallBack(obj: JSONObject?)
        fun onEventInternetError(e: Exception?)
        fun onEventServerError(e: Exception?)
    }
}