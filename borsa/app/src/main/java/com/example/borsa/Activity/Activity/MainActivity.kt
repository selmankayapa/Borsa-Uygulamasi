package com.example.borsa.Activity.Activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.borsa.Activity.hisseActivity
import com.example.borsa.Activity.kayitActivity
import com.example.borsa.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        auth = Firebase.auth

    }


    fun girişYap(view: View) {
        auth.signInWithEmailAndPassword(
            binding.editTextTextEmailAddress.text.toString(),
            binding.editTextTextPassword.text.toString()
        ).addOnCompleteListener { task ->
            if (task.isSuccessful) {

                Toast.makeText(this, "Giriş Başarılı.", Toast.LENGTH_LONG).show()

                val intent = Intent(this, hisseActivity::class.java)
                startActivity(intent)
                finish()
            }
        }.addOnFailureListener { exceptions ->
            Toast.makeText(this, exceptions.localizedMessage, Toast.LENGTH_LONG).show()
        }


    }

    fun kayıtOl(view: View) {
        val intent = Intent(this, kayitActivity::class.java)
        startActivity(intent)
        finish()
    }
}
