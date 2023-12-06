package com.example.firebaserealtimedatabase

import android.util.Log
import com.google.firebase.database.FirebaseDatabase

abstract class FRDWrapper<T> {
    private val db = FirebaseDatabase.getInstance()

    protected abstract fun getTableName(): String

    fun saveData(value: T, successSave: ((Boolean) -> Unit)? = null) {
        db.getReference(getTableName()).setValue(value) { error, _ ->
            successSave?.invoke(error == null)
            error?.let {
                Log.e("FRDWrapper", it.message)
            }
        }
    }




}