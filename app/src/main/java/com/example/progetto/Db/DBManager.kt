package com.example.progetto.Db


import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase

class DBManager(val context: Context) {
    private lateinit var helper: CountryDbHelper
    private lateinit var db: SQLiteDatabase

    fun open(): DBManager {
        helper = CountryDbHelper(context)
        //voglio aprire questo database in modalita scrittura
        db = helper.writableDatabase
        return this
    }

    fun close(){
        helper.close()
    }
    fun insertFilm(idFilm:Long,imageFilm: Int, titoloFilm:String, sala:String, orario:String,durata:String){
        val values = ContentValues().apply {
            put(CountryDbHelper._ID_FILM, idFilm)
            put(CountryDbHelper.NOME_FILM, titoloFilm)
            put(CountryDbHelper.SALA_FILM, sala)
            put(CountryDbHelper.ORA_INIZIO, orario)
            put(CountryDbHelper.DURATA, durata)
        }
        val newRowId = db.insert(CountryDbHelper.TABLE_CINEMA, null, values)

    }

    fun insertutente(nome: String, cognome: String,email:String,password:String,indirizzo: String, pagamento:String){
        val values = ContentValues().apply {
            put(CountryDbHelper.NOME, nome)
            put(CountryDbHelper.COGNOME, cognome)
            put(CountryDbHelper.EMAIL, email)
            put(CountryDbHelper.PASSWORD, password)
            put(CountryDbHelper.INDIRIZZ0, indirizzo)
            put(CountryDbHelper.PAGAMENTO, pagamento)
        }
        val newRowId = db.insert(CountryDbHelper.TABLE_UTENTI, null, values)
    }
    fun modificaEmail(new_email:String,vecchia_email:String):Int{
        val selection = "${CountryDbHelper.EMAIL} = ?"
        val selectionArgs = arrayOf(vecchia_email)
        val values = ContentValues().apply {
            put(CountryDbHelper.EMAIL, new_email)
        }
        val n = db.update(CountryDbHelper.TABLE_UTENTI, values,selection,selectionArgs)
        return n
    }
    fun modificaPassword(new_password:String,vecchia_password:String):Int{
        val selection = "${CountryDbHelper.PASSWORD} = ?"
        val selectionArgs = arrayOf(vecchia_password)
        val values = ContentValues().apply {
            put(CountryDbHelper.PASSWORD, new_password)
        }
        val n = db.update(CountryDbHelper.TABLE_UTENTI, values,selection,selectionArgs)
        return n
    }


    fun updateutente(_id: Long,email:String,password:String,nome: String, cognome: String,indirizzo: String, pagamento:String): Int{
        val selection = "${CountryDbHelper._ID} = ?"
        val selectionArgs = arrayOf(_id.toString())

        val values = ContentValues().apply {
            put(CountryDbHelper.EMAIL, email)
            put(CountryDbHelper.PASSWORD, password)
            put(CountryDbHelper.NOME, nome)
            put(CountryDbHelper.COGNOME, cognome)
            put(CountryDbHelper.INDIRIZZ0, indirizzo)
            put(CountryDbHelper.PAGAMENTO, pagamento)
        }
        val n = db.update(CountryDbHelper.TABLE_UTENTI, values, selection, selectionArgs)
        return n
    }

    fun deleteUtente(_id: Long){
        val selection = "${CountryDbHelper._ID} = ?"
        val selectionArgs = arrayOf(_id.toString())
        db.delete(CountryDbHelper.TABLE_UTENTI, selection, selectionArgs)
    }

    fun fetchAllUtenti(): Cursor{
        //projezione delle 3 colonne
        val projection = arrayOf(CountryDbHelper._ID, CountryDbHelper.EMAIL,CountryDbHelper.PASSWORD,CountryDbHelper.NOME, CountryDbHelper.COGNOME,CountryDbHelper.INDIRIZZ0,CountryDbHelper.PAGAMENTO)
        val cursor = db.query(
            CountryDbHelper.TABLE_UTENTI,
            projection,
            null,
            null,
            null,
            null,
            null
        )
        //il cursore punta a -1 non a 0
        cursor?.moveToFirst()
        return cursor
    }
    fun fetchAllFilm(): Cursor{
        //projezione delle 3 colonne
        val projection = arrayOf(CountryDbHelper._ID_FILM, CountryDbHelper.NOME_FILM,CountryDbHelper.SALA_FILM,CountryDbHelper.ORA_INIZIO,CountryDbHelper.DURATA)
        val cursor = db.query(
            CountryDbHelper.TABLE_CINEMA,
            projection,
            null,
            null,
            null,
            null,
            null
        )
        //il cursore punta a -1 non a 0
        cursor?.moveToFirst()
        return cursor
    }
    fun controllaEsistenzaCredenziali(email: String, password: String): Boolean {
        val db = CountryDbHelper(context).readableDatabase

        val projection = arrayOf(CountryDbHelper.EMAIL, CountryDbHelper.PASSWORD)
        val selection = "${CountryDbHelper.EMAIL} = ? AND ${CountryDbHelper.PASSWORD} = ?"
        val selectionArgs = arrayOf(email, password)

        val cursor= db.query(
            CountryDbHelper.TABLE_UTENTI,
            projection,
            selection,
            selectionArgs,
            null,
            null,
            null
        )

        //il cursore punta a -1 non a 0
        cursor?.moveToFirst()
        if(cursor.count!=0){
            return true
        }
        return false
    }

    fun controllaEsistenzaEmail(email: String): Boolean {
        val db = CountryDbHelper(context).readableDatabase
        val projection = arrayOf(CountryDbHelper.EMAIL)
        val selection = "${CountryDbHelper.EMAIL} = ?"
        val selectionArgs = arrayOf(email)

        val cursor= db.query(
            CountryDbHelper.TABLE_UTENTI,
            projection,
            selection,
            selectionArgs,
            null,
            null,
            null
        )

        //il cursore punta a -1 non a 0
        cursor?.moveToFirst()
        if(cursor.count!=0){
            return true
        }
        return false
    }

}