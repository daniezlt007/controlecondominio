package com.br.controlecondominio.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.br.controlecondominio.R;
import com.br.controlecondominio.dao.DespesaDao;
import com.br.controlecondominio.helper.FormularioDespesaHelper;
import com.br.controlecondominio.modelo.Despesa;

import java.util.Calendar;

public class FormDespesaActivity extends AppCompatActivity {

    private DespesaDao despesaDao;
    private FormularioDespesaHelper formularioDespesaHelper;
    private int ano, mes, dia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_despesa);
        despesaDao = new DespesaDao(this);

        formularioDespesaHelper = new FormularioDespesaHelper(this);
        Intent intent = getIntent();
        final Despesa despesa = (Despesa) intent.getSerializableExtra("despesa");

        if (despesa != null) {
            formularioDespesaHelper.preencheFormularioDespesa(despesa);
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
                Despesa despesa = formularioDespesaHelper.pegaDespesa();
                if (despesa.getIdDespesa() != null) {
                    despesaDao.updateReceita(despesa);
                } else {
                    despesaDao.addDespesa(despesa);
                }
                finish();
                break;
            case R.id.menu_form_total:
                Intent intent = new Intent(FormDespesaActivity.this, FormTotalizadorActivity.class);
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
