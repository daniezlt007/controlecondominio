package com.br.controlecondominio.modelo;

import java.io.Serializable;

public class Receita implements Serializable {

    private Integer idReceita;
    private String descReceita;
    private String dataRecebimento;
    private Double valor;
    private String morador;

    public Receita() {

    }

    public Receita(Integer idReceita, String descReceita, String dataRecebimento, Double valor, String morador) {
        this.idReceita = idReceita;
        this.descReceita = descReceita;
        this.dataRecebimento = dataRecebimento;
        this.valor = valor;
        this.morador = morador;
    }

    public Integer getIdReceita() {
        return idReceita;
    }

    public void setIdReceita(Integer idReceita) {
        this.idReceita = idReceita;
    }

    public String getDescReceita() {
        return descReceita;
    }

    public void setDescReceita(String descReceita) {
        this.descReceita = descReceita;
    }

    public String getDataRecebimento() {
        return dataRecebimento;
    }

    public void setDataRecebimento(String dataRecebimento) {
        this.dataRecebimento = dataRecebimento;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getMorador() {
        return morador;
    }

    public void setMorador(String morador) {
        this.morador = morador;
    }

    @Override
    public String toString() {
        return descReceita + " - " + morador;
    }
}
