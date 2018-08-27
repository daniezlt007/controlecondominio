package com.br.controlecondominio.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import com.br.controlecondominio.config.DBCore;
import com.br.controlecondominio.modelo.Morador;
import com.br.controlecondominio.modelo.Receita;

import java.util.ArrayList;
import java.util.List;

public class ReceitaDao {

    private DBCore dao;

    public ReceitaDao(Context context) {
        dao = new DBCore(context);
    }

    public void addReceita(Receita receita){
        SQLiteDatabase db = dao.getWritableDatabase();
        ContentValues dados = pegaDadosReceita(receita);
        db.insert("receita", null, dados);
        //db.close();
    }

    @NonNull
    private ContentValues pegaDadosReceita(Receita receita) {
        ContentValues dados = new ContentValues();
        dados.put("desc_receita" , receita.getDescReceita());
        dados.put("data_recebimento" , receita.getDataRecebimento());
        dados.put("valor" , receita.getValor());
        dados.put("morador" , receita.getMorador());
        return dados;
    }

    public List<Receita> searchReceita() {
        String sql = "select * from receita";
        SQLiteDatabase db = dao.getReadableDatabase();
        List<Receita> receitas = new ArrayList<>();
        Cursor c = db.rawQuery(sql, null);
        while (c.moveToNext()){
            Receita receita = new Receita();
            Morador morador = new Morador();
            receita.setIdReceita(c.getInt(c.getColumnIndex("idreceita")));
            receita.setDescReceita(c.getString(c.getColumnIndex("desc_receita")));
            receita.setDataRecebimento(c.getString(c.getColumnIndex("data_recebimento")));
            receita.setValor(c.getDouble(c.getColumnIndex("valor")));
            receita.setMorador(c.getString(c.getColumnIndex("morador")));
            receitas.add(receita);
        }
        c.close();
        return receitas;
    }

    public void deleteReceita(Receita receita) {
        SQLiteDatabase db = dao.getWritableDatabase();
        String[] params = {(receita.getIdReceita().toString())};
        db.delete("receita", "idreceita" + "= ?", params);
        //db.close();
    }

    public void updateReceita(Receita receita) {
        SQLiteDatabase db = dao.getWritableDatabase();
        ContentValues dados = pegaDadosReceita(receita);
        String[] params = {(receita.getIdReceita().toString())};
        db.update("receita", dados, "idreceita = ?", params);
        //db.close();
    }

    public int totalDeRegistros() {
        int count = 0;
        try {
            String sql = "select count(*) from receita";
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
            String sql = "select sum(valor) from receita";
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
