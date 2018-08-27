package com.br.controlecondominio.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import com.br.controlecondominio.config.DBCore;
import com.br.controlecondominio.modelo.PrestadorServico;

import java.util.ArrayList;
import java.util.List;

public class PrestadorDao {

    private DBCore dao;

    public PrestadorDao(Context context) {
        this.dao = new DBCore(context);
    }

    public void addPrestador(PrestadorServico prestador){
        SQLiteDatabase db = dao.getWritableDatabase();
        ContentValues dados = pegaDadosPrestador(prestador);
        db.insert("prestador", null, dados);
        //db.close();
    }

    @NonNull
    private ContentValues pegaDadosPrestador(PrestadorServico prestador) {
        ContentValues dados = new ContentValues();
        dados.put("nome_prestador" , prestador.getNomePrestador());
        dados.put("celular_prestador" , prestador.getCelularPrestador());
        dados.put("documento_prestador" , prestador.getDocumento());
        return dados;
    }

    public void deletePrestador(PrestadorServico prestador) {
        SQLiteDatabase db = dao.getWritableDatabase();
        String[] params = {(prestador.getIdprestador().toString())};
        db.delete("prestador", "idprestador" + "= ?", params);
        //db.close();
    }

    public void updatePrestador(PrestadorServico prestador) {
        SQLiteDatabase db = dao.getWritableDatabase();
        ContentValues dados = pegaDadosPrestador(prestador);
        String[] params = {(prestador.getIdprestador().toString())};
        db.update("prestador", dados, "idprestador = ?", params);
        //db.close();
    }

    public List<PrestadorServico> searchPrestador(){
        String sql = "select * from prestador";
        SQLiteDatabase db = dao.getReadableDatabase();
        List<PrestadorServico> prestadores = new ArrayList<>();
        Cursor c = db.rawQuery(sql, null);
        while (c.moveToNext()){
            PrestadorServico prestador = new PrestadorServico();
            prestador.setIdprestador(c.getInt(c.getColumnIndex("idprestador")));
            prestador.setNomePrestador(c.getString(c.getColumnIndex("nome_prestador")));
            prestador.setCelularPrestador(c.getString(c.getColumnIndex("celular_prestador")));
            prestador.setDocumento(c.getString(c.getColumnIndex("documento_prestador")));
            prestadores.add(prestador);
        }
        c.close();
        return prestadores;
    }

    public int totalDeRegistros() {
        int count = 0;
        try {
            String sql = "select count(*) from prestador";
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
