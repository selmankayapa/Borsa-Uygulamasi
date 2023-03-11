package com.example.borsa.Activity

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.borsa.R
import com.example.borsa.databinding.ActivityKayitBinding
import com.google.firebase.auth.FirebaseAuth
import java.text.SimpleDateFormat
import java.util.*

public class kayitActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityKayitBinding
    val myCalendar = Calendar.getInstance()

    private lateinit var btnTarihSecme : Button
    private lateinit var editTextDate : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKayitBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        auth = FirebaseAuth.getInstance()


        btnTarihSecme = findViewById(R.id.btnTarihSecme)
        editTextDate = findViewById(R.id.editTextDate)




        val datePicker = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            myCalendar.set(Calendar.YEAR,year)
            myCalendar.set(Calendar.MONTH,month)
            myCalendar.set(Calendar.DAY_OF_MONTH,dayOfMonth)
            updateLable(myCalendar)
        }
        btnTarihSecme.setOnClickListener{
            DatePickerDialog(this,datePicker,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show()
        }

        }
    private fun updateLable(myCalendar: Calendar) {
        val myFormat = "dd-MM-yyyy"
        val sdf = SimpleDateFormat(myFormat, Locale.UK)

        editTextDate.setText(sdf.format(myCalendar.time))

    }


    fun hesapOluştur(view: View){
        val email = binding.emailText.text.toString()
        val password= binding.txtpassword.text.toString()
        val secilenTarih = myCalendar.get(Calendar.YEAR)

        if(secilenTarih>2000){
            Toast.makeText(this,"18 yaş altında olanlar kayıt yaptıramaz..",Toast.LENGTH_LONG).show()
        }else {
            auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener{ task ->
                if (task.isSuccessful){
                    val intent =Intent(this, hisseActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }.addOnFailureListener { exceptions ->
                Toast.makeText(this,exceptions.localizedMessage,Toast.LENGTH_LONG).show()
            }
        }







    }





}

