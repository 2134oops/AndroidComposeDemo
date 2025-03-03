package com.example.observatory.network.gson

import com.example.observatory.network.model.Uvindex
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class GsonDeserializer : JsonDeserializer<Any> {
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): Any? {
        if (json.isJsonObject) {
            return context.deserialize<Uvindex>(json, typeOfT)
        }

        return null
    }
}