package com.example.myeee

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.example.myeee.databinding.ActivitySignInactivityBinding

class SignINActivity : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth
    private val binding : ActivitySignInactivityBinding by lazy {
        ActivitySignInactivityBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        firebaseAuth = FirebaseAuth.getInstance()
        binding.registerbtn.setOnClickListener{

            val email = binding.etSEmail.text.toString()
            val pass = binding.etSPass.text.toString()
            val confirmPass = binding.etConPass.text.toString()

            if (email.isNotEmpty() && pass.isNotEmpty() && confirmPass.isNotEmpty()) {
                if (pass == confirmPass) {

                    firebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener {
                        if (it.isSuccessful) {
                            val intent = Intent(this,LoginActivity::class.java)
                            startActivity(intent)
                        } else {
                            Toast.makeText(this, "Minimum 6 character password", Toast.LENGTH_SHORT).show()

                        }
                    }
                } else {
                    Toast.makeText(this, "Password is not matching", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Empty Fields Are not Allowed !!", Toast.LENGTH_SHORT).show()

            }

        }

        binding.haveAccountLogin.setOnClickListener{
            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }
    }
}