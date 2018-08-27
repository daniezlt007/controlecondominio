package com.br.controlecondominio.activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.br.controlecondominio.R;
import com.br.controlecondominio.dao.PrestadorDao;
import com.br.controlecondominio.helper.FormularioPrestadorHelper;
import com.br.controlecondominio.modelo.PrestadorServico;

public class FormPrestadorActivity extends AppCompatActivity {

    private FormularioPrestadorHelper formularioPrestadorHelper;
    private PrestadorDao dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prestador_form);
        dao = new PrestadorDao(this);
        formularioPrestadorHelper = new FormularioPrestadorHelper(this);
        Intent intent = getIntent();
        PrestadorServico prestador = (PrestadorServico) intent.getSerializableExtra("prestador");
        if(prestador != null){
            formularioPrestadorHelper.preencheFormularioPrestador(prestador);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_item, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_form_ok:
                PrestadorServico prestador = formularioPrestadorHelper.pegaPrestador();
                if(prestador.getIdprestador() != null){
                    dao.updatePrestador(prestador);
                }else{
                    dao.addPrestador(prestador);
                }
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
