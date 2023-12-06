package com.example.firebaserealtimedatabase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import com.example.firebaserealtimedatabase.databinding.ActivityMainBinding
import com.google.firebase.Firebase
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val userWrapper = UserWrapper()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.save.setOnClickListener {
            val user = User(
                name = "Amir Alimbekerov",
                age = 21,
                address = Address(city = "Almaty", street = "Abay street"),
                hobbies = listOf("Travelling", "Programming")
            )

            userWrapper.saveData(user) { success ->
                if (success) {
                    Toast.makeText(this, "User is saved", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Error in saving User", Toast.LENGTH_SHORT).show()
                }
            }
        }

        binding.get.setOnClickListener {
            userWrapper.getData()
        }

        userWrapper.getDataLiveData.observe(this, Observer { user ->

            user?.let {
                Log.d("MainActivity", "Received user data: $it")
            }
        })
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