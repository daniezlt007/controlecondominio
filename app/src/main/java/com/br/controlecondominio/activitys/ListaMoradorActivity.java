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
import com.br.controlecondominio.dao.MoradorDao;
import com.br.controlecondominio.modelo.Morador;

import java.util.List;

public class ListaMoradorActivity extends AppCompatActivity {

    private ListView listaMoradores;
    private Button btnAddMorador;
    private MoradorDao dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_morador);
        dao = new MoradorDao(this);
        listaMoradores = findViewById(R.id.listaMorador);
        listaMoradores.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> lista, View item, int posicao, long id) {
                Morador morador = (Morador)listaMoradores.getItemAtPosition(posicao);
                Intent intent = new Intent(ListaMoradorActivity.this, FormMoradorActivity.class);
                intent.putExtra("morador", morador);
                startActivity(intent);
            }
        });

        btnAddMorador = findViewById(R.id.btnAddMorador);
        btnAddMorador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent form = new Intent(ListaMoradorActivity.this, FormMoradorActivity.class);
                startActivity(form);
            }
        });
        registerForContextMenu(listaMoradores);
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregaLista();
    }

    private void carregaLista() {
        List<Morador> moradores = dao.searchMorador();
        ArrayAdapter<Morador> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                moradores);
        listaMoradores.setAdapter(adapter);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {
        MenuItem deletar = menu.add("Deletar");
        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
                Morador morador = (Morador)listaMoradores.getItemAtPosition(info.position);
                dao.deleteMorador(morador);
                carregaLista();
                return false;
            }
        });
    }
}
