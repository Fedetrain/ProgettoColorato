package com.example.progetto

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.fragment.app.replace

import com.example.myapplicationkhgyuiv.FragmentProfilo

import com.example.progetto.databinding.ActivityPrincipaleBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.color.DynamicColors

class ActivityPrincipale : AppCompatActivity() {
    private lateinit var navView: BottomNavigationView

    private lateinit var binding: ActivityPrincipaleBinding
    private val fragmentManager = supportFragmentManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPrincipaleBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navView = binding.navView
        setTheme(R.style.AppTheme)
        fragmentManager.commit {
            add<FragmentHome>(R.id.fragmentContainerView)
        }
        navView.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.home -> {
                    fragmentManager.commit {
                        replace<FragmentHome>(R.id.fragmentContainerView)
                    }

                    true
                }

                R.id.profilo -> {
                    fragmentManager.commit {
                        replace<FragmentProfilo>(R.id.fragmentContainerView)
                    }
                    true
                }

                else -> false
            }
        }



    }
}