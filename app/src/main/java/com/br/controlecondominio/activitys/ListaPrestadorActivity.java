package com.br.controlecondominio.activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.br.controlecondominio.R;
import com.br.controlecondominio.config.DBCore;
import com.br.controlecondominio.dao.PrestadorDao;
import com.br.controlecondominio.modelo.PrestadorServico;

import java.util.List;

public class ListaPrestadorActivity extends AppCompatActivity {

    private ListView listaPrestadores;
    private Button btnAddPrestador;
    private PrestadorDao dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_prestador);
        dao = new PrestadorDao(this);
        listaPrestadores = findViewById(R.id.listaPrestador);
        listaPrestadores.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> lista, View item, int posicao, long id) {
                PrestadorServico prestador = (PrestadorServico) listaPrestadores.getItemAtPosition(posicao);
                Intent intent = new Intent(ListaPrestadorActivity.this, FormPrestadorActivity.class);
                intent.putExtra("prestador", prestador);
                startActivity(intent);
            }
        });

        btnAddPrestador = findViewById(R.id.btnAddPrestador);
        btnAddPrestador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent form = new Intent(ListaPrestadorActivity.this, FormPrestadorActivity.class);
                startActivity(form);
            }
        });
        registerForContextMenu(listaPrestadores);

    }
    @Override
    protected void onResume() {
        super.onResume();
        carregaLista();
    }

    private void carregaLista() {
        List<PrestadorServico> prestadores = dao.searchPrestador();
        ArrayAdapter<PrestadorServico> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                prestadores);
        listaPrestadores.setAdapter(adapter);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {
        MenuItem deletar = menu.add("Deletar");
        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
                PrestadorServico prestador = (PrestadorServico)listaPrestadores.getItemAtPosition(info.position);
                dao.deletePrestador(prestador);
                carregaLista();
                return false;
            }
        });
    }
}
