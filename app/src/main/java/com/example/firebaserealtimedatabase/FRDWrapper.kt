package com.example.firebaserealtimedatabase

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.FirebaseDatabase

abstract class FRDWrapper<T : Any> {
    private val db = FirebaseDatabase.getInstance()

    protected abstract fun getTableName(): String
    protected abstract fun getClassType(): Class<T>

    private val _getDataLiveData = MutableLiveData<T?>()
    val getDataLiveData: LiveData<T?> = _getDataLiveData

    fun saveData(value: T, successSave: ((Boolean) -> Unit)? = null) {
        db.getReference(getTableName()).setValue(value)
            .addOnCompleteListener { task ->
                successSave?.invoke(task.isSuccessful)
                if (!task.isSuccessful) {
                    Log.e("FRDWrapper", "Error saving data", task.exception)
                }
            }
    }

    fun getData() {
        db.getReference(getTableName()).get().addOnSuccessListener { snapshot ->
            _getDataLiveData.postValue(snapshot.getValue(getClassType()))
        }.addOnFailureListener {
            Log.e("FRDWrapper", "Error getting data", it)
        }
    }
}
