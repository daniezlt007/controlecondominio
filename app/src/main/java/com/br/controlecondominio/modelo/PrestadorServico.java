package com.br.controlecondominio.modelo;

import java.io.Serializable;

public class PrestadorServico implements Serializable {

    private Integer idprestador;
    private String nomePrestador;
    private String celularPrestador;
    private String documento;

    public PrestadorServico() {

    }

    public PrestadorServico(Integer idprestador, String nomePrestador, String celularPrestador, String documento) {
        this.idprestador = idprestador;
        this.nomePrestador = nomePrestador;
        this.celularPrestador = celularPrestador;
        this.documento = documento;
    }

    public Integer getIdprestador() {
        return idprestador;
    }

    public void setIdprestador(Integer idprestador) {
        this.idprestador = idprestador;
    }

    public String getNomePrestador() {
        return nomePrestador;
    }

    public void setNomePrestador(String nomePrestador) {
        this.nomePrestador = nomePrestador;
    }

    public String getCelularPrestador() {
        return celularPrestador;
    }

    public void setCelularPrestador(String celularPrestador) {
        this.celularPrestador = celularPrestador;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    @Override
    public String toString() {
        return getIdprestador() + " - " + getNomePrestador();
    }
}
