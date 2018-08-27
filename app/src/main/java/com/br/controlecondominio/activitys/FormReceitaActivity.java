package com.br.controlecondominio.activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import com.br.controlecondominio.R;
import com.br.controlecondominio.dao.MoradorDao;
import com.br.controlecondominio.dao.ReceitaDao;
import com.br.controlecondominio.helper.FormularioReceitaHelper;
import com.br.controlecondominio.modelo.Morador;
import com.br.controlecondominio.modelo.Receita;

import java.util.Calendar;

public class FormReceitaActivity extends AppCompatActivity {

    private ReceitaDao receitaDao;
    private FormularioReceitaHelper formularioReceitaHelper;
    private int ano, mes, dia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receita_form);
        receitaDao = new ReceitaDao(this);

        formularioReceitaHelper = new FormularioReceitaHelper(this);
        Intent intent = getIntent();
        final Receita receita = (Receita) intent.getSerializableExtra("receita");
        //Log.i("RECEITA", "id: " + morador.getIdmorador());

        if (receita != null) {
            formularioReceitaHelper.preencheFormularioReceita(receita);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_item_geral, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_form_ok:
                Receita receita = formularioReceitaHelper.pegaReceita();
                if (receita.getIdReceita() != null) {
                    receitaDao.updateReceita(receita);
                } else {
                    receitaDao.addReceita(receita);
                }
                finish();
                break;
            case R.id.menu_form_total:
                Intent intent = new Intent(FormReceitaActivity.this, FormTotalizadorActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void dataSchedule(View view) {
        Calendar c = Calendar.getInstance();
        c.set(ano, mes, dia);
        /*DatePickerDialog dialog = new DatePickerDialog(
                this,
                c.get(Calendar.YEAR),
                c.get(Calendar.MONTH),
                c.get(Calendar.DAY_OF_MONTH));*/

    }

    private void initData() {
        if (ano == 0) {
            Calendar c = Calendar.getInstance();
            ano = c.get(Calendar.YEAR);
            mes = c.get(Calendar.MONTH);
            dia = c.get(Calendar.DAY_OF_MONTH);

        }

    }

}
