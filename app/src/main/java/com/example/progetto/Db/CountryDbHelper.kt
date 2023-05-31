package com.example.progetto.Db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.ContactsContract.CommonDataKinds.Email

class CountryDbHelper(context: Context) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {

    companion object {
        // DATABASE NAME
        const val DB_NAME = "CINEMA_DATABASE"

        // TABLE NAME
        const val TABLE_UTENTI = "UTENTI"
        const val TABLE_CINEMA ="CINEMA"

        // TABLE COLUMNS
        const val _ID: String = "_id"
        const val EMAIL: String = "email"
        const val PASSWORD: String = "password"
        const val NOME: String = "nome"
        const val COGNOME = "cognome"
        const val INDIRIZZ0= "indirizzo"
        const val PAGAMENTO = "pagamento"

        const val _ID_FILM = "_id_film"
        const val NOME_FILM = "nome_film"
        const val SALA_FILM = "sala_film"
        const val ORA_INIZIO = "ora_inizio"
        const val DURATA = "durata"


        // DATABASE VERSION
        const val DB_VERSION = 1

        // STRING TO CREATE TABLE
        private const val SQL_CREATE_UTENTI =
            "CREATE TABLE $TABLE_UTENTI (" +
                    "$_ID LONG PRIMARY KEY," +
                    "$EMAIL TEXT NOT NULL," +
                    "$PASSWORD TEXT NOT NULL," +
                    "$NOME TEXT NOT NULL," +
                    "$COGNOME TEXT NOT NULL," +
                    "$INDIRIZZ0 TEXT NOT NULL," +
                    "$PAGAMENTO TEXT NOT NULL);"


        private const val SQL_CREATE_CINEMA =
            "CREATE TABLE $TABLE_CINEMA (" +
                    "$_ID_FILM LONG PRIMARY KEY," +
                    "$NOME_FILM TEXT NOT NULL," +
                    "$SALA_FILM TEXT NOT NULL," +
                    "$ORA_INIZIO DATE NOT NULL," +
                    "$DURATA TEXT NOT NULL);"

        // STRING TO DROP TABLE
        private const val SQL_DELETE_UTENTI =
            "DROP TABLE IF EXISTS $TABLE_UTENTI;"

        private const val SQL_DELETE_CINEMA =
            "DROP TABLE IF EXISTS $TABLE_CINEMA;"



    }



override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(SQL_CREATE_UTENTI)
        db?.execSQL(SQL_CREATE_CINEMA)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db?.execSQL(SQL_DELETE_UTENTI)
        db?.execSQL(SQL_DELETE_CINEMA)
        onCreate(db)
    }
}