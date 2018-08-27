package com.br.controlecondominio.activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.br.controlecondominio.R;
import com.br.controlecondominio.dao.DespesaDao;
import com.br.controlecondominio.dao.MoradorDao;
import com.br.controlecondominio.dao.PrestadorDao;
import com.br.controlecondominio.dao.ReceitaDao;
import com.br.controlecondominio.util.Util;

public class FormTotalizadorActivity extends AppCompatActivity {

    private TextView txtCountReceita, txtCountDespesa, txtCountMorador, txtCountPrestador;
    private DespesaDao despesaDao;
    private ReceitaDao receitaDao;
    private MoradorDao moradorDao;
    private PrestadorDao prestadorDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_totalizadores);
        receitaDao = new ReceitaDao(this);
        despesaDao = new DespesaDao(this);
        moradorDao = new MoradorDao(this);
        prestadorDao = new PrestadorDao(this);

        txtCountReceita = findViewById(R.id.txtCountReceita);
        txtCountDespesa = findViewById(R.id.txtCountDespesa);
        txtCountMorador = findViewById(R.id.txtCountMorador);
        txtCountPrestador = findViewById(R.id.txtCountPrestador);

        final int countReceita = receitaDao.totalDeRegistros();
        final int countMorador = moradorDao.totalDeRegistros();
        final int countPrestador = prestadorDao.totalDeRegistros();
        final int countDespesa = despesaDao.totalDeRegistrosDespesa();

        final Double sum = receitaDao.sumValor();

        txtCountReceita.setText("Contador de Registros - Receita: " + countReceita);
        txtCountDespesa.setText("Contador de Registros - Despesa: " + countDespesa);
        txtCountMorador.setText("Contador de Registros - Morador: " + countMorador);
        txtCountPrestador.setText("Contador de Registros - Prestador: " + countPrestador);


    }

}
