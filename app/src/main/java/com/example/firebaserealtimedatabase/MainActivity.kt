package com.example.firebaserealtimedatabase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.firebaserealtimedatabase.databinding.ActivityMainBinding
import com.google.firebase.Firebase
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {


    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.save.setOnClickListener {
            val db = FirebaseDatabase.getInstance()
            db.getReference("").setValue()
        }


    }
}