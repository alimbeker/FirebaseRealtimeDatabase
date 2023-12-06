package com.example.firebaserealtimedatabase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import com.example.firebaserealtimedatabase.databinding.ActivityMainBinding
import com.google.firebase.Firebase
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {


    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)



        val db = FirebaseDatabase.getInstance()
        binding.save.setOnClickListener {


        }

        binding.get.setOnClickListener {

        }


    }


}


data class User(
    val id: String? = null,
    val name: String? = null,
    val age: Int? = null,
    val address: Address? = null,
    val hobbies: List<String>? = null
)

data class Address(
    val city: String? = null,
    val street: String? = null
)