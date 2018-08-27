package com.br.controlecondominio.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.br.controlecondominio.R;
import com.br.controlecondominio.dao.DespesaDao;
import com.br.controlecondominio.modelo.Despesa;
import com.br.controlecondominio.modelo.Receita;

import java.util.List;

public class ListaDespesaActivity extends AppCompatActivity {

    private ListView listaDespesa;
    private Button btnAddDespesa;
    private DespesaDao despesaDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_despesa);

        despesaDao = new DespesaDao(this);
        listaDespesa = findViewById(R.id.listaDespesa);
        listaDespesa.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int posicao, long id) {
                Despesa despesa = (Despesa)listaDespesa.getItemAtPosition(posicao);
                Intent intent = new Intent(ListaDespesaActivity.this, FormDespesaActivity.class);
                intent.putExtra("despesa", despesa);
                startActivity(intent);
            }
        });

        btnAddDespesa = findViewById(R.id.btnAddDespesa);
        btnAddDespesa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListaDespesaActivity.this, FormDespesaActivity.class);
                startActivity(intent);
            }
        });
        registerForContextMenu(listaDespesa);
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregaLista();
    }

    private void carregaLista() {
        List<Despesa> despesas = despesaDao.searchDespesa();
        ArrayAdapter<Despesa> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                despesas);
        listaDespesa.setAdapter(adapter);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {
        MenuItem deletar = menu.add("Deletar");
        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
                Despesa despesa = (Despesa) listaDespesa.getItemAtPosition(info.position);
                despesaDao.deleteDespesa(despesa);
                carregaLista();
                return false;
            }
        });
    }

}
