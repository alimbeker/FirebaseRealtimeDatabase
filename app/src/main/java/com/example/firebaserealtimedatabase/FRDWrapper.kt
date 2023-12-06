package com.example.firebaserealtimedatabase

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.FirebaseDatabase

abstract class FRDWrapper<T> {
    private val db = FirebaseDatabase.getInstance()

    protected abstract fun getTableName(): String
    protected abstract fun getClassType(): Class<T>


    private val _getDataLiveData = MutableLiveData<T?>()
    val getDataLiveData: LiveData<T?> = _getDataLiveData


    fun saveData(value: T, successSave: ((Boolean) -> Unit)? = null) {
        db.getReference(getTableName()).setValue(value) { error, _ ->
            successSave?.invoke(error == null)
            error?.let {
                Log.e("FRDWrapper", it.message)
            }
        }
    }


    fun getData() {
        db.getReference(getTableName()).get().addOnSuccessListener {
            _getDataLiveData.postValue(it.getValue(getClassType()))
        }
    }




}