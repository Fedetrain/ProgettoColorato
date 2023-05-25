package com.example.progetto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.progetto.Db.DBManager
import com.example.progetto.databinding.ActivityRegistrazioneBinding
import java.util.regex.Pattern

class ActivityRegistrazione : AppCompatActivity() {
    private lateinit var binding:ActivityRegistrazioneBinding
    private lateinit var dbManager: DBManager
    // Pattern per la validazione della password
    val PASSWORD_PATTERN = Pattern.compile(
        "^" +
                "(?=.*[0-9])" +  // almeno un numero
                "(?=.*[a-z])" +  // almeno una lettera minuscola
                "(?=.*[A-Z])" +  // almeno una lettera maiuscola
                "(?=\\S+$)" +  // nessuno spazio bianco
                ".{8,}" +  // almeno 8 caratteri
                "$"
    )

    override fun onCreate(savedInstanceState: Bundle?) {

        dbManager = DBManager(this)
        dbManager.open()

        super.onCreate(savedInstanceState)
        binding = ActivityRegistrazioneBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.buttonRegistrazione.setOnClickListener {
           if(controllaRegistrazione()){
               dbManager.insertutente(binding.editTextNome.text.toString(),
                   binding.editTextCognome.text.toString(),
                   binding.editTextEmail.text.toString(),
                   binding.editTextPassword.text.toString(),
                   binding.editTextResidenza.text.toString(),"-")
               val intent = Intent(this, ActivityPrincipale::class.java)
               startActivity(intent)

           }


        }

    }
    private fun controllaRegistrazione(): Boolean {
        val nome = binding.editTextNome.text.toString()
        val cognome = binding.editTextCognome.text.toString()
        val email = binding.editTextEmail.text.toString()
        val password = binding.editTextPassword.text.toString()
        val residenza = binding.editTextResidenza.text.toString()

        if (nome.isEmpty() || cognome.isEmpty() || email.isEmpty() || password.isEmpty() || residenza.isEmpty()) {
            Toast.makeText(this, "Completa tutti i campi", Toast.LENGTH_SHORT).show()
            return false
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this, "Email non valida", Toast.LENGTH_SHORT).show()
            return false
        }

        // Controlla se la password è nel formato corretto
        if (!PASSWORD_PATTERN.matcher(password).matches()) {
            Toast.makeText(this, "La password deve contenere almeno 8 caratteri, di cui almeno una lettera maiuscola, una lettera minuscola e un numero", Toast.LENGTH_LONG).show()
            return false
        }
        if(dbManager.controllaEsistenzaCredenziali(email,password)){
            Toast.makeText(this, "TI SEI GIA REGISTRATO", Toast.LENGTH_LONG).show()
            return false
        }


        // Se tutti i controlli passano, ritorna true
        return true
    }



    override fun onDestroy() {
        dbManager.close()
        super.onDestroy()
    }

}