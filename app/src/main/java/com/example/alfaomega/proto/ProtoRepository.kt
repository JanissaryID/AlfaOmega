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
//                Log.d("debug", exception.message.toString())
                emit(DataPreferences.getDefaultInstance())
            }
            else{
                throw exception
            }
        }

    suspend fun updateStore(keyStore: String){
        datastore.updateData { preference ->
            preference.toBuilder().setSTOREID(keyStore).build()
        }
    }

    suspend fun updateNameUser(Nameuser: String){
        datastore.updateData { preference ->
            preference.toBuilder().setUSERNAME(Nameuser).build()
        }
    }
//
    suspend fun updateTypeUser(Typeuser: Int){
        datastore.updateData { preference ->
            preference.toBuilder().setUSERTYPE(Typeuser).build()
        }
    }

    suspend fun updateAddressDevice(AddressDevice: String){
        datastore.updateData { preference ->
            preference.toBuilder().setADDRESSDEVICE(AddressDevice).build()
        }
    }

    suspend fun updateOwnerId(OwnerID: String){
        datastore.updateData { preference ->
            preference.toBuilder().setOWNERID(OwnerID).build()
        }
    }

    suspend fun updateUUID(UUID_Device: String){
        datastore.updateData { preference ->
            preference.toBuilder().setUUIDSTRING(UUID_Device).build()
        }
    }

    suspend fun updateEditMode(status: Boolean){
        datastore.updateData { preference ->
            preference.toBuilder().setEDITMODE(status).build()
        }
    }
}