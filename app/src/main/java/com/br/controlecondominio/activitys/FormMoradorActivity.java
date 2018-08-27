package com.br.controlecondominio.activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.br.controlecondominio.dao.MoradorDao;
import com.br.controlecondominio.helper.FormularioMoradorHelper;
import com.br.controlecondominio.R;
import com.br.controlecondominio.modelo.Morador;

public class FormMoradorActivity extends AppCompatActivity {

    private FormularioMoradorHelper formularioMoradorHelper;
    private MoradorDao dao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_morador_form);
        dao = new MoradorDao(this);
        formularioMoradorHelper = new FormularioMoradorHelper(this);

        Intent intent = getIntent();
        Morador morador = (Morador)intent.getSerializableExtra("morador");
        if(morador != null){
            formularioMoradorHelper.preencheFormulario(morador);
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
                Morador morador = formularioMoradorHelper.pegaMorador();
                if(morador.getIdmorador() != null){
                    dao.updateMorador(morador);
                }else{
                    dao.addMorador(morador);
                }
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
