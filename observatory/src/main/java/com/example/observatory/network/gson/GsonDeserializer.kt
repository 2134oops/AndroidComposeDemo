package com.example.observatory.network.gson

import com.example.observatory.network.model.Uvindex
import com.google.gson.Gson
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class GsonDeserializer<T> : JsonDeserializer<T?> {
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): T? {
        return if (json.isJsonObject ) {
            Gson().fromJson(json,typeOfT)
        } else if (json.isJsonPrimitive && json.asJsonPrimitive.isString && json.asString.isEmpty()) {
            null
        } else {
            null
        }
    }
}