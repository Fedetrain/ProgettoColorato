package com.example.progetto

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

import androidx.navigation.ui.AppBarConfiguration

import com.example.progetto.databinding.ActivityPrincipaleBinding

class ActivityPrincipale : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityPrincipaleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPrincipaleBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }



}