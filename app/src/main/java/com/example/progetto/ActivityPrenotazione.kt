package com.example.progetto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.progetto.databinding.ActivityLoginBinding
import com.example.progetto.databinding.ActivityPrenotazioneBinding

class ActivityPrenotazione : AppCompatActivity() {
    private lateinit var binding: ActivityPrenotazioneBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPrenotazioneBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}