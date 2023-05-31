package com.example.progetto


import android.annotation.SuppressLint
import android.database.Cursor
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.progetto.Db.CountryDbHelper
import com.example.progetto.databinding.LayoutFilmBinding

class Adapter(private val _cursor : Cursor) : RecyclerView.Adapter<Adapter.ViewHolder>(){


    class ViewHolder(binding: LayoutFilmBinding): RecyclerView.ViewHolder(binding.root){
        //Classe che rappresenta il contenitore

        //campi del contenitore
        var imageFilm=binding.imageView
        val titoloFilm=binding.titoloFilm
        val salaFilm=binding.salaFilm
        val orarioFilm=binding.orarioFilm
    }

    //crea i contenitori e gli assegna il layout definito per le cardview
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):ViewHolder{

        val view= LayoutFilmBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        //ritorna il numero di elementi che dobbiamo gestire
        return _cursor.count
    }

    @SuppressLint("Range")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (_cursor.moveToPosition(position)) {
            holder.imageFilm.setImageResource(R.drawable.download)
            holder.titoloFilm.text = _cursor.getLong(_cursor.getColumnIndex(CountryDbHelper.NOME_FILM)).toString()
            holder.salaFilm.text = _cursor.getString(_cursor.getColumnIndex(CountryDbHelper.SALA_FILM))
            holder.orarioFilm.text = _cursor.getString(_cursor.getColumnIndex(CountryDbHelper.ORA_INIZIO))

        }
    }


}