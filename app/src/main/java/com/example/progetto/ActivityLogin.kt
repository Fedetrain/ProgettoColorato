package com.example.progetto

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import java.util.*
import javax.mail.*
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.progetto.Db.DBManager
import com.example.progetto.databinding.ActivityLoginBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ActivityLogin : AppCompatActivity() {

    private lateinit var binding:ActivityLoginBinding
    private lateinit var dbManager: DBManager


    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dbManager = DBManager(this)
        dbManager.open()
        binding.buttonLogin.setOnClickListener {
           // val email = binding.editTextEmail.text.toString()
            //val password = binding.editTextPassword.text.toString()
            //login(email, password)
            val intent = Intent(this, ActivityPrincipale::class.java)
            startActivity(intent)



        }
        binding.textViewRegistrati.setOnClickListener {
            val intent = Intent(this, ActivityRegistrazione::class.java)
            startActivity(intent)


        }
        binding.textPasswordDimenticata.setOnClickListener {
        }
    }
    private fun login(email: String, password: String) {
        // Controlla se l'email esiste nel sistema
        if (dbManager.controllaEsistenzaEmail(email)) {
            if (dbManager.controllaEsistenzaCredenziali(email, password)) {
                val intent = Intent(this, ActivityPrincipale::class.java)
                startActivity(intent)
            } else {
                // Password errata
                Toast.makeText(this, "PASSWORD ERRATA", Toast.LENGTH_LONG).show()
            }
        } else {
            Toast.makeText(this, "Non sei registrato. Registrati prima di accedere.", Toast.LENGTH_LONG).show()
        }
    }





    override fun onDestroy() {
        dbManager.close()
        super.onDestroy()
    }



}