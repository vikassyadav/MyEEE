package com.example.myeee

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.Toast
import com.example.myeee.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    private lateinit var firebaseAuth: FirebaseAuth
    private val binding : ActivityLoginBinding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        firebaseAuth = FirebaseAuth.getInstance()
        binding.loginbtn.setOnClickListener{

            val email = binding.editTextTextEmailAddress3.text.toString()
            val pass = binding.editTextTextPassword.text.toString()
            if (email.isNotEmpty() && pass.isNotEmpty()) {

                firebaseAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener {
                    if (it.isSuccessful) {
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this, "Invalid Credentials", Toast.LENGTH_SHORT).show()

                    }
                }
            } else {
                Toast.makeText(this, "Empty Fields Are not Allowed !!", Toast.LENGTH_SHORT).show()

            }

        }

        binding.dontHActbtn.setOnClickListener{
            val intent = Intent(this,SignINActivity::class.java)
            startActivity(intent)
        }

        fun onStart() {
            super.onStart()

            if(firebaseAuth.currentUser != null){
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }


    }
}