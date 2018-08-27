package com.br.controlecondominio.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import com.br.controlecondominio.config.DBCore;
import com.br.controlecondominio.modelo.Despesa;
import com.br.controlecondominio.modelo.Morador;
import com.br.controlecondominio.modelo.Receita;

import java.util.ArrayList;
import java.util.List;

public class DespesaDao {

    private DBCore dao;

    public DespesaDao(Context context) {
        dao = new DBCore(context);
    }

    public void addDespesa(Despesa despesa){
        SQLiteDatabase db = dao.getWritableDatabase();
        ContentValues dados = pegaDadosDespesa(despesa);
        db.insert("despesa", null, dados);
        //db.close();
    }

    @NonNull
    private ContentValues pegaDadosDespesa(Despesa despesa) {
        ContentValues dados = new ContentValues();
        dados.put("desc_despesa" , despesa.getDescDespesa());
        dados.put("data_pagamento" , despesa.getDataPagamento());
        dados.put("valor" , despesa.getValor());
        dados.put("prestador" , despesa.getPrestador());
        return dados;
    }

    public List<Despesa> searchDespesa() {
        String sql = "select * from despesa";
        SQLiteDatabase db = dao.getReadableDatabase();
        List<Despesa> despesas = new ArrayList<>();
        Cursor c = db.rawQuery(sql, null);
        while (c.moveToNext()){
            Despesa despesa = new Despesa();
            despesa.setIdDespesa(c.getInt(c.getColumnIndex("iddespesa")));
            despesa.setDescDespesa(c.getString(c.getColumnIndex("desc_despesa")));
            despesa.setDataPagamento(c.getString(c.getColumnIndex("data_pagamento")));
            despesa.setValor(c.getDouble(c.getColumnIndex("valor")));
            despesa.setPrestador(c.getString(c.getColumnIndex("prestador")));
            despesas.add(despesa);
        }
        c.close();
        return despesas;
    }

    public void deleteDespesa(Despesa despesa) {
        SQLiteDatabase db = dao.getWritableDatabase();
        String[] params = {(despesa.getIdDespesa().toString())};
        db.delete("despesa", "iddespesa" + "= ?", params);
        //db.close();
    }

    public void updateReceita(Despesa despesa) {
        SQLiteDatabase db = dao.getWritableDatabase();
        ContentValues dados = pegaDadosDespesa(despesa);
        String[] params = {(despesa.getIdDespesa().toString())};
        db.update("despesa", dados, "iddespesa = ?", params);
        //db.close();
    }

    public int totalDeRegistrosDespesa() {
        int count = 0;
        try {
            String sql = "select count(*) from despesa";
            SQLiteDatabase db = dao.getReadableDatabase();
            Cursor cursor = db.rawQuery(sql, null);
            cursor.moveToFirst();
            count = cursor.getInt(0);
            cursor.close();
            db.close();

        } catch (Exception e) {
            // TODO: handle exception
            //Log.e("GroupDBErro", "GetGroup Count "+e.getMessage());
            e.printStackTrace();
        }
        return count;
    }

    public Double sumValor() {
        Double count = 0.0;
        try {
            String sql = "select sum(valor) from despesa";
            SQLiteDatabase db = dao.getReadableDatabase();
            Cursor cursor = db.rawQuery(sql, null);
            cursor.moveToFirst();
            count = cursor.getDouble(0);
            cursor.close();
            db.close();

        } catch (Exception e) {
            // TODO: handle exception
            //Log.e("GroupDBErro", "GetGroup Count "+e.getMessage());
            e.printStackTrace();
        }
        return count;
    }

}
