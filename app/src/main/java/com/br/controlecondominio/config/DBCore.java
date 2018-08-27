package com.br.controlecondominio.config;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;
import android.util.Log;

import com.br.controlecondominio.modelo.Morador;
import com.br.controlecondominio.modelo.PrestadorServico;
import com.br.controlecondominio.modelo.Receita;

import java.util.ArrayList;
import java.util.List;

public class DBCore extends SQLiteOpenHelper {

    private static final String NOME_BD="condominio.db";
    private static final int VERSAO_BD=1;

    public DBCore(Context context) {
        super(context, NOME_BD, null, VERSAO_BD);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i("DBCore","Criando BD");
        db.isOpen();
        //Início -- Criação de tabelas --
        Log.i("DBCore","Criando tabela usuario");
        db.execSQL("CREATE TABLE usuario (idusuario INTEGER PRIMARY KEY AUTOINCREMENT,login TEXT NOT NULL," +
                "senha TEXT NOT NULL)");

        Log.i("DBCore","Criando tabela morador");
        db.execSQL("CREATE TABLE morador (idmorador INTEGER PRIMARY KEY AUTOINCREMENT,nome_morador TEXT NOT NULL," +
                "nome_proprietario TEXT NOT NULL, celular_morador TEXT NOT NULL," +
                "celular_proprietario TEXT NOT NULL, num_casa TEXT NOT NULL)");

        Log.i("DBCore","Criando tabela prestador");
        db.execSQL("CREATE TABLE prestador (idprestador INTEGER PRIMARY KEY AUTOINCREMENT,nome_prestador TEXT NOT NULL," +
                "celular_prestador TEXT NOT NULL, documento_prestador TEXT)");

        Log.i("DBCore","Criando tabela receita");
        db.execSQL("CREATE TABLE receita (idreceita INTEGER PRIMARY KEY AUTOINCREMENT, desc_receita TEXT NOT NULL," +
                "data_recebimento TEXT NOT NULL, valor REAL NOT NULL," +
                "morador TEXT NOT NULL)");

        Log.i("DBCore","Criando tabela despesa");
        db.execSQL("CREATE TABLE despesa (iddespesa INTEGER PRIMARY KEY AUTOINCREMENT, desc_despesa TEXT NOT NULL," +
                "data_pagamento TEXT NOT NULL, valor REAL NOT NULL," +
                "prestador TEXT NOT NULL)");
        //Final -- Criação de tabelas --

        //Início -- Criação de índices --
        db.execSQL("CREATE INDEX idxreceita ON receita(desc_receita)");
        db.execSQL("CREATE INDEX idxdespesa ON despesa(desc_despesa)");
        db.execSQL("CREATE INDEX idxmorador ON morador(nome_morador)");
        db.execSQL("CREATE INDEX idxfornecedor ON prestador(nome_prestador)");
        //Final -- Criação de índices --
        //db.close();

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i("DBCore", "UpgradeBD");

        try {
            db.execSQL("DROP TABLE IF EXISTS usuario");
        } catch (Exception e) {
            Log.i("DBCore", "Impossível apagar usuario");
        }

        try {
            db.execSQL("DROP TABLE IF EXISTS morador");
        } catch (Exception e) {
            Log.i("DBCore", "Impossível apagar morador");
        }

        try {
            db.execSQL("DROP TABLE IF EXISTS fornecedor");
        } catch (Exception e) {
            Log.i("DBCore", "Impossível apagar fornecedor");
        }

        try {
            db.execSQL("DROP TABLE IF EXISTS receita");
        } catch (Exception e) {
            Log.i("DBCore", "Impossível apagar receita");
        }

        try {
            db.execSQL("DROP TABLE IF EXISTS despesa");
        } catch (Exception e) {
            Log.i("DBCore", "Impossível apagar despesa");
        }
        onCreate(db);
    }

}

