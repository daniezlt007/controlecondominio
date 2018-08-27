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
import com.br.controlecondominio.dao.MoradorDao;
import com.br.controlecondominio.dao.ReceitaDao;
import com.br.controlecondominio.modelo.Receita;

import java.util.List;

public class ListaReceitaActivity extends AppCompatActivity {

    private ListView listaReceita;
    private Button btnAddReceita;
    private ReceitaDao receitaDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_receita);

        receitaDao = new ReceitaDao(this);
        listaReceita = findViewById(R.id.listaReceita);
        listaReceita.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> lista, View item, int posicao, long id) {
                Receita receita = (Receita)listaReceita.getItemAtPosition(posicao);
                Intent intent = new Intent(ListaReceitaActivity.this, FormReceitaActivity.class);
                intent.putExtra("receita", receita);
                startActivity(intent);

            }
        });

        btnAddReceita = findViewById(R.id.btnAddReceita);
        btnAddReceita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent form = new Intent(ListaReceitaActivity.this, FormReceitaActivity.class);
                startActivity(form);
            }
        });
        registerForContextMenu(listaReceita);
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregaLista();
    }

    private void carregaLista() {
        List<Receita> receitas = receitaDao.searchReceita();
        ArrayAdapter<Receita> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                receitas);
        listaReceita.setAdapter(adapter);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {
        MenuItem deletar = menu.add("Deletar");
        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
                Receita receita = (Receita)listaReceita.getItemAtPosition(info.position);
                receitaDao.deleteReceita(receita);
                carregaLista();
                return false;
            }
        });
    }

}
