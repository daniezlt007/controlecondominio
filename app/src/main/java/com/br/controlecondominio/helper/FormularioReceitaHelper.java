package com.br.controlecondominio.helper;

import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;

import com.br.controlecondominio.R;
import com.br.controlecondominio.activitys.FormReceitaActivity;
import com.br.controlecondominio.modelo.Morador;
import com.br.controlecondominio.modelo.PrestadorServico;
import com.br.controlecondominio.modelo.Receita;

public class FormularioReceitaHelper {

    private EditText campoDescricaoReceita, campoDataReceita, campoValorReceita, campoMorador;
    private Receita receita;

    public FormularioReceitaHelper(FormReceitaActivity formReceitaActivity){
        campoDescricaoReceita = formReceitaActivity.findViewById(R.id.form_txtDescReceita);
        campoDataReceita = formReceitaActivity.findViewById(R.id.form_txtDataRecebimento);
        campoValorReceita = formReceitaActivity.findViewById(R.id.form_txtValor);
        campoMorador = formReceitaActivity.findViewById(R.id.form_txtMorador);
        receita = new Receita();
    }

    public Receita pegaReceita() {
        receita.setDescReceita(campoDescricaoReceita.getText().toString());
        receita.setDataRecebimento(campoDataReceita.getText().toString());
        receita.setValor(Double.parseDouble(campoValorReceita.getText().toString()));
        receita.setMorador(campoMorador.getText().toString());
        return receita;
    }

    public void preencheFormularioReceita(Receita receita){
        campoDescricaoReceita.setText(receita.getDescReceita());
        campoDataReceita.setText(receita.getDataRecebimento());
        campoValorReceita.setText(receita.getValor().toString());
        campoMorador.setText(receita.getMorador());
        this.receita = receita;
    }

}
