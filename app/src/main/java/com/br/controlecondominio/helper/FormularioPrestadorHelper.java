package com.br.controlecondominio.helper;

import android.widget.EditText;

import com.br.controlecondominio.R;
import com.br.controlecondominio.activitys.FormPrestadorActivity;
import com.br.controlecondominio.modelo.PrestadorServico;

public class FormularioPrestadorHelper {

    private EditText campoNomePrestador, campoCelularPrestador, campoDocumentoPrestador;
    private PrestadorServico prestadorServico;


    public FormularioPrestadorHelper(FormPrestadorActivity formPrestadorActivity) {
        campoNomePrestador = formPrestadorActivity.findViewById(R.id.form_txtNomePrestador);
        campoCelularPrestador = formPrestadorActivity.findViewById(R.id.form_txtCelularPrestador);
        campoDocumentoPrestador = formPrestadorActivity.findViewById(R.id.form_txtDocumentoPrestador);
        prestadorServico = new PrestadorServico();
    }

    public PrestadorServico pegaPrestador(){
        prestadorServico.setNomePrestador(campoNomePrestador.getText().toString());
        prestadorServico.setCelularPrestador(campoCelularPrestador.getText().toString());
        prestadorServico.setDocumento(campoDocumentoPrestador.getText().toString());
        return prestadorServico;
    }

    public void preencheFormularioPrestador(PrestadorServico prestador){
        campoNomePrestador.setText(prestador.getNomePrestador());
        campoCelularPrestador.setText(prestador.getCelularPrestador());
        campoDocumentoPrestador.setText(prestador.getDocumento());
        this.prestadorServico = prestador;
    }

}
