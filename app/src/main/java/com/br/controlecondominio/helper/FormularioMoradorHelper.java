package com.br.controlecondominio.helper;

import android.widget.EditText;

import com.br.controlecondominio.R;
import com.br.controlecondominio.activitys.FormMoradorActivity;
import com.br.controlecondominio.modelo.Morador;

public class FormularioMoradorHelper {

    private final EditText campoNomeMorador, campoNomeProprietario, campoTelefoneMorador,
            campoTelefoneProprietario, campoNumCasa;
    private Morador morador;

    public FormularioMoradorHelper(FormMoradorActivity formMoradorActivity) {
        campoNomeMorador = (EditText) formMoradorActivity.findViewById(R.id.form_txtNomeMorador);
        campoNomeProprietario = (EditText) formMoradorActivity.findViewById(R.id.form_txtProprietario);
        campoTelefoneMorador = (EditText) formMoradorActivity.findViewById(R.id.form_txtCelularMorador);
        campoTelefoneProprietario = (EditText) formMoradorActivity.findViewById(R.id.form_txtCelularProprietario);
        campoNumCasa = (EditText) formMoradorActivity.findViewById(R.id.form_txtNumCasa);
        morador = new Morador();
    }

    public Morador pegaMorador() {
        morador.setNomeMorador(campoNomeMorador.getText().toString());
        morador.setCelularMorador(campoTelefoneMorador.getText().toString());
        morador.setCelularProprietario(campoTelefoneProprietario.getText().toString());
        morador.setNomeProprietario(campoNomeProprietario.getText().toString());
        morador.setNumCasa(campoNumCasa.getText().toString());
        return morador;
    }

    public void preencheFormulario(Morador morador) {
        campoNomeMorador.setText(morador.getNomeMorador());
        campoNomeProprietario.setText(morador.getNomeProprietario());
        campoTelefoneMorador.setText(morador.getCelularMorador());
        campoTelefoneProprietario.setText(morador.getCelularProprietario());
        campoNumCasa.setText(morador.getNumCasa());
        this.morador = morador;
    }
}
