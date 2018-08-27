package com.br.controlecondominio.activitys;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import com.br.controlecondominio.R;
import com.br.controlecondominio.dao.DespesaDao;
import com.br.controlecondominio.fragment.DatePickerFragment;
import com.br.controlecondominio.helper.FormularioDespesaHelper;
import com.br.controlecondominio.modelo.Despesa;

import java.util.Calendar;

public class FormDespesaActivity extends AppCompatActivity {

    private DespesaDao despesaDao;
    private FormularioDespesaHelper formularioDespesaHelper;
    private EditText form_txtDataPagamento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_despesa);
        despesaDao = new DespesaDao(this);
        form_txtDataPagamento = findViewById(R.id.form_txtDataPagamento);
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

    public void setData(View view) {
        DatePickerFragment datePickerFragment = new DatePickerFragment();

        Calendar cal = Calendar.getInstance();

        Bundle bundle = new Bundle();
        bundle.putInt("dia", cal.get(Calendar.DAY_OF_MONTH));
        bundle.putInt("mes", cal.get(Calendar.MONTH));
        bundle.putInt("ano", cal.get(Calendar.YEAR));

        datePickerFragment.setArguments(bundle);
        datePickerFragment.setDateListener(dateListener);
        datePickerFragment.show(getFragmentManager(), "Data Receb.");
    }

    private DatePickerDialog.OnDateSetListener dateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            form_txtDataPagamento.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
        }
    };
}
