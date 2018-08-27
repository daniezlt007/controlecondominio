package com.br.controlecondominio.modelo;

import java.io.Serializable;

public class Morador implements Serializable {

    private Integer idmorador;
    private String nomeMorador;
    private String nomeProprietario;
    private String celularMorador;
    private String celularProprietario;
    private String numCasa;

    public Morador() {

    }

    public Morador(Integer idmorador, String nomeMorador, String nomeProprietario, String celularMorador, String celularProprietario, String numCasa) {
        this.idmorador = idmorador;
        this.nomeMorador = nomeMorador;
        this.nomeProprietario = nomeProprietario;
        this.celularMorador = celularMorador;
        this.celularProprietario = celularProprietario;
        this.numCasa = numCasa;
    }

    public Integer getIdmorador() {
        return idmorador;
    }

    public void setIdmorador(int idmorador) {
        this.idmorador = idmorador;
    }

    public String getNomeMorador() {
        return nomeMorador;
    }

    public void setNomeMorador(String nomeMorador) {
        this.nomeMorador = nomeMorador;
    }

    public String getNomeProprietario() {
        return nomeProprietario;
    }

    public void setNomeProprietario(String nomeProprietario) {
        this.nomeProprietario = nomeProprietario;
    }

    public String getCelularMorador() {
        return celularMorador;
    }

    public void setCelularMorador(String celularMorador) {
        this.celularMorador = celularMorador;
    }

    public String getCelularProprietario() {
        return celularProprietario;
    }

    public void setCelularProprietario(String celularProprietario) {
        this.celularProprietario = celularProprietario;
    }

    public String getNumCasa() {
        return numCasa;
    }

    public void setNumCasa(String numCasa) {
        this.numCasa = numCasa;
    }

    @Override
    public String toString() {
        return getIdmorador() + " - " + getNomeMorador();
    }
}
