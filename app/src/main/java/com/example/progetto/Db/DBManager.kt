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

    fun delete(_id: Long){
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