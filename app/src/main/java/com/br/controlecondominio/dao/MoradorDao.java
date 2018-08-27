package com.br.controlecondominio.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import com.br.controlecondominio.config.DBCore;
import com.br.controlecondominio.modelo.Morador;

import java.util.ArrayList;
import java.util.List;

public class MoradorDao {

    private DBCore dao;

    public MoradorDao(Context context) {
        this.dao = new DBCore(context);
    }

    public void addMorador(Morador morador){
        SQLiteDatabase db = dao.getWritableDatabase();
        ContentValues dados = pegaDadosMorador(morador);
        db.insert("morador", null, dados);
        //db.close();
    }

    @NonNull
    private ContentValues pegaDadosMorador(Morador morador) {
        ContentValues dados = new ContentValues();
        dados.put("nome_morador" , morador.getNomeMorador());
        dados.put("nome_proprietario" , morador.getNomeProprietario());
        dados.put("celular_morador" , morador.getCelularMorador());
        dados.put("celular_proprietario" , morador.getCelularProprietario());
        dados.put("num_casa" , morador.getNumCasa());
        return dados;
    }

    public List<Morador> searchMorador(){
        String sql = "select * from morador";
        SQLiteDatabase db = dao.getReadableDatabase();
        List<Morador> moradores = new ArrayList<>();
        Cursor c = db.rawQuery(sql, null);
        while (c.moveToNext()){
            Morador morador = new Morador();
            morador.setIdmorador(c.getInt(c.getColumnIndex("idmorador")));
            morador.setNomeMorador(c.getString(c.getColumnIndex("nome_morador")));
            morador.setNomeProprietario(c.getString(c.getColumnIndex("nome_proprietario")));
            morador.setCelularMorador(c.getString(c.getColumnIndex("celular_morador")));
            morador.setCelularProprietario(c.getString(c.getColumnIndex("celular_proprietario")));
            morador.setNumCasa(c.getString(c.getColumnIndex("num_casa")));
            moradores.add(morador);
        }
        c.close();
        return moradores;
    }

    public void deleteMorador(Morador morador){
        SQLiteDatabase db = dao.getWritableDatabase();
        String[] params = {(morador.getIdmorador().toString())};
        db.delete("morador", "idmorador" + "= ?", params);
        //db.close();
    }

    public void updateMorador(Morador morador){
        SQLiteDatabase db = dao.getWritableDatabase();
        ContentValues dados = pegaDadosMorador(morador);
        String[] params = {(morador.getIdmorador().toString())};
        db.update("morador", dados, "idmorador = ?", params);
        //db.close();
    }

    public int totalDeRegistros() {
        int count = 0;
        try {
            String sql = "select count(*) from morador";
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

}
