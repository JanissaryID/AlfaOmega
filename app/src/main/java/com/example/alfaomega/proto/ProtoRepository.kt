package com.example.alfaomega.proto

import android.content.Context
import android.util.Log
import androidx.datastore.DataStore
import androidx.datastore.createDataStore
import com.example.alfaomega.DataPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import java.io.IOException

class ProtoRepository(context: Context) {

    private val datastore: DataStore<DataPreferences> = context.createDataStore(
        fileName = "myData",
        serializer = MySerializer()
    )

    val readProto: Flow<DataPreferences> = datastore.data
        .catch { exception ->
            if(exception is IOException){
                Log.d("debug", exception.message.toString())
                emit(DataPreferences.getDefaultInstance())
            }
            else{
                throw exception
            }
        }

//    val readProtoStateFlow: StateFlow<DataPreferences> get() = dataProto

    suspend fun updateStore(keyStore: String){
        datastore.updateData { preference ->
            preference.toBuilder().setSTOREID(keyStore).build()
        }
    }

//    suspend fun updateWashTimeGiant(WashTimeGiant: Int){
//        datastore.updateData { preference ->
//            preference.toBuilder().setTIMEWASHERGIANT(WashTimeGiant).build()
//        }
//    }
//
//    suspend fun updateDryTimeGiant(DryTimeGiant: Int){
//        datastore.updateData { preference ->
//            preference.toBuilder().setTIMEDRYERGIANT(DryTimeGiant).build()
//        }
//    }
//
//    suspend fun updateWashTimeTitan(WashTimeTitan: Int){
//        datastore.updateData { preference ->
//            preference.toBuilder().setTIMEWASHERGIANT(WashTimeTitan).build()
//        }
//    }
//
//    suspend fun updateDryTimeTitan(DryTimeTitan: Int){
//        datastore.updateData { preference ->
//            preference.toBuilder().setTIMEDRYERGIANT(DryTimeTitan).build()
//        }
//    }
}