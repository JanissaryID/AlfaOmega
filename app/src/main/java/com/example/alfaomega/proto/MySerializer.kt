package com.example.alfaomega.proto

import androidx.datastore.CorruptionException
import androidx.datastore.Serializer
import com.example.alfaomega.DataPreferences
import com.google.protobuf.InvalidProtocolBufferException
import java.io.InputStream
import java.io.OutputStream

class MySerializer: Serializer<DataPreferences> {
    override fun readFrom(input: InputStream): DataPreferences {
        try {
            return DataPreferences.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", exception)
        }
    }

    override fun writeTo(t: DataPreferences, output: OutputStream) {
        t.writeTo(output)
    }

}