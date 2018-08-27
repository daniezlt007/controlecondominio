package com.br.controlecondominio.helper;

import android.widget.EditText;

import com.br.controlecondominio.R;
import com.br.controlecondominio.activitys.FormDespesaActivity;
import com.br.controlecondominio.activitys.FormReceitaActivity;
import com.br.controlecondominio.modelo.Despesa;

public class FormularioDespesaHelper {

    private EditText campoDescricaoDespesa, campoDataDespesa, campoValorDespesa, campoPrestador;
    private Despesa despesa;

    public FormularioDespesaHelper(FormDespesaActivity formDespesaActivity){
        campoDescricaoDespesa = formDespesaActivity.findViewById(R.id.form_txtDescDespesa);
        campoDataDespesa = formDespesaActivity.findViewById(R.id.form_txtDataPagamento);
        campoValorDespesa = formDespesaActivity.findViewById(R.id.form_txtValor);
        campoPrestador = formDespesaActivity.findViewById(R.id.form_txtPrestador);
        despesa = new Despesa();
    }

    public Despesa pegaDespesa() {
        despesa.setDescDespesa(campoDescricaoDespesa.getText().toString());
        despesa.setDataPagamento(campoDataDespesa.getText().toString());
        despesa.setValor(Double.parseDouble(campoValorDespesa.getText().toString()));
        despesa.setPrestador(campoPrestador.getText().toString());
        return despesa;
    }

    public void preencheFormularioDespesa(Despesa despesa){
        campoDescricaoDespesa.setText(despesa.getDescDespesa());
        campoDataDespesa.setText(despesa.getDataPagamento());
        campoValorDespesa.setText(despesa.getValor().toString());
        campoPrestador.setText(despesa.getPrestador());
        this.despesa = despesa;
    }

}
