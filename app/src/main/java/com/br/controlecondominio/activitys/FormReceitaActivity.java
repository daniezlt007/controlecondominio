package com.br.controlecondominio.activitys;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.br.controlecondominio.R;
import com.br.controlecondominio.dao.MoradorDao;
import com.br.controlecondominio.dao.ReceitaDao;
import com.br.controlecondominio.fragment.DatePickerFragment;
import com.br.controlecondominio.helper.FormularioReceitaHelper;
import com.br.controlecondominio.modelo.Morador;
import com.br.controlecondominio.modelo.Receita;

import java.util.Calendar;

public class FormReceitaActivity extends AppCompatActivity {

    private ReceitaDao receitaDao;
    private FormularioReceitaHelper formularioReceitaHelper;
    private EditText form_txtDataRecebimento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receita_form);
        receitaDao = new ReceitaDao(this);
        form_txtDataRecebimento = findViewById(R.id.form_txtDataRecebimento);
        formularioReceitaHelper = new FormularioReceitaHelper(this);
        Intent intent = getIntent();
        final Receita receita = (Receita) intent.getSerializableExtra("receita");
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
            form_txtDataRecebimento.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
        }
    };

}
