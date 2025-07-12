package com.jonlange.jhtassessment.data

import android.content.Context
import com.jonlange.core.ResourceLoader
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import javax.inject.Inject

class AndroidResourceLoader @Inject constructor(
    @ApplicationContext private val context: Context,
    private val json: Json
) : ResourceLoader {

    @OptIn(ExperimentalSerializationApi::class)
    override fun <T> loadJSONFromRaw(resId: Int,
                                     deserializer: DeserializationStrategy<T>): T {
        return context.resources.openRawResource(resId).use { inputStream ->
            json.decodeFromStream(deserializer, inputStream)
        }
    }
}