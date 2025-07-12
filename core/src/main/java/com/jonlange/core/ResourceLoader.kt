package com.jonlange.core

interface ResourceLoader {
    fun <T> loadJSONFromRaw(resId: Int, deserializer: kotlinx.serialization.DeserializationStrategy<T>): T
}