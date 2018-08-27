package com.br.controlecondominio.modelo;

import java.io.Serializable;

public class Despesa implements Serializable {

    private Integer idDespesa;
    private String descDespesa;
    private String dataPagamento;
    private Double valor;
    private String prestador;

    public Despesa() {

    }

    public Despesa(Integer idReceita, String descDespesa, String dataPagamento, Double valor, String prestador) {
        this.idDespesa = idReceita;
        this.descDespesa = descDespesa;
        this.dataPagamento = dataPagamento;
        this.valor = valor;
        this.prestador = prestador;
    }

    public Integer getIdDespesa() {
        return idDespesa;
    }

    public void setIdDespesa(Integer idDespesa) {
        this.idDespesa = idDespesa;
    }

    public String getDescDespesa() {
        return descDespesa;
    }

    public void setDescDespesa(String descDespesa) {
        this.descDespesa = descDespesa;
    }

    public String getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(String dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getPrestador() {
        return prestador;
    }

    public void setPrestador(String prestador) {
        this.prestador = prestador;
    }

    @Override
    public String toString() {
        return descDespesa + " - " + prestador;
    }
}
