package com.laodev.airvet.models

import org.json.JSONObject

class UserModel(jsonData: JSONObject) {
    var name: NameModel = NameModel(jsonData.getJSONObject("name"))
    var location: LocationModel = LocationModel(jsonData.getJSONObject("location"))
    var userAvvatar: UserAvatarModel = UserAvatarModel(jsonData.getJSONObject("picture"))

}

class NameModel(jsonData: JSONObject) {
    var title: String = jsonData.getString("title")
    var first: String = jsonData.getString("first")
    var last: String = jsonData.getString("last")

    fun getFullName() : String {
        return title + "." + first + " " + last
    }
}

class LocationModel(jsonData: JSONObject) {
    var street: StreetModel = StreetModel(jsonData.getJSONObject("street"))
    var city: String = jsonData.getString("city")
    var state: String = jsonData.getString("state")
    var country: String = jsonData.getString("country")
    var postcode = jsonData.get("postcode")

    fun getLocation(): String {
        return street.name + ", " + street.number + ", " + city + ", " + state
    }
}

class StreetModel(jsonData: JSONObject) {
    var number: Int = jsonData.getInt("number")
    var name: String = jsonData.getString("name")
}

class UserAvatarModel(jsonData: JSONObject) {
    var large: String = jsonData.getString("large")
    var medium: String = jsonData.getString("medium")
    var thumbnail: String = jsonData.getString("thumbnail")
}