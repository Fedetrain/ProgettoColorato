package com.example.progetto

import android.app.DatePickerDialog
import android.icu.util.Calendar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.example.progetto.databinding.ActivityLoginBinding
import com.example.progetto.databinding.ActivityPrenotazioneBinding

class ActivityPrenotazione : AppCompatActivity() {
    private lateinit var binding: ActivityPrenotazioneBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPrenotazioneBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val ArrayAdapterPosti = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, arrayOf(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20))

        binding.spinnerPosti.adapter=ArrayAdapterPosti

        binding.editTextData.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                val selectedDate = "${dayOfMonth}/${month + 1}/${year}"
                binding.editTextData.setText(selectedDate)
            }, year, month, day)

            datePickerDialog.show()
        }


    }


}