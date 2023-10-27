package com.barbosahub.imgurApi.ui.home.view.model

import org.json.JSONObject
import java.io.Serializable

data class CatsJson(
    var id: String,
    var title: String,
    var link: String
) : Serializable {
    constructor(jsonObject: JSONObject): this(
        jsonObject.getString("id"),
        jsonObject.getString("title"),
        getLink(jsonObject)
    )
}

fun getLink(jsonObject: JSONObject): String {
   return jsonObject.getJSONArray("images").getJSONObject(0).getString("link")
}